package com.example.projback.wzorce.L6.Template;

import com.example.projback.entity.Reservation;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.time.Instant;

//###   start L6 Template
public abstract class CancellationTemplate {

//###   start L9, 2. (part 1) dostosuj długości metod w programie, żeby nie miały więcej niż 20 linii
//    public final void cancelReservation(Reservation reservation) {
//        if (reservation == null) {
//            throw new IllegalArgumentException("Rezerwacja nie może być null");
//        }
//        boolean isCancelable = canBeCancelled(reservation);
//        boolean hasRefund = reservation.getFinalPrice() != null && reservation.getFinalPrice() > 0;
//        if (!isCancelable) {
//            throw new IllegalStateException("Nie można anulować");
//            return;
//        }
//        if (reservation.getEstimatedPrice() == null) {
//            throw new IllegalStateException("Brakuje ceny estymowanej");
//        }
//        if (reservation.getStartTime() == null || reservation.getEndTime() == null) {
//            throw new IllegalStateException("Niekompletna data rezerwacji");
//        }
//        if (reservation.getUserId() == null || reservation.getRoomId() == null) {
//            throw new IllegalStateException("Brak przypisanego użytkownika lub pokoju");
//        }
//        double cancellationBase = reservation.getEstimatedPrice();
//        double fee = hasRefund ? cancellationBase * 0.5 : 0;
//        applyCancellationPolicy(reservation);
//        processRefundOrCharge(reservation);
//        notifyUser(reservation);
//        saveCancellation(reservation);
//    }

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

//###   end L9, 2. (part 1) dostosuj długości metod w programie, żeby nie miały więcej niż 20 linii

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
//###   end L6 Template
