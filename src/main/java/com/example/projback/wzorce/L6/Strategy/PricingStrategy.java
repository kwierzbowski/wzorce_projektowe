package com.example.projback.wzorce.L6.Strategy;

import com.example.projback.entity.Reservation;
import com.example.projback.entity.Room;

//###   start L6 STRATEGY
public interface PricingStrategy {
    void applyPricing(Reservation reservation, Room room);
}
//###   end L6 STRATEGY