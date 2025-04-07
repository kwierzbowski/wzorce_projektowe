//###   start L6 STATE
package com.example.projback.wzorce.L6.State;

import com.example.projback.entity.Reservation;
import org.springframework.stereotype.Component;

@Component
public class PendingState implements ReservationState {
    @Override
    public void checkReservationState(Reservation reservation) {
        System.out.println("L6 | STATE | Status jest dalej PENDING.");
    }
}
//###   end L6 STATE

