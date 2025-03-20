package com.example.projback.wzorce.L5.Command;

import com.example.projback.entity.Equipment;
import com.example.projback.repository.EquipmentRepository;

//###   start L5 Command -> Equipment
public class Equipment_Create_Command implements Command {
    private Equipment equipment;
    private EquipmentRepository equipmentRepository;

    public Equipment_Create_Command(Equipment equipment, EquipmentRepository equipmentRepository) {
        this.equipment = equipment;
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public Equipment execute() {
        equipmentRepository.save(equipment);
        return null;
    }
}
//###   end L5 Command -> Equipment