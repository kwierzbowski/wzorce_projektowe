//###   start L6 STRATEGY
package com.example.projback.wzorce.L6.Strategy;

import com.example.projback.entity.Reservation;
import com.example.projback.entity.Room;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class HourlyPricingStrategy implements PricingStrategy {
    @Override
    public void applyPricing(Reservation reservation, Room room) {
        if (room == null || reservation == null) {
            System.out.println("Błąd: Room lub Reservation jest null!");
            return;
        }

        long hours = (long) Math.ceil(Duration.between(reservation.getStartTime().toInstant(), reservation.getEndTime().toInstant()).toMinutes() / 60.0);

        double totalPrice = hours * room.getPricePerHour();
        reservation.setEstimatedPrice(totalPrice);

        System.out.println("L6 | STRATEGY | Użyto HourlyPricingStrategy: " +
                "liczba godzin = " + hours +
                ", cena za godzinę = " + room.getPricePerHour() +
                ", final_price = " + totalPrice);
    }
}
//###   end L6 STRATEGY