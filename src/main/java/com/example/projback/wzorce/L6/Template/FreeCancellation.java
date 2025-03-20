//###   start L6 TEMPLATE

package com.example.projback.wzorce.L6.Template;

import com.example.projback.entity.Reservation;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
public class FreeCancellation extends CancellationTemplate {

    @Override
    protected boolean canBeCancelled(Reservation reservation) {
        return true; // Każda rezerwacja może być anulowana
    }

    @Override
    protected void applyCancellationPolicy(Reservation reservation) {
        System.out.println("L6 | TEMPLATE | Rezerwacja ID " + reservation.getId() + " została anulowana BEZPŁATNIE.");
    }

    @Override
    protected void processRefundOrCharge(Reservation reservation) {
        System.out.println("L6 | TEMPLATE | Zwrot pełnej kwoty dla rezerwacji ID " + reservation.getId());
    }
}

//###   end L6 TEMPLATE