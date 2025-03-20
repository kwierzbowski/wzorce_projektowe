package com.example.projback.wzorce.L1.factory;

import com.example.projback.entity.Reservation;
import com.example.projback.entity.ReservationStatus;

import java.util.Date;
import java.util.List;

//###   start L1 Factory -> Part 2 - declaration
public class ReservationFactory {
    public static Reservation createReservation(Long userId, Long roomId, Date startTime, Date endTime, List<Long> equipment, double finalPrice, ReservationStatus status) {
        Reservation reservation = new Reservation();
        reservation.setUserId(userId);
        reservation.setRoomId(roomId);
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);
        reservation.setAdditionalEquipment(equipment);
        reservation.setEstimatedPrice(finalPrice);
        reservation.setStatus(status);
        return reservation;
    }
}
//###   end L1 Factory -> Part 2 - declaration