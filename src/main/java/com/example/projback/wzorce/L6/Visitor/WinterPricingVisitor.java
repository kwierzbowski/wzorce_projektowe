package com.example.projback.wzorce.L6.Visitor;

import com.example.projback.entity.Reservation;
import org.springframework.stereotype.Component;

//###   start L6 VISITOR
@Component
public class WinterPricingVisitor implements PricingVisitor {
    // start L9, 8. (wyeliminuj magiczne liczby)
    private static final double WINTER_PRICE_MULTIPLIER = 0.9;
    // end L9, 8. (wyeliminuj magiczne liczby)

    @Override
    public void visit(Reservation reservation) {

        // start L9, 8. (wyeliminuj magiczne liczby)
        //double newPrice = reservation.getEstimatedPrice() * 0.9;
        double newPrice = reservation.getEstimatedPrice() * WINTER_PRICE_MULTIPLIER;
        // end L9, 8. (wyeliminuj magiczne liczby)

        reservation.setEstimatedPrice(newPrice);
        System.out.println("L6 | VISITOR | Sezon zimowy! Nowa cena: " + newPrice + " PLN");
    }
}
//###   end L6 VISITOR