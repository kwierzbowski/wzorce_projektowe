//package com.example.projback.wzorce.L8.OdwracanieZaleznosci;
//
//import com.example.projback.config.JwtUtil;
//import com.example.projback.entity.Equipment;
//import com.example.projback.repository.EquipmentRepository;
//import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Equipment.IEquipmentService_Creating;
//import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Equipment.IEquipmentService_Manipulating;
//import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Equipment.IEquipmentService_Query;
//
////###   start L8 Odwrocenie zaleznosci -> klasa abstrakcyjna
//public abstract class AbstractEquipmentService implements IEquipmentService_Creating, IEquipmentService_Manipulating, IEquipmentService_Query {
//    protected final EquipmentRepository equipmentRepository;
//    protected final JwtUtil jwtUtil;
//
//    protected AbstractEquipmentService(EquipmentRepository equipmentRepository, JwtUtil jwtUtil) {
//        this.equipmentRepository = equipmentRepository;
//        this.jwtUtil = jwtUtil;
//    }
//
//    public abstract void createEquipmentByMediator(Equipment equipment, String token);
//
//    public abstract String deleteEquipment(Long id, String token);
//
//    public abstract Equipment updateEquipment(Equipment equipment, String token, long id);
//
//    public abstract Equipment undoLastEquipmentEdit();
//
//    public abstract void printAllEquipment();
//}
////###   end L8 Odwrocenie zaleznosci -> klasa abstrakcyjna