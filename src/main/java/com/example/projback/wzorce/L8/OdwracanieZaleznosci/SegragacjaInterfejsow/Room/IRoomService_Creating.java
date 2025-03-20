package com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Room;

import com.example.projback.entity.Room;

//###   start L8 Segregacja interfejsów
public interface IRoomService_Creating {
    Room createRoom(Room room, String token);
}
//###   end L8 Segregacja interfejsów