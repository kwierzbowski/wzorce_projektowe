package com.example.projback.wzorce.L10;

import com.example.projback.entity.Equipment;

//###   start L10, EquipmentFilter (part 1)
@FunctionalInterface
public interface EquipmentFilter {
    boolean test(Equipment equipment);
}
//###   end L10, EquipmentFilter (part 1)