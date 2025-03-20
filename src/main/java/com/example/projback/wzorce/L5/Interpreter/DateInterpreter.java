package com.example.projback.wzorce.L5.Interpreter;

import com.example.projback.entity.Reservation;
import com.example.projback.repository.ReservationRepository;

import java.util.Date;
import java.util.List;

//###   start L5 Interpreter -> Date
public class DateInterpreter implements Expression{
    private final ReservationRepository reservationRepository;
    private final Date startDate;
    private final Date endDate;

    public DateInterpreter(ReservationRepository reservationRepository, Date startDate, Date endDate) {
        this.reservationRepository = reservationRepository;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public List<Reservation> interpret() {
        System.out.println("Wywołano AndExpression.interpret()");
        return reservationRepository.findAll().stream()
                .filter(reservation -> !reservation.getStartTime().before(startDate) && !reservation.getEndTime().after(endDate))
                .toList();
    }
}
//###   end L5 Interpreter -> Date