//###   start L6 VISITOR

package com.example.projback.wzorce.L6.Visitor;

import com.example.projback.entity.Reservation;
import org.springframework.stereotype.Component;

@Component
public class StandardPricingVisitor implements PricingVisitor {
    @Override
    public void visit(Reservation reservation) {
        System.out.println("L6 | VISITOR | Sezon wiosna/jesień! Cena pozostaje bez zmian: " + reservation.getEstimatedPrice() + " PLN");
    }
}

//###   end L6 VISITOR