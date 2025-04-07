package com.example.projback.wzorce.L6.Template;

import com.example.projback.entity.Reservation;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.time.Instant;

//###   start L6 TEMPLATE
@Component
public class PaidCancellation extends CancellationTemplate {
    // start L9, 8. (part 2) (wyeliminuj magiczne liczby)
    private static final double CANCELLATION_FEE_PERCENTAGE = 50.0;
    // end L9, 8. (part 2) (wyeliminuj magiczne liczby)

    @Override
    protected boolean canBeCancelled(Reservation reservation) {
        return true;
    }

    @Override
    protected void applyCancellationPolicy(Reservation reservation) {
        System.out.println("L6 | TEMPLATE | Rezerwacja ID " + reservation.getId() + " została anulowana PŁATNIE.");
    }

    @Override
    protected void processRefundOrCharge(Reservation reservation) {
        // start L9, 8. (part 2) (wyeliminuj magiczne liczby)
        //double cancellationFee = reservation.getEstimatedPrice() * 0.5;
        double cancellationFee = reservation.getEstimatedPrice() * CANCELLATION_FEE_PERCENTAGE;
        // end L9, 8. (part 2) (wyeliminuj magiczne liczby)

        System.out.println("L6 | TEMPLATE | Użytkownik musi zapłacić opłatę za anulowanie: " + cancellationFee + " PLN.");
    }
}
//###   end L6 TEMPLATE