package com.example.projback.controller;

import com.example.projback.dto.FilterReservationDTO;
import com.example.projback.dto.MakeReservationDTO;
import com.example.projback.dto.UpdateReservationDTO;
import com.example.projback.dto.UpdateReservationEmployeeDTO;
import com.example.projback.entity.*;
//import com.example.projback.wzorce.L8.OdwracanieZaleznosci.IReservationService;
import com.example.projback.wzorce.L3.Facade.ReservationFacade;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Equipment.IEquipmentService_Creating;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Equipment.IEquipmentService_Manipulating;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Equipment.IEquipmentService_Query;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Reservation.IReservationService_Creating;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Reservation.IReservationService_Manipulating;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Reservation.IReservationService_Query;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Reservation.IReservationService_Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

//###   start L3 Facade -> Part 2 - adapting the code to use facade
@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "*")
public class
ReservationController {
//    @Autowired
//    private ReservationService reservationService;

    private final ReservationFacade reservationFacade;

    @Autowired
    public ReservationController(ReservationFacade reservationFacade) {
        this.reservationFacade = reservationFacade;

    }

    @PostMapping("/customer/add")
    public ResponseEntity<String> createReservation(@RequestBody MakeReservationDTO reservation, @RequestHeader("Authorization") String token) {
        return reservationFacade.createReservation(reservation, token);
    }

    @DeleteMapping("/customer/delete/{reservationId}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long reservationId, @RequestHeader("Authorization") String token) {
        return reservationFacade.deleteReservation(reservationId, token);
    }

    @PutMapping("/customer/update/{reservationId}")
    public ResponseEntity<String> updateReservation(
            @PathVariable Long reservationId,
            @RequestBody UpdateReservationDTO updateReservation,
            @RequestHeader("Authorization") String token) {
        return reservationFacade.updateReservation(reservationId, updateReservation, token);
    }

    @GetMapping("/customer/get/{id}")
    public ResponseEntity<?> getCustomerReservationById(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        return reservationFacade.getReservationById(id, token);
    }

    @GetMapping("/customer/getAll")
    public ResponseEntity<List<Reservation>> getReservationsByUser(@RequestHeader("Authorization") String token) {
        return reservationFacade.getReservationsByUser(token);
    }

    @GetMapping("/customer/getReservations/{id}")
    public ResponseEntity<List<Reservation>> getReservationsOnRoom(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        return reservationFacade.getReservationsByRoomId(id, token);
    }

    @PutMapping("/employee/update/{reservationId}")
    public ResponseEntity<String> updateReservationStatusAndPrice(
            @PathVariable Long reservationId,
            @RequestBody UpdateReservationEmployeeDTO updateReservation,
            @RequestHeader("Authorization") String token) {
        return reservationFacade.updateReservationStatusAndPrice(reservationId, updateReservation, token);
    }

    @GetMapping("/employee/reservations")
    public ResponseEntity<List<Reservation>> getReservations(
            @RequestParam(required = false) ReservationStatus status,
            @RequestHeader("Authorization") String token) {

        return reservationFacade.getReservations(status, token);
    }

    @GetMapping("/employee/get/{id}")
    public ResponseEntity<?> getEmployeeReservationById(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        return reservationFacade.getEmployeeReservationById(id, token);
    }

    @GetMapping("/employee/filtered")
    public ResponseEntity<List<Reservation>> getFilteredReservations(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) ReservationStatus status,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestHeader("Authorization") String token) {
//        reservationFacade.createReservationByMediator(reservation, token);
        try {
            FilterReservationDTO filterReservationDTO = new FilterReservationDTO();
            if (userId != null) {filterReservationDTO.setUserId(userId);}
            if (status != null) {filterReservationDTO.setStatus(status);}
            if (startDate != null) {filterReservationDTO.setStartDate(startDate);}
            if (endDate != null) {filterReservationDTO.setEndDate(endDate);}
            filterReservationDTO.setToken(token);
//            List<Reservation> reservations = reservationServiceQuery.getFilteredReservations(userId, status, startDate, endDate);
//            List<Reservation> reservations = reservationFacade.getFilteredReservations(userId, status, startDate, endDate);
            List<Reservation> reservations = reservationFacade.getFilteredReservations(filterReservationDTO);
            return ResponseEntity.ok(reservations);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

    }

    @PostMapping("/mediator")
    public void createWithMediator(@RequestBody MakeReservationDTO reservation, @RequestHeader("Authorization") String token) {
//        reservationServiceCreating.createReservationByMediator(reservation, token);
        reservationFacade.createReservationByMediator(reservation, token);
    }

}
//###   end L3 Facade -> Part 2 - adapting the code to use facade
