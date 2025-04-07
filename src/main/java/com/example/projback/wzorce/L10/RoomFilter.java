package com.example.projback.wzorce.L10;

import com.example.projback.entity.Room;

//###   start L10, RoomFilter (part 1)
@FunctionalInterface
public interface RoomFilter {
    boolean test(Room room);
}
//###   start L10, RoomFilter  (part 1)
