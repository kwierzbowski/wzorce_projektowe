//package com.example.projback.wzorce.L8.OdwracanieZaleznosci;
//
//import com.example.projback.config.JwtUtil;
//import com.example.projback.entity.Room;
//import com.example.projback.repository.RoomRepository;
//import com.example.projback.service.UserService;
//import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Room.IRoomService_Creating;
//import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Room.IRoomService_Manipulating;
//import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Room.IRoomService_Query;
//
//import java.util.Date;
//import java.util.List;
////###   start L8 Odwrocenie zaleznosci -> klasa abstrakcyjna
//public abstract class AbstractRoomService implements IRoomService_Manipulating, IRoomService_Query, IRoomService_Creating {
//    protected final RoomRepository roomRepository;
//    protected final JwtUtil jwtUtil;
//    protected final UserService userService;
//
//    protected AbstractRoomService(RoomRepository roomRepository, JwtUtil jwtUtil, UserService userService) {
//        this.roomRepository = roomRepository;
//        this.jwtUtil = jwtUtil;
//        this.userService = userService;
//    }
//
//    public abstract Room createRoom(Room room, String token);
//
//    public abstract List<Room> getAllRooms();
//
//    public abstract List<Room> findAvailableRooms(Date startTime, Date endTime);
//
//    public abstract Room findRoomById(Long id);
//
//    public abstract boolean isEquipmentAvailable(Long roomId, List<Long> equipment);
//
//    public abstract boolean isRoomAvailableIgnoringReservation(Long roomId, Date startTime, Date endTime, Long reservationIdToIgnore);
//
//    public abstract Room getRoomById(Long roomId, String token);
//
//    public abstract String deleteRoomById(Long roomId, String token);
//
//    public abstract Room updateRoom(Room room, String token, Long roomId);
//
//    public abstract Room undoLastRoomEdit();
//
//    public abstract void printAllRoom();
//}
//
////###   end L8 Odwrocenie zaleznosci -> klasa abstrakcyjna