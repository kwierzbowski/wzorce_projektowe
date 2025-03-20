package com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Equipment;

import com.example.projback.entity.Equipment;

//###   start L8 Segregacja interfejsów
public interface IEquipmentService_Creating {
    Equipment saveEquipment(Equipment equipment, String token);

    void createEquipmentByMediator(Equipment equipment, String token);
}
//###   end L8 Segregacja interfejsów