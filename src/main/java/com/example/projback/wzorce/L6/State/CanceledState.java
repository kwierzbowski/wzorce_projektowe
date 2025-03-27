package com.example.projback.wzorce.L6.State;

import com.example.projback.entity.Reservation;
import org.springframework.stereotype.Component;

//###   start L6 State (part 3)
@Component
public class CanceledState implements ReservationState {
    @Override
    public void checkReservationState(Reservation reservation) {

//###   start L9, 6. (part 3) dodaj zwracanie wyjątków zamiast kodów błędów (3 wystąpienia)
//        System.out.println("L6 | STATE | Nie można zmienić anulowanej rezerwacji.");
        throw new IllegalStateException("L6 | STATE | Nie można zmienić anulowanej rezerwacji.");
//###   end L9, 6. (part 3) dodaj zwracanie wyjątków zamiast kodów błędów (3 wystąpienia)
    }
}
//###   end L6 State (part 3)