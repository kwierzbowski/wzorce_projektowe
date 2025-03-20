package com.example.projback.wzorce.L1.builder;

import com.example.projback.entity.Equipment;

//###   start L1 Builder -> Part 1 - declaration
public class EquipmentBuilder {
    private Equipment equipment = new Equipment();

    public EquipmentBuilder setName(String name) {
        equipment.setName(name);
        return this;
    }

    public EquipmentBuilder setPrice(Double price) {
        equipment.setPrice(price);
        return this;
    }

    public EquipmentBuilder setDescription(String description) {
        equipment.setDescription(description);
        return this;
    }

    public EquipmentBuilder setImageName(String imageName) {
        equipment.setImageName(imageName);
        return this;
    }

    public Equipment build() {
        return equipment;
    }
}
//###   end L1 Builder -> Part 1 - declaration