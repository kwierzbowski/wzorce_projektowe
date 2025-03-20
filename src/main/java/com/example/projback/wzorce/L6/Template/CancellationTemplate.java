//###   start L6 TEMPLATE

package com.example.projback.wzorce.L6.Template;

import com.example.projback.entity.Reservation;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

public abstract class CancellationTemplate {

    public final void cancelReservation(Reservation reservation) {
        if (!canBeCancelled(reservation)) {
            System.out.println("L6 | TEMPLATE | Rezerwacja ID " + reservation.getId() + " już trwa – nie można jej anulować!");
            return;
        }

        applyCancellationPolicy(reservation);
        processRefundOrCharge(reservation);
        notifyUser(reservation);
        saveCancellation(reservation);
    }

    protected boolean canBeCancelled(Reservation reservation) {
        return Instant.now().isBefore(reservation.getStartTime().toInstant());
    }

    protected abstract void applyCancellationPolicy(Reservation reservation);
    protected abstract void processRefundOrCharge(Reservation reservation);

    protected void notifyUser(Reservation reservation) {
        System.out.println("L6 | TEMPLATE | Powiadomienie: Rezerwacja ID " + reservation.getId() + " została anulowana.");
    }

    protected void saveCancellation(Reservation reservation) {
        System.out.println("L6 | TEMPLATE | Zmiana statusu na CANCELED w bazie dla rezerwacji ID " + reservation.getId());
    }
}

//###   end L6 TEMPLATE
