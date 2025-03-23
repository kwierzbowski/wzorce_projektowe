package com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Reservation;

import com.example.projback.dto.UpdateReservationDTO;
import com.example.projback.dto.UpdateReservationEmployeeDTO;
import com.example.projback.dto.UpdateReservationRequestDTO;
import com.example.projback.entity.Reservation;

//###   start L8 Segregacja interfejsów
public interface IReservationService_Manipulating {
    void deleteReservation(Long reservationId, String token);
    void updateReservation(UpdateReservationRequestDTO updateReservation);
    void updateReservationStatusAndPrice(Long reservationId, UpdateReservationEmployeeDTO updateReservation, String token);
    void updateReservationFields(Reservation reservation, UpdateReservationDTO updateReservation);
    Reservation undoLastReservationEdit();

}
//###   end L8 Segregacja interfejsów
