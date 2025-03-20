package com.example.projback.wzorce.L6.Observer;

import com.example.projback.entity.Equipment;

//###   start L6 Observer
public interface EquipmentObserver {
    void onEquipmentChange(String eventType, Equipment equipment);
}
//###   end L6 Observer