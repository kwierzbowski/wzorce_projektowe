package com.example.projback.wzorce.L8.OdwracanieZaleznosci;

import com.example.projback.config.JwtUtil;
import com.example.projback.dto.MakeReservationDTO;
import com.example.projback.dto.UpdateReservationDTO;
import com.example.projback.dto.UpdateReservationEmployeeDTO;
import com.example.projback.entity.Reservation;
import com.example.projback.entity.ReservationStatus;
import com.example.projback.repository.ReservationRepository;
import com.example.projback.service.UserService;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Reservation.IReservationService_Creating;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Reservation.IReservationService_Manipulating;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Reservation.IReservationService_Query;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Reservation.IReservationService_Validator;

import java.util.Date;
import java.util.List;

//###   start L2 Bridge -> Klasa abstrakcyjna
//###   start L8 Odwrocenie zaleznosci -> klasa abstrakcyjna
public abstract class AbstractReservationService_Manipulating implements IReservationService_Manipulating {
    protected final ReservationRepository reservationRepository;
    protected final JwtUtil jwtUtil;
    protected final UserService userService;

    protected AbstractReservationService_Manipulating(ReservationRepository reservationRepository, JwtUtil jwtUtil, UserService userService) {
        this.reservationRepository = reservationRepository;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    public abstract void createReservation(MakeReservationDTO reservation, String token);

    public abstract void deleteReservation(Long reservationId, String token);

    public abstract void updateReservation(Long reservationId, UpdateReservationDTO updateReservation, String token);

    public abstract void updateReservationStatusAndPrice(Long reservationId, UpdateReservationEmployeeDTO updateReservation, String token);

    public abstract void updateReservationFields(Reservation reservation, UpdateReservationDTO updateReservation);

    public abstract Reservation undoLastReservationEdit();

}
//###   end L8 Odwrocenie zaleznosci -> klasa abstrakcyjna
//###   end L2 Bridge -> Klasa abstrakcyjna