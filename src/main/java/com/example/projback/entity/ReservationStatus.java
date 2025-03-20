package com.example.projback.entity;

//###   start L3 Flyweight -> Part 1, Zastowowanie jako zastąpienie Stringa w obiekcie Reservation, przy pomocy enum co pozwala na oszczędzanie pamięci. Podobnie w innych użyciach enum.
public enum ReservationStatus {
    PENDING, APPROVED, CANCELED
}
//###  end L3 Flyweight -> Part 1
