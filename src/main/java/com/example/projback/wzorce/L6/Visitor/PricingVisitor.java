package com.example.projback.wzorce.L6.Visitor;

import com.example.projback.entity.Reservation;

//###   start L6 Visitor (part 1)
public interface PricingVisitor {
    void visit(Reservation reservation);
}
//###   end L6 Visitor (part 2)