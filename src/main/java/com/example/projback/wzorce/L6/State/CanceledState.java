//###   start L6 STATE
package com.example.projback.wzorce.L6.State;

import com.example.projback.entity.Reservation;
import org.springframework.stereotype.Component;

@Component
public class CanceledState implements ReservationState {
    @Override
    public void checkReservationState(Reservation reservation) {
        throw new IllegalStateException("L6 | STATE | Nie można zmienić anulowanej rezerwacji.");
    }
}
//###   end L6 STATE