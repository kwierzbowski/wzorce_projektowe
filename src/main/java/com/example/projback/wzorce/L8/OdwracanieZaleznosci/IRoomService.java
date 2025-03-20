//package com.example.projback.wzorce.L8.OdwracanieZaleznosci;
//
//import com.example.projback.entity.Room;
//
//import java.util.Date;
//import java.util.List;
//
/// /###   start L8 Odwrocenie zaleznosci -> Interfejs
/// /###   start L8 "Gruby" interfejs"
//public interface IRoomService {
//    Room createRoom(Room room, String token);
//    List<Room> getAllRooms();
//    List<Room> findAvailableRooms(Date startTime, Date endTime);
//    Room findRoomById(Long id);
//    boolean isEquipmentAvailable(Long roomId, List<Long> equipment);
//    boolean isRoomAvailableIgnoringReservation(Long roomId, Date startTime, Date endTime, Long reservationIdToIgnore);
//    Room getRoomById(Long roomId, String token);
//    String deleteRoomById(Long roomId, String token);
//    Room updateRoom(Room room, String token, Long roomId);
//    Room undoLastRoomEdit();
//    void printAllRoom();
//}
/// /###   end L8 Odwrocenie zaleznosci -> Interfejs
/// /###   end L8 "Gruby" interfejs"