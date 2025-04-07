//###   start L6 STRATEGY
package com.example.projback.wzorce.L6.Strategy;

import com.example.projback.entity.Reservation;
import com.example.projback.entity.Room;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class DailyPricingStrategy extends PricingStrategy {

    @Override
    public void applyPricing(Reservation reservation, Room room) {
        if (room == null || reservation == null) {
            System.out.println("Błąd: Room lub Reservation jest null!");
            return;
        }
        long days = Duration.between(reservation.getStartTime().toInstant(), reservation.getEndTime().toInstant()).toDays();
        return (days == 0) ? 1 : days;
    }

    @Override
    protected double calculatePrice(Room room, long timeUnits) {
        return timeUnits * room.getPricePerDay();
    }

    @Override
    protected double getUnitPrice(Room room) {
        return room.getPricePerDay();
    }
}
//###   end L6 STRATEGY