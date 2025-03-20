package com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Reservation;

import com.example.projback.dto.MakeReservationDTO;

//###   start L8 Segregacja interfejsów
public interface IReservationService_Creating {
    void createReservationByMediator(MakeReservationDTO reservationDTO, String token);
    void createReservation(MakeReservationDTO reservation, String token);
}
//###   end L8 Segregacja interfejsów