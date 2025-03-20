package com.example.projback.wzorce.L8.OdwracanieZaleznosci;

import com.example.projback.config.JwtUtil;
import com.example.projback.entity.Equipment;
import com.example.projback.repository.EquipmentRepository;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Equipment.IEquipmentService_Creating;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Equipment.IEquipmentService_Manipulating;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Equipment.IEquipmentService_Query;

//###   start L2 Bridge -> Klasa abstrakcyjna
//###   start L8 Odwrocenie zaleznosci -> klasa abstrakcyjna
public abstract class AbstractEquipmentService_Query implements IEquipmentService_Query {
    protected final EquipmentRepository equipmentRepository;
    protected final JwtUtil jwtUtil;

    protected AbstractEquipmentService_Query(EquipmentRepository equipmentRepository, JwtUtil jwtUtil) {
        this.equipmentRepository = equipmentRepository;
        this.jwtUtil = jwtUtil;
    }

    public abstract void printAllEquipment();
}
//###   end L8 Odwrocenie zaleznosci -> klasa abstrakcyjna
//###   end L2 Bridge -> Klasa abstrakcyjna