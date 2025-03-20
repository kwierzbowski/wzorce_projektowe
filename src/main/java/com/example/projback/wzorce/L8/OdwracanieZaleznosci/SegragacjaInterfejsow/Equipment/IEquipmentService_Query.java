package com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Equipment;

import com.example.projback.entity.Equipment;

import java.util.List;

//###   start L8 Segregacja interfejsów
public interface IEquipmentService_Query {
    List<Equipment> getAllEquipment();
    void printAllEquipment();
    Equipment getEquipmentById(Long id);
}
//###   end L8 Segregacja interfejsów
