package com.example.projback.wzorce.L6.Strategy;

import com.example.projback.entity.Reservation;
import com.example.projback.entity.Room;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

////###   start L6 Strategy (part 3)
////###   start L9, 7. (part 1) dostosuj kod tak, by sie nie powtarzal

//@Component
//public class HourlyPricingStrategy implements PricingStrategy {
//    @Override
//    public void applyPricing(Reservation reservation, Room room) {
//
////#####   start L9, 6. (part 1) dodaj zwracanie wyjątków zamiast kodów błędów (3 wystąpienia)
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
////#####   end L9, 6. (part 1) dodaj zwracanie wyjątków zamiast kodów błędów (3 wystąpienia)
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

//###   start L9, 2. (part 3) dostosuj długości metod w programie, żeby nie miały więcej niż 20 linii
//    @Override
//    protected long calculateTimeUnits(Reservation reservation) {
//        if (reservation == null) {
//            throw new IllegalArgumentException("Reservation is null");
//        }
//        Date startDate = reservation.getStartTime();
//        Date endDate = reservation.getEndTime();
//        if (startDate == null || endDate == null) {
//            throw new IllegalArgumentException("Start or end time is missing");
//        }
//        Instant startInstant = startDate.toInstant();
//        Instant endInstant = endDate.toInstant();
//        Duration durationBetween = Duration.between(startInstant, endInstant);
//        long totalMinutes = durationBetween.toMinutes();
//        double hoursExact = totalMinutes / 60.0;
//        long roundedHours = (long) Math.ceil(hoursExact);
//        if (roundedHours == 0) {
//            roundedHours = 1;
//        }
//        long verifiedResult = roundedHours;
//        if (verifiedResult < 0) {
//            verifiedResult = 1;
//        }
//        return verifiedResult;
//    }

    @Override
    protected long calculateTimeUnits(Reservation reservation) {
        long hours = (long) Math.ceil(Duration.between(
                reservation.getStartTime().toInstant(),
                reservation.getEndTime().toInstant()
        ).toMinutes() / 60.0);
        return (hours == 0) ? 1 : hours;
    }
//###   start L9, 2. (part 3) dostosuj długości metod w programie, żeby nie miały więcej niż 20 linii

    @Override
    protected double calculatePrice(Room room, long timeUnits) {
        return timeUnits * room.getPricePerHour();
    }

    @Override
    protected double getUnitPrice(Room room) {
        return room.getPricePerHour();
    }
}
////###   end L6  (part 3)
////###   end L9, 7. (part 1) dostosuj kod tak, by sie nie powtarzal