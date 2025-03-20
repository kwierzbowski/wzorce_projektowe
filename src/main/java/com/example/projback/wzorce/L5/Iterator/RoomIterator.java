package com.example.projback.wzorce.L5.Iterator;

import com.example.projback.entity.Room;
import java.util.List;
//###   start L5 Iterator -> Room
public class RoomIterator implements Iterator<Room> {
    private List<Room> rooms;
    private int position = 0;

    public RoomIterator(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean hasNext() {
        return position < rooms.size();
    }

    @Override
    public Room next() {
        if (!hasNext()) {
            throw new IllegalStateException("Brak kolejnych pokoi");
        }
        return rooms.get(position++);
    }
}
//###   end L5 Iterator -> Room