package com.example.projback.wzorce.L1.factory;

import com.example.projback.entity.Room;
import com.example.projback.entity.RoomType;

//###   start L1 Factory -> Part 3 - declaration
public class RoomFactory {
    public static Room createRoom(String name, RoomType type, Double pricePerHour, Double pricePerDay) {
        Room room = new Room();
        room.setName(name);
        room.setType(type);
        room.setPricePerHour(pricePerHour);
        room.setPricePerDay(pricePerDay);
        return room;
    }
}
//###   end L1 Factory -> Part 3 - declaration