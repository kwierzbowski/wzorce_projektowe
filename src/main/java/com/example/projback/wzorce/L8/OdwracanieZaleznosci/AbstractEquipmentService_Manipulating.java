package com.example.projback.wzorce.L8.OdwracanieZaleznosci;

import com.example.projback.config.JwtUtil;
import com.example.projback.entity.Equipment;
import com.example.projback.repository.EquipmentRepository;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Equipment.IEquipmentService_Creating;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Equipment.IEquipmentService_Manipulating;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Equipment.IEquipmentService_Query;

//###   start L2 Bridge -> Klasa abstrakcyjna
//###   start L8 Odwrocenie zaleznosci -> klasa abstrakcyjna
public abstract class AbstractEquipmentService_Manipulating implements IEquipmentService_Manipulating {
    protected final EquipmentRepository equipmentRepository;
    protected final JwtUtil jwtUtil;

    protected AbstractEquipmentService_Manipulating(EquipmentRepository equipmentRepository, JwtUtil jwtUtil) {
        this.equipmentRepository = equipmentRepository;
        this.jwtUtil = jwtUtil;
    }

    public abstract String deleteEquipment(Long id, String token);

    public abstract Equipment updateEquipment(Equipment equipment, String token, long id);

    public abstract Equipment undoLastEquipmentEdit();

}
//###   end L8 Odwrocenie zaleznosci -> klasa abstrakcyjna
//###   end L2 Bridge -> Klasa abstrakcyjna