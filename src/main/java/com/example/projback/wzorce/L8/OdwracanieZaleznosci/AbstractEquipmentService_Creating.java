package com.example.projback.wzorce.L8.OdwracanieZaleznosci;

import com.example.projback.config.JwtUtil;
import com.example.projback.entity.Equipment;
import com.example.projback.repository.EquipmentRepository;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Equipment.IEquipmentService_Creating;

//###   start L2 Bridge -> Klasa abstrakcyjna
//###   start L8 Odwrocenie zaleznosci -> klasa abstrakcyjna
public abstract class AbstractEquipmentService_Creating implements IEquipmentService_Creating {
    protected final EquipmentRepository equipmentRepository;
    protected final JwtUtil jwtUtil;

    protected AbstractEquipmentService_Creating(EquipmentRepository equipmentRepository, JwtUtil jwtUtil) {
        this.equipmentRepository = equipmentRepository;
        this.jwtUtil = jwtUtil;
    }

    public abstract void createEquipmentByMediator(Equipment equipment, String token);

}
//###   end L8 Odwrocenie zaleznosci -> klasa abstrakcyjna
//###   end L2 Bridge -> Klasa abstrakcyjna