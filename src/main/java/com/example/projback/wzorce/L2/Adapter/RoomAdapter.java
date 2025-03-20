package com.example.projback.wzorce.L2.Adapter;

import com.example.projback.entity.Room;

//###   start L2 Adapter -> Part 3
public class RoomAdapter implements AdapterInterface {
    private final Room room;

    public RoomAdapter(Room room) {
        this.room = room;
    }

    @Override
    public String getName() {
        return room.getName();
    }

    @Override
    public Long getId() {
        return room.getId();
    }

}
//###   end L2 Adapter -> Part 3