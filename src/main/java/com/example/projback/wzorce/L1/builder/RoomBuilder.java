package com.example.projback.wzorce.L1.builder;

import com.example.projback.entity.Room;
import com.example.projback.entity.RoomType;
import jakarta.persistence.*;

import java.util.List;

//###   start L1 Builder -> Part 3 - declaration
public class RoomBuilder {
    private Room room = new Room();

    public RoomBuilder setName(String name) {
        room.setName(name);
        return this;
    }

    public RoomBuilder setType(RoomType type) {
        room.setType(type);
        return this;
    }

    public RoomBuilder setBaseEquipment(List<Long> baseEquipment) {
        room.setBaseEquipment(baseEquipment);
        return this;
    }

    public RoomBuilder setOptionalEquipment(List<Long> optionalEquipment) {
        room.setOptionalEquipment(optionalEquipment);
        return this;
    }

    public RoomBuilder setPricePerHour(Double pricePerHour) {
        room.setPricePerHour(pricePerHour);
        return this;
    }

    public RoomBuilder setPricePerDay(Double pricePerDay) {
        room.setPricePerDay(pricePerDay);
        return this;
    }

    public Room build() {
        return room;
    }
}
//###   end L1 Builder -> Part 3 - declaration