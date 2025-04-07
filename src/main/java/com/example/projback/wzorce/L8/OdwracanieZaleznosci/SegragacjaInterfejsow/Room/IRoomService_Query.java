package com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Room;

import java.util.Date;
import java.util.List;
import com.example.projback.entity.Room;

//###   start L8 Segregacja interfejsów
public interface IRoomService_Query {
    List<Room> getAllRooms();
    List<Room> findAvailableRooms(Date startTime, Date endTime);
    Room findRoomById(Long id);
    boolean isEquipmentAvailable(Long roomId, List<Long> equipment);
    boolean isRoomAvailableIgnoringReservation(Long roomId, Date startTime, Date endTime, Long reservationIdToIgnore);
    Room getRoomById(Long roomId, String token);
    void printAllRoom();
}
//###   end L8 Segregacja interfejsów