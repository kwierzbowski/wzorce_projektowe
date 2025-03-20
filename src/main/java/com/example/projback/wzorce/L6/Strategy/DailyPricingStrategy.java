//###   start L6 STRATEGY
package com.example.projback.wzorce.L6.Strategy;

import com.example.projback.entity.Reservation;
import com.example.projback.entity.Room;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class DailyPricingStrategy implements PricingStrategy {
    @Override
    public void applyPricing(Reservation reservation, Room room) {
        if (room == null || reservation == null) {
            System.out.println("Błąd: Room lub Reservation jest null!");
            return;
        }
        long days = Duration.between(reservation.getStartTime().toInstant(), reservation.getEndTime().toInstant()).toDays();
        if (days == 0) {
            days = 1;
        }

        double totalPrice = days * room.getPricePerDay();
        reservation.setEstimatedPrice(totalPrice);

        System.out.println("L6 | STRATEGY | Użyto DailyPricingStrategy: " +
                "liczba dni = " + days +
                ", cena za dzień = " + room.getPricePerDay() +
                ", final_price = " + totalPrice);
    }
}
//###   end L6 STRATEGY