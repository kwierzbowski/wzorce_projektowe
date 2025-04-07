package com.example.projback.wzorce.L2.Bridge;

import com.example.projback.entity.Equipment;
import com.example.projback.entity.User;
import org.springframework.stereotype.Component;


@Component("simpleValidator_Equipment")
public class EquipmentValidator_Simple implements EquipmentValidator {
    @Override
    public boolean validate(Equipment equipment, User user) {
        if (equipment.getName() == null || equipment.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nazwa wyposażenia nie może być pusta.");
        }
        return true;
    }
}
