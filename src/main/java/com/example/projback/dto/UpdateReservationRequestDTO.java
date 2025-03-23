package com.example.projback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateReservationRequestDTO {
    private Long reservationId;
    private UpdateReservationDTO updateReservation;
    private String token;


}
