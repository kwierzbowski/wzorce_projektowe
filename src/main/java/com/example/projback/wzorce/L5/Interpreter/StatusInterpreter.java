package com.example.projback.wzorce.L5.Interpreter;

import com.example.projback.entity.Reservation;
import com.example.projback.entity.ReservationStatus;
import com.example.projback.repository.ReservationRepository;

import java.util.List;

//###   start L5 Interpreter -> Status
public class StatusInterpreter implements  Expression{
    private final ReservationRepository reservationRepository;
    private final ReservationStatus status;

    public StatusInterpreter(ReservationRepository reservationRepository, ReservationStatus status) {
        this.reservationRepository = reservationRepository;
        this.status = status;
    }

    @Override
    public List<Reservation> interpret() {
        return (status != null) ? reservationRepository.findByStatus(status) : reservationRepository.findAll();
    }
}
//###   end L5 Interpreter -> Status