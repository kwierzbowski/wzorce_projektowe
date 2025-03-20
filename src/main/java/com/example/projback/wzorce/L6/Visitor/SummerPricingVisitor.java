//###   start L6 VISITOR

package com.example.projback.wzorce.L6.Visitor;

import com.example.projback.entity.Reservation;
import org.springframework.stereotype.Component;

@Component
public class SummerPricingVisitor implements PricingVisitor {
    @Override
    public void visit(Reservation reservation) {
        double newPrice = reservation.getEstimatedPrice() * 1.2; // +20%
        reservation.setEstimatedPrice(newPrice);
        System.out.println("L6 | VISITOR | Sezon letni! Nowa cena: " + newPrice + " PLN");
    }
}

//###   end L6 VISITOR