package com.example.projback.wzorce.L5.Command;

import com.example.projback.entity.Equipment;
import com.example.projback.entity.Reservation;
import com.example.projback.repository.ReservationRepository;

//###   start L5 Command -> Reservation
public class Reservation_Create_Command implements Command {
    private Reservation reservation;
    private ReservationRepository reservationRepository;

    public Reservation_Create_Command(Reservation reservation, ReservationRepository repository) {
        this.reservation = reservation;
        this.reservationRepository = repository;
    }

    @Override
    public Equipment execute() {
        reservationRepository.save(reservation);
        return null;
    }
}
//###   end L5 Command -> Reservation