package com.example.projback.wzorce.L5.Interpreter;

import com.example.projback.entity.Reservation;
import com.example.projback.repository.ReservationRepository;

import java.util.List;

//###   start L5 Interpreter -> User
public class UserInterpreter implements Expression{
    private final ReservationRepository reservationRepository;
    private final Long userId;

    public UserInterpreter(ReservationRepository reservationRepository, Long userId) {
        this.reservationRepository = reservationRepository;
        this.userId = userId;
    }

    @Override
    public List<Reservation> interpret() {
        return reservationRepository.findByUserId(userId);
    }
}
//###   end L5 Interpreter -> User