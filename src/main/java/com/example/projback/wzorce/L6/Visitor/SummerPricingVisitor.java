package com.example.projback.wzorce.L6.Visitor;

import com.example.projback.entity.Reservation;
import org.springframework.stereotype.Component;

//###   start L6 VISITOR
@Component
public class SummerPricingVisitor implements PricingVisitor {
    // start L9, 8. (part 3) (wyeliminuj magiczne liczby)
    private static final double SUMMER_PRICE_MULTIPLIER = 1.2;
    // end L9, 8. (part 3) (wyeliminuj magiczne liczby)

    @Override
    public void visit(Reservation reservation) {

        // start L9, 8. (part 3) (wyeliminuj magiczne liczby)
        //double newPrice = reservation.getEstimatedPrice() * 1.2;
        double newPrice = reservation.getEstimatedPrice() * SUMMER_PRICE_MULTIPLIER;
        // end L9, 8. (part 3) (wyeliminuj magiczne liczby)

        reservation.setEstimatedPrice(newPrice);
        System.out.println("L6 | VISITOR | Sezon letni! Nowa cena: " + newPrice + " PLN");
    }
}
//###   end L6 VISITOR