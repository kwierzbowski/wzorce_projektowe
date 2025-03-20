package com.example.projback.wzorce.L5.Iterator;

import com.example.projback.entity.Reservation;
import java.util.List;
//###   start L5 Iterator -> Collection -> Reservation
public class ReservationCollection implements IterableCollection<Reservation> {
    private List<Reservation> reservations;

    public ReservationCollection(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public Iterator<Reservation> createIterator() {
        return new ReservationIterator(reservations);
    }
}
//###   end L5 Iterator -> Collection -> Reservation