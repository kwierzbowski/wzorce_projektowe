package com.example.projback.controller;

import com.example.projback.dto.MakeReservationDTO;
import com.example.projback.entity.Equipment;
//import com.example.projback.wzorce.L8.OdwracanieZaleznosci.IEquipmentService;
import com.example.projback.wzorce.L10.EquipmentFilter;
import com.example.projback.wzorce.L3.Proxy.EquipmentServiceProxy;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Equipment.IEquipmentService_Query;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Equipment.IEquipmentService_Creating;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Equipment.IEquipmentService_Manipulating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/equipment")
@CrossOrigin(origins = "*")
public class EquipmentController {
//    @Autowired
//    private EquipmentService equipmentService;

    /// ### start L3 Proxy -> Part 3, changing EquipmentController by adding EquipmentServiceProxy instead EquipmentService
    private final EquipmentServiceProxy equipmentServiceProxy;

    @Autowired
    public EquipmentController(EquipmentServiceProxy equipmentServiceProxy) {
        this.equipmentServiceProxy = equipmentServiceProxy;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Equipment>> getAllEquipment() {
        return ResponseEntity.ok(equipmentServiceProxy.getAllEquipment());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable Long id) {
        return ResponseEntity.ok(equipmentServiceProxy.getEquipmentById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Equipment> addEquipment(@RequestBody Equipment equipment, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(equipmentServiceProxy.saveEquipment(equipment, token));
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEquipment(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        return equipmentServiceProxy.deleteEquipment(id, token);
    }

    @PutMapping("/update/{id}")
    public Equipment updateEquipment(@PathVariable Long id, @RequestBody Equipment equipment, @RequestHeader("Authorization") String token) {
        return equipmentServiceProxy.updateEquipment(equipment, token, id);
    }

//    @GetMapping("/getAll")
//    public List<Equipment> getAllEquipment() {
//        return equipmentQueryService.getAllEquipment();
//    }
//
//    @GetMapping("/{id}")
//    public Equipment getEquipmentById(@PathVariable Long id) {
//        return equipmentQueryService.getEquipmentById(id);
//    }
//
//    @PostMapping("/add")
//    public Equipment addEquipment(@RequestBody Equipment equipment, @RequestHeader("Authorization") String token) {
//        return equipmentCreationService.saveEquipment(equipment, token);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public String deleteEquipment(@PathVariable Long id, @RequestHeader("Authorization") String token) {
//        return equipmentModificationService.deleteEquipment(id, token);
//    }
//
//    @PutMapping("/update/{id}")
//    public Equipment updateEquipment(@PathVariable Long id, @RequestBody Equipment equipment, @RequestHeader("Authorization") String token) {
//        return equipmentModificationService.updateEquipment(equipment, token, id);
//    }

    @GetMapping("/undo")
    public Equipment undoEquipment() {
        return equipmentServiceProxy.undoLastEquipmentEdit();
//        return equipmentModificationService.undoLastEquipmentEdit();
    }
    @PostMapping("/mediator")
    public void createWithMediator(@RequestBody Equipment equipment, @RequestHeader("Authorization") String token) {
        equipmentServiceProxy.createEquipmentByMediator(equipment, token);
//        equipmentCreationService.createEquipmentByMediator(equipment, token);
//        return "mediator";
    }

    //###   start L10, EquipmentFilter (part 3)
    @GetMapping("/filteredByPrice")
    public List<Equipment> getFilteredEquipment(@RequestParam double minPrice) {
        return equipmentServiceProxy.getEquipmentFilteredByPrice(minPrice);
    }
    //###   end L10, EquipmentFilter (part 3)
}

