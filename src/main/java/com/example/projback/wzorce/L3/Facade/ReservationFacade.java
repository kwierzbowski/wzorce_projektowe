package com.example.projback.wzorce.L3.Facade;

import com.example.projback.dto.MakeReservationDTO;
import com.example.projback.dto.UpdateReservationDTO;
import com.example.projback.dto.UpdateReservationEmployeeDTO;
import com.example.projback.entity.Equipment;
import com.example.projback.entity.Reservation;
import com.example.projback.entity.ReservationStatus;
import com.example.projback.wzorce.L3.Proxy.ReservationServiceProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
//###   start L3 Facade -> Part 2
@Service
public class ReservationFacade {
    /// ### start L3 Proxy -> Part 2, changing ReservationFacade by adding ReservationServiceProxy instead ReservationService
    private final ReservationServiceProxy reservationServiceProxy;

    public ReservationFacade(ReservationServiceProxy reservationServiceProxy) {
        this.reservationServiceProxy = reservationServiceProxy;
    }

    public ResponseEntity<String> createReservation(MakeReservationDTO reservation, String token) {
        reservationServiceProxy.createReservation(reservation, token);
        return ResponseEntity.ok("Reservation created");
    }

    public ResponseEntity<String> deleteReservation(Long reservationId, String token) {
        reservationServiceProxy.deleteReservation(reservationId, token);
        return ResponseEntity.ok("Reservation deleted");
    }

    public ResponseEntity<String> updateReservation(Long reservationId, UpdateReservationDTO updateReservation, String token) {
        reservationServiceProxy.updateReservation(reservationId, updateReservation, token);
        return ResponseEntity.ok("Reservation updated");
    }

    public ResponseEntity<String> updateReservationStatusAndPrice(Long reservationId, UpdateReservationEmployeeDTO updateReservation, String token) {
        reservationServiceProxy.updateReservationStatusAndPrice(reservationId, updateReservation, token);
        return ResponseEntity.ok("Reservation status and price updated");
    }

    public ResponseEntity<List<Reservation>> getReservationsByUser(String token) {
        return ResponseEntity.ok(reservationServiceProxy.getReservationsByUser(token));
    }

    public ResponseEntity<List<Reservation>> getReservations(ReservationStatus status, String token) {
        return ResponseEntity.ok(reservationServiceProxy.getReservationsByStatusOrAll(status, token));
    }

    public ResponseEntity<Reservation> getReservationById(Long id, String token) {
        return ResponseEntity.ok(reservationServiceProxy.getReservationById(id, token));
    }

    public ResponseEntity<Reservation> getEmployeeReservationById(Long id, String token) {
        return ResponseEntity.ok(reservationServiceProxy.getEmployeeReservationById(id, token));
    }

    public ResponseEntity<List<Reservation>> getReservationsByRoomId(Long roomId, String token) {
        return ResponseEntity.ok(reservationServiceProxy.getReservationsByRoomId(roomId, token));
    }



    public Reservation undoLastReservationEdit() {
        return reservationServiceProxy.undoLastReservationEdit();
    }

    public void createReservationByMediator(MakeReservationDTO reservation, String token) {
        reservationServiceProxy.createReservationByMediator(reservation, token);
    }

    public List<Reservation> getFilteredReservations(Long userId, ReservationStatus status, Date startDate, Date endDate) {
        return reservationServiceProxy.getFilteredReservations(userId, status, startDate, endDate);
    }
}
//###   end L3 Facade -> Part 2