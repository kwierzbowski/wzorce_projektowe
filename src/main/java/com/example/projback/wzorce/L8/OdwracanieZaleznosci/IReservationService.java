//package com.example.projback.wzorce.L8.OdwracanieZaleznosci;
//
//import com.example.projback.dto.MakeReservationDTO;
//import com.example.projback.dto.UpdateReservationDTO;
//import com.example.projback.dto.UpdateReservationEmployeeDTO;
//import com.example.projback.entity.Reservation;
//import com.example.projback.entity.ReservationStatus;
//
//import java.util.Date;
//import java.util.List;
//
/// /###   start L8 Odwrocenie zaleznosci -> Interfejs
/// /###   start L8 "Gruby" interfejs"
//public interface IReservationService {
//    void createReservation(MakeReservationDTO reservation, String token);
//    void createReservationByMediator(MakeReservationDTO reservationDTO, String token);
//    Reservation validateReservation(String token, Long reservationId);
//    void deleteReservation(Long reservationId, String token);
//    void updateReservation(Long reservationId, UpdateReservationDTO updateReservation, String token);
//    void updateReservationStatusAndPrice(Long reservationId, UpdateReservationEmployeeDTO updateReservation, String token);
//    List<Reservation> getReservationsByStatusOrAll(ReservationStatus status, String token);
//    List<Reservation> getReservationsByUser(String token);
//    void updateReservationFields(Reservation reservation, UpdateReservationDTO updateReservation);
//    Reservation getCustomerReservationById(Long reservationId, String token);
//    Reservation getEmployeeReservationById(Long reservationId, String token);
//    List<Reservation> getReservationsByRoomId(Long roomId, String token);
//    List<Reservation> getFilteredReservations(Long userId, ReservationStatus status, Date startDate, Date endDate);
//    Reservation undoLastReservationEdit();
//    void printAllReservations();
//}
/// /###   end L8 Odwrocenie zaleznosci -> Interfejs
/// /###   end L8 "Gruby" interfejs"