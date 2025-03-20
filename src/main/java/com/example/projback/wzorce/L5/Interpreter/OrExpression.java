package com.example.projback.wzorce.L5.Interpreter;

import com.example.projback.entity.Reservation;

import java.util.List;
import java.util.HashSet;

//###   start L5 Interpreter -> Or Expression
public class OrExpression implements Expression {
    private final Expression expr1;
    private final Expression expr2;

    public OrExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public List<Reservation> interpret() {
        List<Reservation> result1 = expr1.interpret();
        List<Reservation> result2 = expr2.interpret();

        HashSet<Reservation> combinedResults = new HashSet<>(result1);
        combinedResults.addAll(result2);

        List<Reservation> finalResult = combinedResults.stream().toList();
        return finalResult;
    }
}
//###   end L5 Interpreter -> Or Expression