package com.example.projback.wzorce.L6.Strategy;

import com.example.projback.entity.Reservation;
import com.example.projback.entity.Room;
import org.springframework.stereotype.Component;

////###   start L6 Strategy (part 4)
@Component
public class PricingContext {

    private PricingStrategy strategy;

    public void setStrategy(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy(Reservation reservation, Room room) {
        if (strategy == null) {
            throw new IllegalStateException("L6 | STRATEGY | Strategia cenowa nie została ustawiona!");
        }
        strategy.applyPricing(reservation, room);
    }
}
////###   end L6 Strategy (part 4)