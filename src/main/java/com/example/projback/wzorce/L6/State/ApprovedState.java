package com.example.projback.wzorce.L6.State;

import com.example.projback.entity.Reservation;
import org.springframework.stereotype.Component;

//###   start L6 State
@Component
public class ApprovedState implements ReservationState {
    @Override
    public void checkReservationState(Reservation reservation) {
        reservation.setStatus(com.example.projback.entity.ReservationStatus.PENDING);
        System.out.println("L6 | STATE | Z uwagi na zmianę rezerwacji, zmieniono jej status z APPROVED na PENDING.");
    }
}
//###   end L6 State

