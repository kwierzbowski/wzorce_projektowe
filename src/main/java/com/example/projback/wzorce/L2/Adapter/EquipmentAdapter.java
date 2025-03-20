package com.example.projback.wzorce.L2.Adapter;

import com.example.projback.entity.Equipment;
//###   start L2 Adapter -> Part 2
public class EquipmentAdapter implements AdapterInterface {
    private final Equipment equipment;

    public EquipmentAdapter(Equipment equipment) {
        this.equipment = equipment;
    }

    @Override
    public String getName() {
        return equipment.getName();
    }

    @Override
    public Long getId() {
        return equipment.getId();
    }

}

// Uage example
//        AdapterInterface adapter = new UserAdapter(user);
//        String name =  adapter.getName();

//###   end L2 Adapter -> Part 2