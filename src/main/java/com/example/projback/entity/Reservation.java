package com.example.projback.entity;


import com.example.projback.wzorce.L6.Visitor.PricingVisitor;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import com.example.projback.wzorce.L6.State.*;


import java.util.Date;
import java.util.List;

@Entity
public class Reservation implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long roomId;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date endTime;

    @ElementCollection
    @CollectionTable(name = "reservation_equipment_ids", joinColumns = @JoinColumn(name = "reservation_id"))
    @Column(name = "equipment_id")
    private List<Long> additionalEquipment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status;

    @Column(nullable = false)
    private Double estimatedPrice;

    @Column
    private Double finalPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public List<Long> getAdditionalEquipment() {
        return additionalEquipment;
    }

    public void setAdditionalEquipment(List<Long> additionalEquipment) {
        this.additionalEquipment = additionalEquipment;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
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

    //###   start L1 Prototype -> Part 2
    @Override
    public Reservation clone() {
        try {
            return (Reservation) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Błąd klonowania rezerwacji", e);
        }
    }
    //###   end L1 Prototype -> Part 2

    //###   start L6 Visitor
    public void accept(PricingVisitor visitor) {
        visitor.visit(this);
    }
    //### end L6 Visitor


}

