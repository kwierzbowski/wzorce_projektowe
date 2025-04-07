package com.example.projback.entity;

import com.example.projback.wzorce.L2.Composite.EquipmentComponent;
import jakarta.persistence.*;

@Entity
public class Equipment implements Cloneable, EquipmentComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(length = 500) // Opcjonalnie ustaw limit długości
    private String description;

    @Column(nullable = false)
    private String imageName; // Nazwa pliku obrazka w katalogu

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    //###   start L2 Composite -> Part 3
    @Override
    public void displayInfo() {
        System.out.println("Element wyposażenia: " + name + ", cena: " + price);
    }
    //###   end L2 Composite -> Part 3

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //###   start L1 Prototype -> Part 1
    @Override
    public Equipment clone() {
        try {
            return (Equipment) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Błąd klonowania elementu wyposażenia", e);
        }
    }
        //PRZYKŁAD ZASTOSOWANIA:
        //Equipment existing = equipmentRepository.findById(1).orElseThrow();
        //Equipment cloned = existing.clone();
    //###   end L1 Prototype -> Part 1

    //###   start L6 Observer
    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
    //###   end L6 Observer
}

