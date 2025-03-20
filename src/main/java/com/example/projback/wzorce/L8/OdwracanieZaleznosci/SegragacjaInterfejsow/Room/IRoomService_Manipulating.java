package com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Room;

import com.example.projback.entity.Room;

//###   start L8 Segregacja interfejsów
public interface IRoomService_Manipulating {
    String deleteRoomById(Long roomId, String token);
    Room updateRoom(Room room, String token, Long roomId);
    Room undoLastRoomEdit();
}
//###   end L8 Segregacja interfejsów