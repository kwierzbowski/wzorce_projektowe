package com.example.projback.wzorce.L2.Decorator;

import com.example.projback.entity.Equipment;
import com.example.projback.entity.User;
import com.example.projback.wzorce.L2.Bridge.EquipmentValidator;

//###   start L2 Decorator -> Part 1
public class EquipmentValidator_Description implements EquipmentValidator {

    private final EquipmentValidator decoratedValidator;

    public EquipmentValidator_Description(EquipmentValidator decoratedValidator) {
        this.decoratedValidator = decoratedValidator;
    }

    @Override
    public boolean validate(Equipment equipment, User user) {
        boolean baseValidation = decoratedValidator.validate(equipment, user);

        if (equipment.getDescription() != null && equipment.getDescription().length() > 500) {
            throw new IllegalArgumentException("Opis wyposażenia nie może przekraczać 500 znaków.");
        }
        return baseValidation;
    }
}
//###   end L2 Decorator -> Part 1