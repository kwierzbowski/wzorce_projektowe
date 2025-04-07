package com.example.projback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MakeReservationDTO {
    private Long room;
    private Date startTime;
    private Date endTime;
    private List<Long> additionalEquipment;
    private Double estimatedPrice;


    public List<Long> getAdditionalEquipment() {
        return additionalEquipment;
    }

    public void setAdditionalEquipment(List<Long> additionalEquipment) {
        this.additionalEquipment = additionalEquipment;
    }

    public Double getEstimatedPrice() {
        return estimatedPrice;
    }

    public void setEstimatedPrice(Double estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getRoom() {
        return room;
    }

    public void setRoom(Long room) {
        this.room = room;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
