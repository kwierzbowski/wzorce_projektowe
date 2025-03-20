package com.example.projback.wzorce.L2.Bridge;

import com.example.projback.entity.Equipment;
import com.example.projback.entity.User;

//###   start L2 Bridge -> Equipment -> Part 1
public interface EquipmentValidator {
    boolean validate(Equipment equipment, User user);
}
//###   end L2 Bridge -> Equipment -> Part 1