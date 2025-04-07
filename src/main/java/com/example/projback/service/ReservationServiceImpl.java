package com.example.projback.service;

import com.example.projback.config.JwtUtil;
import com.example.projback.dto.*;
import com.example.projback.entity.*;
import com.example.projback.repository.ReservationRepository;
import com.example.projback.wzorce.L1.builder.ReservationBuilder;
import com.example.projback.wzorce.L2.Bridge.ReservationValidator;
import com.example.projback.wzorce.L5.Command.Command;
import com.example.projback.wzorce.L5.Command.Reservation_Create_Command;
import com.example.projback.wzorce.L5.Interpreter.Expression;
import com.example.projback.wzorce.L5.Interpreter.StatusInterpreter;
import com.example.projback.wzorce.L5.Interpreter.QueryParser;
import com.example.projback.wzorce.L5.Iterator.Iterator;
import com.example.projback.wzorce.L5.Iterator.ReservationCollection;
import com.example.projback.wzorce.L5.Mediator.ReservationMediator;
import com.example.projback.wzorce.L5.Memento.Caretaker;
import com.example.projback.wzorce.L5.Memento.Memento;
import com.example.projback.wzorce.L6.State.*;
//import com.example.projback.wzorce.L8.OdwracanieZaleznosci.IRoomService;
import com.example.projback.wzorce.L6.Strategy.DailyPricingStrategy;
import com.example.projback.wzorce.L6.Strategy.HourlyPricingStrategy;
import com.example.projback.wzorce.L6.Strategy.PricingContext;
import com.example.projback.wzorce.L6.Template.FreeCancellation;
import com.example.projback.wzorce.L6.Template.PaidCancellation;
import com.example.projback.wzorce.L6.Visitor.StandardPricingVisitor;
import com.example.projback.wzorce.L6.Visitor.SummerPricingVisitor;
import com.example.projback.wzorce.L6.Visitor.WinterPricingVisitor;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.AbstractReservationService_Creating;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Reservation.IReservationService_Manipulating;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Reservation.IReservationService_Query;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Reservation.IReservationService_Validator;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Room.IRoomService_Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.List;

//###   start L2 Bridge -> UserService -> Usage
//###   start L8 Odwrocenie zaleznosci -> Usage
@Service
public class ReservationServiceImpl extends AbstractReservationService_Creating implements IReservationService_Query, IReservationService_Manipulating, IReservationService_Validator {

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, JwtUtil jwtUtil, UserService userService, @Qualifier("simpleValidator_Reservation")ReservationValidator validator, IRoomService_Query roomService, @Qualifier("pendingState") ReservationState reservationState, PricingContext pricingContext, DailyPricingStrategy dailyPricingStrategy, HourlyPricingStrategy hourlyPricingStrategy) {
        super(reservationRepository, jwtUtil, userService);
        this.validator = validator;
        this.roomService_Query = roomService;
        this.reservationState = reservationState;
        this.pricingContext = pricingContext;
        this.dailyPricingStrategy = dailyPricingStrategy;
        this.hourlyPricingStrategy = hourlyPricingStrategy;
    }

    //###   end L2 Bridge -> UserService -> Usage
    //###   end L8 Odwrocenie zaleznosci -> Usage

    //###   start L5 MEMENTO -> Caretaker field
    private final Caretaker<Reservation> reservationCaretaker = new Caretaker<>();
    //###   end L5 MEMENTO -> Caretaker field

    //###   start L5 MEDIATOR -> field
    private ReservationMediator reservationMediator;
    @Autowired
    public void setMediator(ReservationMediator reservationMediator) {
        this.reservationMediator = reservationMediator;
    }
    //###   end L5 MEDIATOR -> field

    private final ReservationValidator validator;

    @Autowired
    private final IRoomService_Query roomService_Query;

    //###   start L6 STATE
    private final ReservationState reservationState;
    //###   end L6 STATE

    //###   start L6 STRATEGY
    private final PricingContext pricingContext;
    private final DailyPricingStrategy dailyPricingStrategy;
    private final HourlyPricingStrategy hourlyPricingStrategy;
    //###   end L6 STRATEGY

    //###   start L6 TEMPLATE
    @Autowired
    private FreeCancellation freeCancellation;

    @Autowired
    private PaidCancellation paidCancellation;
    //###   end L6 TEMPLATE

    //###   start L6 VISITOR
    @Autowired
    private SummerPricingVisitor summerPricingVisitor;

    @Autowired
    private WinterPricingVisitor winterPricingVisitor;

    @Autowired
    private StandardPricingVisitor standardPricingVisitor;
    //###   end L6 VISITOR

    private User userExtract(String token){
        String username = jwtUtil.extractUsername(token.substring(7));
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (jwtUtil.isTokenExpired(token.substring(7))) {
            throw new RuntimeException("Token is expired");
        }
        return user;
    }

    @Override
    public void createReservation(MakeReservationDTO reservation, String token) {
        //###   start L2 Bridge -> Reservation -> Part 4
        if (!validator.validate(reservation)) {
            throw new IllegalArgumentException("Rezerwacja niepoprawna");
        }
        //###   end L2 Bridge -> Reservation -> Part 4

        User user = userExtract(token);
//        String username = jwtUtil.extractUsername(token.substring(7));
//        User user = userService.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        if (jwtUtil.isTokenExpired(token.substring(7))) {
//            throw new RuntimeException("Token is expired");
//        }

        Room room = roomService_Query.findRoomById(reservation.getRoom());
//        boolean available = roomService.findAvailableRooms(reservation.getStartTime(), reservation.getEndTime()).contains(room);
//        if (!available) {
//            throw new RuntimeException("The date is taken");
//        }

        //###   start L1 Factory -> Part 2 - usage
//        Reservation newReservation = ReservationFactory.createReservation(user.getId(), room.getId(), reservation.getStartTime(),reservation.getEndTime(),reservation.getAdditionalEquipment(), reservation.getEstimatedPrice(), ReservationStatus.PENDING );
//        reservationRepository.save(newReservation);
        //###   end L1 Factory -> Part 2 - usage

        //###   start L1 Builder -> Part 2 - usage
        Reservation newReservation = new ReservationBuilder()
                .setUserId(user.getId())
                .setRoomId(room.getId())
                .setStartTime(reservation.getStartTime())
                .setEndTime(reservation.getEndTime())
                .setAdditionalEquipment(reservation.getAdditionalEquipment())
                .setEstimatedPrice(reservation.getEstimatedPrice())
                .setStatus(ReservationStatus.PENDING)
                .build();

        //###   start L6 STRATEGY
        applyPricingStrategy(newReservation, room);
        //###   end L6 STRATEGY

        //###   start L6 VISITOR
        applySeasonalPricing(newReservation);
        //###   end L6 VISITOR

        //###   start L5 Command -> Reservation - usage
        Command createReservationCommand = new Reservation_Create_Command(newReservation, reservationRepository);
        createReservationCommand.execute();
        //###   end L5 Command -> Reservation - usage

//        reservationRepository.save(newReservation);
        //###   end L1 Builder -> Part 2 - usage

    }

    @Override
    public void createReservationByMediator(MakeReservationDTO reservationDTO, String token) {

        Reservation reservation = new Reservation();
        reservation.setRoomId(reservationDTO.getRoom());
        reservation.setStartTime(reservationDTO.getStartTime());
        reservation.setEndTime(reservationDTO.getEndTime());
        reservation.setAdditionalEquipment(reservationDTO.getAdditionalEquipment());
        reservation.setEstimatedPrice(reservationDTO.getEstimatedPrice());
        reservation.setStatus(ReservationStatus.PENDING);
        //###   start L5 MEDIATOR -> Reservation - usage
        reservationMediator.notify(reservation, "create", token);
        //###   end L5 MEDIATOR -> Reservation - usage
    }

    @Override
    public Reservation validateReservation(String token, Long reservationId) {
        User user = userExtract(token);
//        String username = jwtUtil.extractUsername(token.substring(7));
//        User user = userService.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        if (jwtUtil.isTokenExpired(token.substring(7))) {
//            throw new RuntimeException("Token is expired");
//        }

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        if (!reservation.getUserId().equals(user.getId())) {
            throw new RuntimeException("You are not the owner of this reservation");
        }
        return reservation;
    }

    @Override
    public void deleteReservation(Long reservationId, String token) {
        Reservation reservation = validateReservation(token, reservationId);

        long hoursUntilStart = Duration.between(Instant.now(), reservation.getStartTime().toInstant()).toHours();

        if (hoursUntilStart >= 48) {
            freeCancellation.cancelReservation(reservation);
        } else {
            paidCancellation.cancelReservation(reservation);
        }
        reservationRepository.deleteById(reservationId);
    }

    @Override
    public void updateReservation(UpdateReservationRequestDTO updateReservationRequest) {

        Reservation reservation = validateReservation(updateReservationRequest.getToken(), updateReservationRequest.getReservationId());

        updateReservationFields(reservation, updateReservationRequest.getUpdateReservation());
        //###   start L6 STATE
        PendingState pendingState = new PendingState();
        ApprovedState approvedState = new ApprovedState();
        CanceledState canceledState = new CanceledState();
        switch (reservation.getStatus()) {
            case PENDING -> pendingState.checkReservationState(reservation);
            case APPROVED -> approvedState.checkReservationState(reservation);
            case CANCELED -> canceledState.checkReservationState(reservation);
            default -> throw new IllegalStateException("Nieobsługiwany status rezerwacji: " + reservation.getStatus());
        }
        //###   end L6 STATE
        reservationRepository.save(reservation);
    }

    @Override
    public void updateReservationStatusAndPrice(Long reservationId, UpdateReservationEmployeeDTO updateReservation, String token) {
        User user = userExtract(token);
//        String username = jwtUtil.extractUsername(token.substring(7));
//        User user = userService.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getRole().equals(Role.EMPLOYEE)) {
            throw new RuntimeException("You are not authorized to perform this action");
        }

//        if (jwtUtil.isTokenExpired(token.substring(7))) {
//            throw new RuntimeException("Token is expired");
//        }

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        if (updateReservation.getReservationStatus() != null) {
            reservation.setStatus(updateReservation.getReservationStatus());
        }

        if (updateReservation.getFinalPrice() != null) {
            if (updateReservation.getFinalPrice() < 0) {
                throw new IllegalArgumentException("Final price cannot be negative");
            }
            reservation.setFinalPrice(updateReservation.getFinalPrice());
        }

        //###   start L5 MEMENTO -> Reservation -> Usage (Saving in history)
        reservationCaretaker.save(new Memento<>(reservation.clone()));
        //###   end L5 MEMENTO -> Reservation -> Usage (Saving in history)

        reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getReservationsByStatusOrAll(ReservationStatus status, String token) {
//        String username = jwtUtil.extractUsername(token.substring(7));
//        User user = userService.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));

        User user = userExtract(token);
        if (!user.getRole().equals(Role.EMPLOYEE)) {
            throw new RuntimeException("You are not authorized to view reservations");
        }

//        if (jwtUtil.isTokenExpired(token.substring(7))) {
//            throw new RuntimeException("Token is expired");
//        }

        printAllReservations();

        StatusInterpreter reservationInterpreter = new StatusInterpreter(reservationRepository, status);
        return reservationInterpreter.interpret();

//        return status != null ? reservationRepository.findByStatus(status) : reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getReservationsByUser(String token) {
//        String username = jwtUtil.extractUsername(token.substring(7));
//        User user = userService.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
        User user = userExtract(token);
        return reservationRepository.findByUserId(user.getId());
    }

    @Override
    public void updateReservationFields(Reservation reservation, UpdateReservationDTO updateReservation) {
        if (updateReservation.getStartTime() != null) {
            reservation.setStartTime(updateReservation.getStartTime());
        }

        if (updateReservation.getEndTime() != null) {
            reservation.setEndTime(updateReservation.getEndTime());
        }

        if (updateReservation.getEquipment() != null) {
            reservation.setAdditionalEquipment(updateReservation.getEquipment());
        }
    }

    @Override
    public Reservation getCustomerReservationById(Long reservationId, String token) {
//        if (jwtUtil.isTokenExpired(token.substring(7))) {
//            throw new RuntimeException("Token is expired");
//        }
//
//        String username = jwtUtil.extractUsername(token.substring(7));
//        User user = userService.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        return reservationRepository.findById(reservationId)
//                .filter(reservation -> reservation.getUserId().equals(user.getId()))
//                .orElseThrow(() -> new RuntimeException("Reservation not found or access denied"));
        return getReservationById(reservationId, token);
    }

    @Override
    public Reservation getEmployeeReservationById(Long reservationId, String token) {
//        if (jwtUtil.isTokenExpired(token.substring(7))) {
//            throw new RuntimeException("Token is expired");
//        }
//
//        String username = jwtUtil.extractUsername(token.substring(7));
//        User user = userService.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
        User user = userExtract(token);

        if (!user.getRole().equals(Role.EMPLOYEE)) {
            throw new RuntimeException("Access denied. User is not an employee.");
        }

        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    @Override
    public List<Reservation> getReservationsByRoomId(Long roomId, String token) {
//        if (jwtUtil.isTokenExpired(token.substring(7))) {
//            throw new RuntimeException("Token is expired");
//        }
//        String username = jwtUtil.extractUsername(token.substring(7));
//        User user = userService.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
        User user = userExtract(token);
        return reservationRepository.findByRoomId(roomId);

    }
    @Override
    public Reservation getReservationById(Long reservationId, String token) {
//        if (jwtUtil.isTokenExpired(token.substring(7))) {
//            throw new RuntimeException("Token is expired");
//        }
//
//        String username = jwtUtil.extractUsername(token.substring(7));
//        User user = userService.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
        User user = userExtract(token);

        return reservationRepository.findById(reservationId)
                .filter(reservation -> reservation.getUserId().equals(user.getId()))
                .orElseThrow(() -> new RuntimeException("Reservation not found or access denied"));
    }

    //###   start L9 e, Part2
    @Override
    public List<Reservation> getFilteredReservations(FilterReservationDTO filterReservationDTO) {

        //###   start L5 Interpreter -> usage
        String mode = " AND ";

        StringBuilder queryBuilder = new StringBuilder();
        if (filterReservationDTO.getStatus() != null) queryBuilder.append("status=").append(filterReservationDTO.getStatus()).append(mode);
        if (filterReservationDTO.getUserId() != null) queryBuilder.append("userId=").append(filterReservationDTO.getUserId()).append(mode);
        if (filterReservationDTO.getStartDate() != null && filterReservationDTO.getEndDate() != null) queryBuilder.append("date=").append(filterReservationDTO.getStartDate()).append(",").append(filterReservationDTO.getEndDate()).append(mode);


        String query = queryBuilder.toString().replaceAll(" AND $", "");
        query = query.toString().replaceAll(" OR $", "");
        System.out.println("zapytanie: " + query);

        Expression filter = QueryParser.parse(query, reservationRepository);

        if (filter == null) {
            throw new IllegalStateException("Błąd " + query);
        }

        List<Reservation> reservations = filter.interpret();
        return reservations;
        //###   end L5 Interpreter -> usage
    }
    //###   start L9 e, Part2

    //###   start L5 MEMENTO -> Reservation -> Usage (Undo changes)
    @Override
    public Reservation undoLastReservationEdit() {
        Memento<Reservation> memento = reservationCaretaker.undo();
        if (memento != null) {
            Reservation restored = memento.getState();
            return reservationRepository.save(restored);
        }
        return null;
    }
    //###   end L5 MEMENTO -> Reservation -> Usage (Undo changes)

    //###   start L5 ITERATOR -> Reservation -> Usage
    @Override
    public void printAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        ReservationCollection collection = new ReservationCollection(reservations);
        Iterator<Reservation> iterator = collection.createIterator();

        while (iterator.hasNext()) {
            Reservation reservation = iterator.next();
            System.out.println("Id: " + reservation.getId() + ", status: " + reservation.getStatus());
        }
    }
    //###   end L5 ITERATOR -> Reservation -> Usage

    //###   start L1 Prototype -> Part 3 -> Usage
    public Reservation duplicateReservation(Long id) {

        Reservation existing = reservationRepository.findById(id).orElseThrow();
        Reservation cloned = existing.clone();

        cloned.setFinalPrice(200.0);
        return cloned;
    }
    //###   end L1 Prototype -> Part 3 -> Usage

    //###   start L6 STRATEGY
    public void applyPricingStrategy(Reservation reservation, Room room) {
        long hours = Duration.between(reservation.getStartTime().toInstant(), reservation.getEndTime().toInstant()).toHours();

        if (hours % 24 == 0) {
            pricingContext.setStrategy(dailyPricingStrategy);
        } else {
            pricingContext.setStrategy(hourlyPricingStrategy);
        }

        pricingContext.executeStrategy(reservation, room);
    }
    //###   end L6 STRATEGY

    //###   start L6 VISITOR
    public void applySeasonalPricing(Reservation reservation) {
        LocalDate now = LocalDate.now();
        Month month = now.getMonth();

        if (month == Month.JUNE || month == Month.JULY || month == Month.AUGUST) {
            reservation.accept(summerPricingVisitor);
        } else if (month == Month.DECEMBER || month == Month.JANUARY || month == Month.FEBRUARY) {
            reservation.accept(winterPricingVisitor);
        } else {
            reservation.accept(standardPricingVisitor);
        }
    }
    //###   end L6 VISITOR

}
