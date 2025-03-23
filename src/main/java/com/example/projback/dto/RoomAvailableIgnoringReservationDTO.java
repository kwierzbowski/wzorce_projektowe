package com.example.projback.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RoomAvailableIgnoringReservationDTO {
    private Long roomId;
    private Date startTime;
    private Date endTime;
    private Long reservationIdToIgnore;
}
