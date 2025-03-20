package com.example.projback.wzorce.L5.Interpreter;

import com.example.projback.entity.Reservation;

import java.util.List;
//###   start L5 Interpreter -> Expression
public interface Expression {
    List<Reservation> interpret();
}
//###   end L5 Interpreter -> Expression