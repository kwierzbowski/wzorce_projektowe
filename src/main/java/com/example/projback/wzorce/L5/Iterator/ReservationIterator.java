package com.example.projback.wzorce.L5.Iterator;

import com.example.projback.entity.Reservation;
import java.util.List;
//###   start L5 Iterator -> Reservation
public class ReservationIterator implements Iterator<Reservation> {
    private List<Reservation> reservations;
    private int position = 0;

    public ReservationIterator(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public boolean hasNext() {
        return position < reservations.size();
    }

    @Override
    public Reservation next() {
        if (!hasNext()) {
            throw new IllegalStateException("Brak kolejnych rezerwacji");
        }
        return reservations.get(position++);
    }
}
//###   end L5 Iterator -> Reservation