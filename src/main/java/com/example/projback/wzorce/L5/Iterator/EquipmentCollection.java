package com.example.projback.wzorce.L5.Iterator;

import com.example.projback.entity.Equipment;

import java.util.List;
//###   start L5 Iterator -> Collection -> Equipment
public class EquipmentCollection  implements IterableCollection<Equipment> {
    private List<Equipment> equipmentList;

    public EquipmentCollection(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

    @Override
    public Iterator<Equipment> createIterator() {
        return new EquipmentIterator(equipmentList);
    }
}
//###   end L5 Iterator -> Collection -> Equipment