package com.example.projback.wzorce.L5.Iterator;

import com.example.projback.entity.Room;
import java.util.List;
//###   start L5 Iterator -> Collection -> Room
public class RoomCollection implements IterableCollection<Room> {
    private List<Room> rooms;

    public RoomCollection(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public Iterator<Room> createIterator() {
        return new RoomIterator(rooms);
    }
}
//###   end L5 Iterator -> Collection -> Room