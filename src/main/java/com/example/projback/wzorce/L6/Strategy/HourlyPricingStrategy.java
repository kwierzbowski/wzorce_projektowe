package com.example.projback.wzorce.L6.Strategy;

import com.example.projback.entity.Reservation;
import com.example.projback.entity.Room;
import org.springframework.stereotype.Component;
import java.time.Duration;

////###   start L6 Strategy
////###   start L9, 7. dostosuj kod tak, by sie nie powtarzal

//@Component
//public class HourlyPricingStrategy implements PricingStrategy {
//    @Override
//    public void applyPricing(Reservation reservation, Room room) {
//
////#####   start L9, 6. dodaj zwracanie wyjątków zamiast kodów błędów (3 wystąpienia)
////        if (room == null || reservation == null) {
////            System.out.println("Błąd: Room lub Reservation jest null!");
////            return;
////        }
//        if (reservation == null) {
//            throw new IllegalArgumentException("Rezerwacja jest null!");
//        }
//        if (room == null) {
//            throw new IllegalArgumentException("Pokój dla tej rezerwacji nie istnieje!");
//        }
////#####   end L9, 6. dodaj zwracanie wyjątków zamiast kodów błędów (3 wystąpienia)
//
//        long hours = (long) Math.ceil(Duration.between(reservation.getStartTime().toInstant(), reservation.getEndTime().toInstant()).toMinutes() / 60.0);
//
//        double totalPrice = hours * room.getPricePerHour();
//        reservation.setEstimatedPrice(totalPrice);
//
//        System.out.println("L6 | STRATEGY | Użyto HourlyPricingStrategy: " +
//                "liczba godzin = " + hours +
//                ", cena za godzinę = " + room.getPricePerHour() +
//                ", final_price = " + totalPrice);
//    }
//}

@Component
public class HourlyPricingStrategy extends PricingStrategy {

    @Override
    protected long calculateTimeUnits(Reservation reservation) {
        long hours = (long) Math.ceil(Duration.between(
                reservation.getStartTime().toInstant(),
                reservation.getEndTime().toInstant()
        ).toMinutes() / 60.0);
        return (hours == 0) ? 1 : hours;
    }

    @Override
    protected double calculatePrice(Room room, long timeUnits) {
        return timeUnits * room.getPricePerHour();
    }

    @Override
    protected double getUnitPrice(Room room) {
        return room.getPricePerHour();
    }
}
////###   end L6 Strategy
////###   end L9, 7. dostosuj kod tak, by sie nie powtarzal