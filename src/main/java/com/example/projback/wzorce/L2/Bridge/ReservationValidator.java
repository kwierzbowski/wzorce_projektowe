package com.example.projback.wzorce.L2.Bridge;

import com.example.projback.dto.MakeReservationDTO;


public interface ReservationValidator {
    boolean validate(MakeReservationDTO reservation);
}
