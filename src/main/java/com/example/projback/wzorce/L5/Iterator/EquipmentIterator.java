package com.example.projback.wzorce.L5.Iterator;

import com.example.projback.entity.Equipment;

import java.util.List;
//###   start L5 Iterator -> Equipment
public class EquipmentIterator implements Iterator<Equipment>  {
    private List<Equipment> equipmentList;
    private int position = 0;

    public EquipmentIterator(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

    @Override
    public boolean hasNext() {
        return position < equipmentList.size();
    }

    @Override
    public Equipment next() {
        if (!hasNext()) {
            throw new IllegalStateException("Brak kolejnych elementów");
        }
        return equipmentList.get(position++);
    }
}
//###   end L5 Iterator -> Equipment