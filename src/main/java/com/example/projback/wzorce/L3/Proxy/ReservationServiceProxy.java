package com.example.projback.wzorce.L3.Proxy;

import com.example.projback.config.JwtUtil;
import com.example.projback.dto.*;
import com.example.projback.entity.Reservation;
import com.example.projback.entity.ReservationStatus;
import com.example.projback.entity.User;
import com.example.projback.repository.ReservationRepository;
import com.example.projback.repository.UserRepository;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Reservation.IReservationService_Creating;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Reservation.IReservationService_Manipulating;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Reservation.IReservationService_Query;
import org.springframework.stereotype.Service;

import java.util.*;

//###   start L3 Proxy -> Part 2, validating access to reservation, holding reservations in cache
@Service
public class ReservationServiceProxy implements IReservationService_Query, IReservationService_Manipulating, IReservationService_Creating {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    //###   start L8 Odwrocenie zaleznosci -> usage
    //    @Autowired
    //    private IReservationService reservationService;
    private IReservationService_Query reservationServiceQuery;
    private IReservationService_Manipulating reservationServiceManipulating;
    private IReservationService_Creating reservationServiceCreating;

    //###   end L8 Odwrocenie zaleznosci -> usage

    private final Map<Long, Reservation> cache = new HashMap<>();  // Cache rezerwacji

    public ReservationServiceProxy(JwtUtil jwtUtil,
                                   UserRepository userRepository, ReservationRepository reservationRepository, IReservationService_Query reservationServiceQuery, IReservationService_Manipulating reservationServiceManipulating, IReservationService_Creating reservationServiceCreating) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
        this.reservationServiceQuery = reservationServiceQuery;
        this.reservationServiceManipulating = reservationServiceManipulating;
        this.reservationServiceCreating = reservationServiceCreating;
    }

    @Override
    public void createReservationByMediator(MakeReservationDTO reservationDTO, String token) {
        reservationServiceCreating.createReservationByMediator(reservationDTO, token);
    }

    @Override
    public void createReservation(MakeReservationDTO reservation, String token) {
        String username = getUsernameFromToken(token);
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        reservationServiceCreating.createReservation(reservation, token);
    }

    @Override
    public void deleteReservation(Long reservationId, String token) {
        validateUserAccessToReservation(reservationId, token);
        reservationServiceManipulating.deleteReservation(reservationId, token);
        cache.remove(reservationId);
    }

    @Override
    public void updateReservation(UpdateReservationRequestDTO updateReservation) {
        validateUserAccessToReservation(updateReservation.getReservationId(), updateReservation.getToken());
        reservationServiceManipulating.updateReservation(updateReservation);
        cache.remove(updateReservation.getReservationId());
    }

    @Override
    public void updateReservationStatusAndPrice(Long reservationId, UpdateReservationEmployeeDTO updateReservation, String token) {
        reservationServiceManipulating.updateReservationStatusAndPrice(reservationId, updateReservation, token);
        cache.remove(reservationId);
    }

    @Override
    public void updateReservationFields(Reservation reservation, UpdateReservationDTO updateReservation) {

    }

    @Override
    public List<Reservation> getReservationsByUser(String token) {
        return reservationServiceQuery.getReservationsByUser(token);
    }

    @Override
    public Reservation getCustomerReservationById(Long reservationId, String token) {
        return null;
    }

    @Override
    public List<Reservation> getReservationsByStatusOrAll(ReservationStatus status, String token) {
        return reservationServiceQuery.getReservationsByStatusOrAll(status, token);
    }

    @Override
    public Reservation getReservationById(Long reservationId, String token) {
        validateUserAccessToReservation(reservationId, token);
        return cache.computeIfAbsent(reservationId, id -> reservationServiceQuery.getReservationById(id, token));
    }


    @Override
    public List<Reservation> getFilteredReservations(FilterReservationDTO filterReservationDTO) {
        return reservationServiceQuery.getFilteredReservations(filterReservationDTO);
    }

//    @Override
//    public List<Reservation> getFilteredReservations(Long userId, ReservationStatus status, Date startDate, Date endDate) {
//        return reservationServiceQuery.getFilteredReservations(userId, status, startDate, endDate);
//    }
//    filterReservationDTO

    @Override
    public void printAllReservations() {

    }

    @Override
    public Reservation getEmployeeReservationById(Long reservationId, String token) {
        return reservationServiceQuery.getEmployeeReservationById(reservationId, token);
    }

    @Override
    public List<Reservation> getReservationsByRoomId(Long roomId, String token) {
        return reservationServiceQuery.getReservationsByRoomId(roomId, token);
    }

    private String getUsernameFromToken(String token) {
        return jwtUtil.extractUsername(token.substring(7));
    }

    private void validateUserAccessToReservation(Long reservationId, String token) {
        String username = getUsernameFromToken(token);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Reservation reservation = cache.getOrDefault(reservationId, reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found")));

        cache.put(reservationId, reservation);

        if (!reservation.getUserId().equals(user.getId())) {
            throw new RuntimeException("You are not the owner of this reservation");
        }
    }

    public Reservation undoLastReservationEdit() {
        return reservationServiceManipulating.undoLastReservationEdit();
    }
}
//###   end L3 Proxy -> Part 2