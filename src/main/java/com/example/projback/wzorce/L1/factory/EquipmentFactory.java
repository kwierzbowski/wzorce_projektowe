package com.example.projback.wzorce.L1.factory;

import com.example.projback.entity.Equipment;

//###   start L1 Factory -> Part 1 - declaration
public class EquipmentFactory {
    public static Equipment createEquipment(String name, Double price, String description, String imageName) {
        Equipment equipment = new Equipment();
        equipment.setName(name);
        equipment.setPrice(price);
        equipment.setDescription(description);
        equipment.setImageName(imageName);
        return equipment;
    }
}
//###   end L1 Factory -> Part 1 - declaration