package com.example.projback.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class Room implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomType type;

    @ElementCollection
    @CollectionTable(name = "room_base_equipment_ids", joinColumns = @JoinColumn(name = "room_id"))
    @Column(name = "equipment_id")
    private List<Long> baseEquipment;

    @ElementCollection
    @CollectionTable(name = "room_optional_equipment_ids", joinColumns = @JoinColumn(name = "room_id"))
    @Column(name = "equipment_id")
    private List<Long> optionalEquipment;

    @Column
    private Double pricePerHour;

    @Column
    private Double pricePerDay;

    @ElementCollection
    private List<Long> reservations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getReservations() {
        return reservations;
    }

    public void setReservations(List<Long> reservations) {
        this.reservations = reservations;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public List<Long> getOptionalEquipment() {
        return optionalEquipment;
    }

    public void setOptionalEquipment(List<Long> optionalEquipment) {
        this.optionalEquipment = optionalEquipment;
    }

    public List<Long> getBaseEquipment() {
        return baseEquipment;
    }

    public void setBaseEquipment(List<Long> baseEquipment) {
        this.baseEquipment = baseEquipment;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //###   start L1 Prototype -> Part 3
    @Override
    public Room clone() {
        try {
            return (Room) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Błąd klonowania pomieszczenia", e);
        }
    }
    //###   end L1 Prototype -> Part 3
}

