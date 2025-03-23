package com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Reservation;

import com.example.projback.dto.FilterReservationDTO;
import com.example.projback.entity.Reservation;
import com.example.projback.entity.ReservationStatus;

import java.util.Date;
import java.util.List;

//###   start L8 Segregacja interfejsów
public interface IReservationService_Query {
    List<Reservation> getReservationsByStatusOrAll(ReservationStatus status, String token);
    List<Reservation> getReservationsByUser(String token);
    Reservation getCustomerReservationById(Long reservationId, String token);
    Reservation getEmployeeReservationById(Long reservationId, String token);
    List<Reservation> getReservationsByRoomId(Long roomId, String token);

    Reservation getReservationById(Long reservationId, String token);

    List<Reservation> getFilteredReservations(FilterReservationDTO filterReservationDTO);
    void printAllReservations();
}
//###   end L8 Segregacja interfejsów