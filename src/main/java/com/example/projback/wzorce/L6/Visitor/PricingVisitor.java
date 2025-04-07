//###   start L6 VISITOR

package com.example.projback.wzorce.L6.Visitor;

import com.example.projback.entity.Reservation;

public interface PricingVisitor {
    void visit(Reservation reservation);
}

//###   end L6 VISITOR