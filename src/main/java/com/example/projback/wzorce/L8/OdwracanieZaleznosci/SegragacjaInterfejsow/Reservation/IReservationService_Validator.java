package com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Reservation;

import com.example.projback.entity.Reservation;

//###   start L8 Segregacja interfejsów
public interface IReservationService_Validator {
    Reservation validateReservation(String token, Long reservationId);

}
//###   end L8 Segregacja interfejsów