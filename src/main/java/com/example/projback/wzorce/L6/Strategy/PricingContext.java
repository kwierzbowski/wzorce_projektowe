//###   start L6 STRATEGY
package com.example.projback.wzorce.L6.Strategy;

import org.springframework.stereotype.Component;

@Component
public class PricingContext {
    private PricingStrategy strategy;

    public void setStrategy(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy(com.example.projback.entity.Reservation reservation, com.example.projback.entity.Room room) {
        if (strategy == null) {
            throw new IllegalStateException("L6 | STRATEGY | Strategia cenowa nie została ustawiona!");
        }

        strategy.applyPricing(reservation, room);
    }
}
//###   end L6 STRATEGY