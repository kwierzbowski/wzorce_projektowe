package com.example.projback.wzorce.L2.Bridge;

import com.example.projback.entity.Equipment;
import com.example.projback.entity.User;
import org.springframework.stereotype.Component;


@Component("advancedValidator_Equipment")
public class EquipmentValidator_Advanced implements EquipmentValidator {
    @Override
    public boolean validate(Equipment equipment, User user) {
        if (equipment.getName() == null || equipment.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nazwa wyposażenia nie może być pusta.");
        }
        if (equipment.getPrice() == null || equipment.getPrice() < 0) {
            throw new IllegalArgumentException("Cena wyposażenia musi być większa lub równa 0.");
        }
        return true;
    }
}
