package com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Equipment;

import com.example.projback.entity.Equipment;

//###   start L8 Segregacja interfejsów
public interface IEquipmentService_Manipulating {
    String deleteEquipment(Long id, String token);
    Equipment updateEquipment(Equipment equipment, String token, long id);
    Equipment undoLastEquipmentEdit();
}
//###   end L8 Segregacja interfejsów