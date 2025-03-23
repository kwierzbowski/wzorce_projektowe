package com.example.projback.wzorce.L8.OdwracanieZaleznosci;

import com.example.projback.config.JwtUtil;
import com.example.projback.dto.FilterReservationDTO;
import com.example.projback.dto.MakeReservationDTO;
import com.example.projback.entity.Reservation;
import com.example.projback.entity.ReservationStatus;
import com.example.projback.repository.ReservationRepository;
import com.example.projback.service.UserService;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Reservation.IReservationService_Query;

import java.util.Date;
import java.util.List;

//###   start L2 Bridge -> Klasa abstrakcyjna
//###   start L8 Odwrocenie zaleznosci -> klasa abstrakcyjna
public abstract class AbstractReservationService_Query implements IReservationService_Query {
    protected final ReservationRepository reservationRepository;
    protected final JwtUtil jwtUtil;
    protected final UserService userService;

    protected AbstractReservationService_Query(ReservationRepository reservationRepository, JwtUtil jwtUtil, UserService userService) {
        this.reservationRepository = reservationRepository;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    public abstract void createReservation(MakeReservationDTO reservation, String token);

    public abstract List<Reservation> getReservationsByStatusOrAll(ReservationStatus status, String token);

    public abstract List<Reservation> getReservationsByUser(String token);

    public abstract Reservation getCustomerReservationById(Long reservationId, String token);

    public abstract Reservation getEmployeeReservationById(Long reservationId, String token);

    public abstract List<Reservation> getReservationsByRoomId(Long roomId, String token);

    public abstract List<Reservation> getFilteredReservations(FilterReservationDTO filterReservationDTO);

    public abstract void printAllReservations();
}
//###   end L8 Odwrocenie zaleznosci -> klasa abstrakcyjna
//###   end L2 Bridge -> Klasa abstrakcyjna