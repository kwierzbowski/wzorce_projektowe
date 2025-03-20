package com.example.projback.wzorce.L2.Bridge;

import com.example.projback.dto.MakeReservationDTO;

public abstract class ReservationServiceBridge {
    protected ReservationValidator validator;

    public ReservationServiceBridge(ReservationValidator validator) {
        this.validator = validator;
    }

    public abstract void createReservation(MakeReservationDTO reservation, String token);
}