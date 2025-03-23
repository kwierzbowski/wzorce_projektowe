package com.example.projback.wzorce.L6.Strategy;

import com.example.projback.entity.Reservation;
import com.example.projback.entity.Room;

////#####   start L9, 7. dostosuj kod tak, by sie nie powtarzal
////###   start L6 Strategy

//public interface PricingStrategy {
//    void applyPricing(Reservation reservation, Room room);
//}

public abstract class PricingStrategy {

    public void applyPricing(Reservation reservation, Room room) {
        validateInputs(reservation, room);
        long timeUnits = calculateTimeUnits(reservation);
        double totalPrice = calculatePrice(room, timeUnits);
        reservation.setEstimatedPrice(totalPrice);
        logPricing(reservation, room, timeUnits, totalPrice);
    }

    private void validateInputs(Reservation reservation, Room room) {
        if (reservation == null) {
            throw new IllegalArgumentException("Rezerwacja jest null!");
        }
        if (room == null) {
            throw new IllegalArgumentException("Pokój dla tej rezerwacji nie istnieje!");
        }
    }

    protected abstract long calculateTimeUnits(Reservation reservation);

    protected abstract double calculatePrice(Room room, long timeUnits);

    protected abstract double getUnitPrice(Room room);

    private void logPricing(Reservation reservation, Room room, long timeUnits, double totalPrice) {
        System.out.println("L6 | STRATEGY | " + this.getClass().getSimpleName() +
                ": jednostki czasu = " + timeUnits +
                ", cena za jednostkę = " + getUnitPrice(room) +
                ", final_price = " + totalPrice);
    }
}
////###   end L6 Strategy
////###   end L9, 7. dostosuj kod tak, by sie nie powtarzal