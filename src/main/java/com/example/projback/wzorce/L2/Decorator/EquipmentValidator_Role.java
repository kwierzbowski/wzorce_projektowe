package com.example.projback.wzorce.L2.Decorator;

import com.example.projback.entity.Equipment;
import com.example.projback.entity.Role;
import com.example.projback.entity.User;
import com.example.projback.wzorce.L2.Bridge.EquipmentValidator;

//###   start L2 Decorator -> Part 3
public class EquipmentValidator_Role implements EquipmentValidator {

    private final EquipmentValidator decoratedValidator;

    public EquipmentValidator_Role(EquipmentValidator decoratedValidator) {
        this.decoratedValidator = decoratedValidator;
    }

    @Override
    public boolean validate(Equipment equipment, User user) {
        boolean baseValidation = decoratedValidator.validate(equipment, user);

        if (user.getRole() != Role.EMPLOYEE) {
            throw new IllegalArgumentException("Tylko pracownik może dodawać wyposażenie.");
        }
        return baseValidation;
    }
}
//###   end L2 Decorator -> Part 3