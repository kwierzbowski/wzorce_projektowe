package com.example.projback.wzorce.L6.Strategy;

import com.example.projback.entity.Reservation;
import com.example.projback.entity.Room;

////#####   start L9, 7. (part 3) dostosuj kod tak, by sie nie powtarzal
////###   start L6 Strategy (part 1)

//public interface PricingStrategy {
//    void applyPricing(Reservation reservation, Room room);
//}

public abstract class PricingStrategy {

//###   start L9, 2. (part 2) dostosuj długości metod w programie, żeby nie miały więcej niż 20 linii
//    public void applyPricing(Reservation reservation, Room room) {
//        validateInputs(reservation, room);
//        if (reservation.getStartTime() == null || reservation.getEndTime() == null) {
//            throw new IllegalStateException("Rezerwacja nie zawiera daty rozpoczęcia lub zakończenia.");
//        }
//        if (reservation.getStartTime().isAfter(reservation.getEndTime())) {
//            throw new IllegalArgumentException("Data rozpoczęcia rezerwacji jest po dacie zakończenia.");
//        }
//        long timeUnits = calculateTimeUnits(reservation);
//        if (timeUnits <= 0) {
//            throw new IllegalArgumentException("Nieprawidłowy czas trwania rezerwacji.");
//        }
//        double basePrice = calculatePrice(room, timeUnits);
//        double discount = 0.0;
//        if (timeUnits >= 8) {
//            discount = 0.1;
//        }
//        double discountedPrice = basePrice * (1 - discount);
//        if (discountedPrice < 50.0) {
//            discountedPrice = 50.0;
//        }
//        double finalPrice = Math.ceil(discountedPrice);
//        reservation.setEstimatedPrice(finalPrice);
//        logPricing(reservation, room, timeUnits, finalPrice);
//    }

    public void applyPricing(Reservation reservation, Room room) {
        validateInputs(reservation, room);
        long timeUnits = calculateTimeUnits(reservation);
        double totalPrice = calculatePrice(room, timeUnits);
        reservation.setEstimatedPrice(totalPrice);
        logPricing(reservation, room, timeUnits, totalPrice);
    }
//###   end L9, 2. (part 2) dostosuj długości metod w programie, żeby nie miały więcej niż 20 linii

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
////###   end L6 Strategy (part 1)
////###   end L9, 7. (part 3) dostosuj kod tak, by sie nie powtarzal