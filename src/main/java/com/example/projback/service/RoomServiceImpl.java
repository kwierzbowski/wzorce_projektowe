package com.example.projback.service;

import com.example.projback.config.JwtUtil;
import com.example.projback.entity.*;
import com.example.projback.repository.EquipmentRepository;
import com.example.projback.repository.ReservationRepository;
import com.example.projback.repository.RoomRepository;
import com.example.projback.wzorce.L1.builder.RoomBuilder;
import com.example.projback.wzorce.L2.Adapter.AdapterInterface;
import com.example.projback.wzorce.L2.Adapter.EquipmentAdapter;
import com.example.projback.wzorce.L2.Adapter.RoomAdapter;
import com.example.projback.wzorce.L2.Composite.EquipmentGroup;
import com.example.projback.wzorce.L5.Iterator.Iterator;
import com.example.projback.wzorce.L5.Iterator.RoomCollection;
import com.example.projback.wzorce.L5.Mediator.EquipmentMediator;
import com.example.projback.wzorce.L5.Mediator.RoomMediator;
import com.example.projback.wzorce.L5.Memento.Caretaker;
import com.example.projback.wzorce.L5.Memento.Memento;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.AbstractRoomService_Creating;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Room.IRoomService_Manipulating;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Room.IRoomService_Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//###   start L2 Bridge -> UserService -> Usage
//###   start L8 Odwrocenie zaleznosci -> Usage
@Service
public class RoomServiceImpl extends AbstractRoomService_Creating implements IRoomService_Query, IRoomService_Manipulating {

    private ReservationRepository reservationRepository;

    public RoomServiceImpl(RoomRepository roomRepository, JwtUtil jwtUtil, UserService userService, ReservationRepository reservationRepository) {
        super(roomRepository, jwtUtil, userService);
        this.reservationRepository = reservationRepository;
    }

    //###   end L2 Bridge -> UserService -> Usage
    //###   end L8 Odwrocenie zaleznosci -> Usage

    //###   start L5 MEMENTO -> Caretaker field
    private final Caretaker<Room> roomCaretaker = new Caretaker<>();
    //###   end L5 MEMENTO -> Caretaker field

    //###   start L1 Singleton -> Part 3 - Bill Pugh Singleton
    private static class SingletonHelper {
        private static RoomServiceImpl INSTANCE;
    }
    public static RoomServiceImpl getInstance(RoomRepository roomRepository, JwtUtil jwtUtil, UserService userService, ReservationRepository reservationRepository) {
        if (RoomServiceImpl.SingletonHelper.INSTANCE == null) {
            synchronized (RoomServiceImpl.class) {
                if (RoomServiceImpl.SingletonHelper.INSTANCE == null) {
                    RoomServiceImpl.SingletonHelper.INSTANCE = new RoomServiceImpl(roomRepository, jwtUtil, userService, reservationRepository);
                }
            }
        }
        return RoomServiceImpl.SingletonHelper.INSTANCE;
    }
    //###   end L1 Singleton -> Part 3 - Bill Pugh Singleton

    //###   start L5 MEDIATOR -> field
    private RoomMediator roomMediator;
    @Autowired
    public void setMediator(RoomMediator roomMediator) {
        this.roomMediator = roomMediator;
    }
    //###   end L5 MEDIATOR -> field

    @Override
    public Room createRoom(Room room, String token) {
        String username = jwtUtil.extractUsername(token.substring(7));

        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (jwtUtil.isTokenExpired(token.substring(7))) {
            throw new RuntimeException("Token się przedawnił");
        }

        if (user.getRole() != Role.EMPLOYEE) {
            throw new RuntimeException("You are not authorized to create rooms");
        }

        // Możesz dodać dodatkowe walidacje dla `room` tutaj:
        // Przykład: sprawdzanie, czy nazwa pokoju nie jest pusta
        if (room.getName() == null || room.getName().isEmpty()) {
            throw new IllegalArgumentException("Room name cannot be empty");
        }

        //###   start L2 Composite -> Part 3 -> Usage
//        if(!room.getOptionalEquipment().isEmpty()){
//            Equipment equipment1 = new Equipment();
//            Equipment equipment2 = new Equipment();
//
//            equipment1 = equipmentRepository.findEquipmentById(1L);
//            equipment2 = equipmentRepository.findEquipmentById(10L);
//
//            EquipmentGroup optionalEquipment = new EquipmentGroup("Zestaw dodatkowy");
//            optionalEquipment.addComponent(equipment1);
//            optionalEquipment.addComponent(equipment2);
//
//            equipment1.displayInfo();
//            optionalEquipment.displayInfo();
//        }
        //###   end L2 Composite -> Part 3 -> Usage

        //###   start L1 Factory -> Part 3 - usage
//        Room newRoom = RoomFactory.createRoom(room.getName(), room.getType(), room.getPricePerHour(), room.getPricePerDay());
//        return roomRepository.save(newRoom);
        //###   end L1 Factory -> Part 3 - usage

        //###   start L1 Builder -> Part 3 - usage
        Room newRoom = new RoomBuilder()
                .setName(room.getName())
                .setBaseEquipment(room.getBaseEquipment())
                .setOptionalEquipment(room.getOptionalEquipment())
                .setType(room.getType())
                .setPricePerDay(room.getPricePerDay())
                .setPricePerHour(room.getPricePerHour())
                .build();

        //###   start L5 MEDIATOR -> Room - usage
        return roomMediator.notify(newRoom, "create", token);
        //###   end L5 MEDIATOR -> Room - usage

//        return roomRepository.save(newRoom);
        //###   end L1 Builder -> Part 3 - usage
    }

    @Override
    public List<Room> getAllRooms() {
        printAllRoom();
        return roomRepository.findAll();
    }

    @Override
    public List<Room> findAvailableRooms(Date startTime, Date endTime) {
        if (startTime.after(endTime)) {
            throw new IllegalArgumentException("Start time cannot be after end time");
        }

        return roomRepository.findAvailableRooms(startTime, endTime);
    }

    @Override
    public Room findRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));
    }

    @Override
    public boolean isEquipmentAvailable(Long roomId, List<Long> equipment) {
        Room room = findRoomById(roomId);

        // Możesz dodać dodatkowe zabezpieczenia:
        // Przykład: sprawdzanie, czy lista wyposażenia nie jest pusta
        if (equipment == null || equipment.isEmpty()) {
            throw new IllegalArgumentException("Equipment list cannot be empty");
        }
        return room.getOptionalEquipment().containsAll(equipment);
    }

    @Override
    public boolean isRoomAvailableIgnoringReservation(Long roomId, Date startTime, Date endTime, Long reservationIdToIgnore) {
        if (startTime.after(endTime)) {
            throw new IllegalArgumentException("Start time cannot be after end time");
        }

        List<Reservation> conflictingReservations = reservationRepository.findReservationsForRoom(
                roomId, startTime, endTime);

        // Ignorowanie rezerwacji o podanym ID
        return conflictingReservations.stream()
                .noneMatch(reservation -> !reservation.getId().equals(reservationIdToIgnore));
    }

    @Override
    public Room getRoomById(Long roomId, String token) {
        if (jwtUtil.isTokenExpired(token.substring(7))) {
            throw new RuntimeException("Token się przedawnił");
        }

        return roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));
    }

    @Override
    public String deleteRoomById(Long roomId, String token) {
        String username = jwtUtil.extractUsername(token.substring(7));

        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (jwtUtil.isTokenExpired(token.substring(7))) {
            throw new RuntimeException("Token się przedawnił.");
        }

        if (user.getRole() != Role.EMPLOYEE) {
            throw new RuntimeException("You are not authorized to delete rooms");
        }
        try {
            roomRepository.deleteById(roomId);
            return "Usunięto";
        }catch (Exception e){
            return "Cos poszło nie tak z usuwaniem.";
        }
    }

    @Override
    public Room updateRoom(Room room, String token, Long roomId) {
        String username = jwtUtil.extractUsername(token.substring(7));
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (jwtUtil.isTokenExpired(token.substring(7))) {
            throw new RuntimeException("Token się przedawnił.");
        }

        if (user.getRole() != Role.EMPLOYEE) {
            throw new RuntimeException("You are not authorized to delete rooms");
        }

        if(room == null) {
            throw new IllegalArgumentException("Room cannot be null");
        }
        if (room.getName() == null || room.getName().isEmpty()) {
            throw new IllegalArgumentException("Room name cannot be empty");
        }
        if(room.getPricePerDay() ==null || room.getPricePerDay() <= 0) {
            throw new IllegalArgumentException("Price per day cannot be empty");
        }
        if(room.getPricePerHour() == null || room.getPricePerHour() <= 0) {
            throw new IllegalArgumentException("Price per hour cannot be empty");
        }
        if(room.getType() == null) {
            throw new IllegalArgumentException("Room type cannot be empty");
        }

        //###   start L5 MEMENTO -> Room -> Usage (Saving in history)
        Room currentRoom = findRoomById(roomId);
        roomCaretaker.save(new Memento<>(currentRoom.clone()));
        //###   end L5 MEMENTO -> Room -> Usage (Saving in history)

        currentRoom.setName(room.getName());
        currentRoom.setBaseEquipment(room.getBaseEquipment());
        currentRoom.setPricePerDay(room.getPricePerDay());
        currentRoom.setPricePerHour(room.getPricePerHour());
        currentRoom.setType(room.getType());
        currentRoom.setOptionalEquipment(room.getOptionalEquipment());

        return roomRepository.save(currentRoom);
    }

    //###   start L5 MEMENTO -> Room -> Usage (Undo changes)
    @Override
    public Room undoLastRoomEdit() {
        Memento<Room> memento = roomCaretaker.undo();
        if (memento != null) {
            Room restored = memento.getState();
            roomRepository.save(restored);
        }
        return null;
    }
    //###   end L5 MEMENTO -> Room -> Usage (Undo changes)

    //###   start L5 ITERATOR -> Room -> Usage
    @Override
    public void printAllRoom() {
        List<Room> roomList = roomRepository.findAll();
        RoomCollection collection = new RoomCollection(roomList);
        Iterator<Room> iterator = collection.createIterator();

        while (iterator.hasNext()) {
            Room room = iterator.next();
            System.out.println("Name: " + room.getName() + ", base equipment: " + room.getBaseEquipment());
        }
    }
    //###   end L5 ITERATOR -> Room -> Usage

    //###   start L1 Prototype -> Part 3 -> Usage
    public Room duplicateRoom(Long id) {

        Room existing = roomRepository.findById(id).orElseThrow();
        Room cloned = existing.clone();

        //###   start L2 Adapter -> Part 3 -> Usage
        AdapterInterface adapter = new RoomAdapter(cloned);
        String name = ("Cloned " + adapter.getName());
        //###   end L2 Adapter -> Part 3 -> Usage

        cloned.setName(name);
        return cloned;
    }
    //###   end L1 Prototype -> Part 3 -> Usage
}