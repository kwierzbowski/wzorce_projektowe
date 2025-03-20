package com.example.projback.wzorce.L8.Liskov.Room.Klasy_Pochodne;

import com.example.projback.entity.Room;
import com.example.projback.entity.RoomType;

//###   start L8 Liskov -> Klasa pochodna
public class SmallRoom extends Room {
    public SmallRoom() {
        this.setType(RoomType.SMALL_ROOM);
    }
}
//###   end L8 Liskov -> Klasa pochodna