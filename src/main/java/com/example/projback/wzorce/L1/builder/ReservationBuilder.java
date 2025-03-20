package com.example.projback.wzorce.L1.builder;

import com.example.projback.entity.Reservation;
import com.example.projback.entity.ReservationStatus;

import java.util.Date;
import java.util.List;

//###   start L1 Builder -> Part 2 - declaration
public class ReservationBuilder {
    private Reservation reservation = new Reservation();

    public ReservationBuilder setUserId(Long userId) {
        reservation.setUserId(userId);
        return this;
    }

    public ReservationBuilder setRoomId(Long roomId) {
        reservation.setRoomId(roomId);
        return this;
    }

    public ReservationBuilder setStartTime(Date startTime) {
        reservation.setStartTime(startTime);
        return this;
    }

    public ReservationBuilder setEndTime(Date endTime) {
        reservation.setEndTime(endTime);
        return this;
    }

    public ReservationBuilder setAdditionalEquipment(List<Long> additionalEquipment) {
        reservation.setAdditionalEquipment(additionalEquipment);
        return this;
    }

    public ReservationBuilder setStatus(ReservationStatus status) {
        reservation.setStatus(status);
        return this;
    }

    public ReservationBuilder setEstimatedPrice(Double estimatedPrice) {
        reservation.setEstimatedPrice(estimatedPrice);
        return this;
    }

    public Reservation build() {
        return reservation;
    }
}
//###   end L1 Builder -> Part 2 - declaration