package com.example.projback.wzorce.L8.OdwracanieZaleznosci;

import com.example.projback.config.JwtUtil;
import com.example.projback.entity.Room;
import com.example.projback.repository.RoomRepository;
import com.example.projback.service.UserService;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Room.IRoomService_Creating;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Room.IRoomService_Manipulating;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Room.IRoomService_Query;

import java.util.Date;
import java.util.List;

//###   start L2 Bridge -> Klasa abstrakcyjna
//###   start L8 Odwrocenie zaleznosci -> klasa abstrakcyjna
public abstract class AbstractRoomService_Creating implements IRoomService_Creating {
    protected final RoomRepository roomRepository;
    protected final JwtUtil jwtUtil;
    protected final UserService userService;

    protected AbstractRoomService_Creating(RoomRepository roomRepository, JwtUtil jwtUtil, UserService userService) {
        this.roomRepository = roomRepository;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    public abstract Room createRoom(Room room, String token);
}
//###   end L8 Odwrocenie zaleznosci -> klasa abstrakcyjna
//###   end L2 Bridge -> Klasa abstrakcyjna