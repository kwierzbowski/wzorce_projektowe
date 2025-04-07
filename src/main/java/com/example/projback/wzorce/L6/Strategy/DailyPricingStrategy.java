//###   start L6 STRATEGY
package com.example.projback.wzorce.L6.Strategy;

import com.example.projback.entity.Reservation;
import com.example.projback.entity.Room;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class DailyPricingStrategy implements PricingStrategy {

    @Override
    public long applyPricing(Reservation reservation, Room room) {
        if (room == null || reservation == null) {
            System.out.println("Błąd: Room lub Reservation jest null!");
            return 0;
        }
        long days = Duration.between(reservation.getStartTime().toInstant(), reservation.getEndTime().toInstant()).toDays();
        return (days == 0) ? 1 : days;
    }

    protected double calculatePrice(Room room, long timeUnits) {
        return timeUnits * room.getPricePerDay();
    }

    protected double getUnitPrice(Room room) {
        return room.getPricePerDay();
    }
}
//###   end L6 STRATEGY