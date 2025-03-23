package com.example.projback.dto;

import com.example.projback.entity.ReservationStatus;
import lombok.Data;

import java.util.Date;

//###   start L9 e, DTO
@Data
public class FilterReservationDTO {
    private Long userId = null;
    private ReservationStatus status = null;
    private Date startDate = null;
    private Date endDate = null;
    private String token;
}
//###   end L9 e, DTO