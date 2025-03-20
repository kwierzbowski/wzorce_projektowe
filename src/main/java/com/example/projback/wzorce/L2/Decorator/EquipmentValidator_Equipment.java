package com.example.projback.wzorce.L2.Decorator;

import com.example.projback.entity.Equipment;
import com.example.projback.entity.User;
import com.example.projback.wzorce.L2.Bridge.EquipmentValidator;

//###   start L2 Decorator -> Part 2
public class EquipmentValidator_Equipment implements EquipmentValidator {

    private final EquipmentValidator decoratedValidator;

    public EquipmentValidator_Equipment(EquipmentValidator decoratedValidator) {
        this.decoratedValidator = decoratedValidator;
    }

    @Override
    public boolean validate(Equipment equipment, User user) {
        boolean baseValidation = decoratedValidator.validate(equipment, user);

        if (equipment == null) {
            throw new IllegalArgumentException("Wyposażenie nie może być puste.");
        }
        return baseValidation;
    }
}
//###   end L2 Decorator -> Part 2