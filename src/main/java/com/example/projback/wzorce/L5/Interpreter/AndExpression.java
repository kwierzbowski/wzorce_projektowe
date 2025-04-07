package com.example.projback.wzorce.L5.Interpreter;

import com.example.projback.entity.Reservation;

import java.util.List;

//###   start L5 Interpreter -> And Expression
public class AndExpression implements Expression {
    private Expression expr1;
    private Expression expr2;

    public AndExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public List<Reservation> interpret() {
        System.out.println("INTERPRET");

        List<Reservation> result1 = expr1.interpret();
        List<Reservation> result2 = expr2.interpret();

        result1.retainAll(result2);

        return result1;
    }

}
//###   end L5 Interpreter -> And Expression