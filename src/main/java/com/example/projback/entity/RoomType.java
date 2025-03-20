package com.example.projback.entity;

//###   start L3 Flyweight -> Part 3
public enum RoomType {
    SMALL_ROOM, HALL, CONFERENCE_ROOM
}
//###   end L3 Flyweight -> Part 3, jak była mowa przy oddawaniu labolatorium, można uznać relacyjne połączenie obiektów bazy danych jako zastosowanie Flyweight, gdyż nie tworzymy za każdym razem obiektów tylko wykorzystujemy istniejące(ciężko by to było oznaczyć w kodzie)