package com.example.projback.wzorce.L3.Proxy;

import com.example.projback.config.JwtUtil;
import com.example.projback.entity.Equipment;
import com.example.projback.entity.Role;
import com.example.projback.entity.User;
import com.example.projback.repository.UserRepository;
import com.example.projback.wzorce.L10.EquipmentFilter;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Equipment.IEquipmentService_Creating;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Equipment.IEquipmentService_Manipulating;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Equipment.IEquipmentService_Query;
import org.springframework.stereotype.Service;
import com.example.projback.wzorce.L6.Observer.EquipmentNotificationSystem;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//###   start L3 Proxy -> Part 3, validating admin access
@Service
public class EquipmentServiceProxy implements IEquipmentService_Manipulating, IEquipmentService_Query, IEquipmentService_Creating {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    private final Map<Long, Equipment> cache = new HashMap<>();
    //###   start L6 Observer
    private final EquipmentNotificationSystem notificationSystem;
    //###   end L6 Observer


    //###   start L8 Odwrocenie zaleznosci -> usage
//    @Autowired
//    private IEquipmentService equipmentService;
    private final IEquipmentService_Query equipmentQueryService;
    private final IEquipmentService_Creating equipmentCreationService;
    private final IEquipmentService_Manipulating equipmentModificationService;
    //###   end L8 Odwrocenie zaleznosci -> usage

    public EquipmentServiceProxy(JwtUtil jwtUtil,
                                 UserRepository userRepository, IEquipmentService_Query equipmentQueryService, IEquipmentService_Creating equipmentCreationService, IEquipmentService_Manipulating equipmentModificationService, EquipmentNotificationSystem notificationSystem) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.equipmentQueryService = equipmentQueryService;
        this.equipmentCreationService = equipmentCreationService;
        this.equipmentModificationService = equipmentModificationService;
        this.notificationSystem = notificationSystem;

    }

    @Override
    public Equipment saveEquipment(Equipment equipment, String token) {
        validateAdminAccess(token);
        //###   start L6 Observer (part 5)
        notificationSystem.notifyObservers("added", equipment);
        //###   end L6 Observer (part 5)
        return equipmentCreationService.saveEquipment(equipment, token);
    }

    @Override
    public String deleteEquipment(Long equipmentId, String token) {
        validateAdminAccess(token);
        //###   start L6 Observer (part 6)
        notificationSystem.notifyObservers("deleted", getEquipmentById(equipmentId));
        //###   end L6 Observer (part 6)
        String message = equipmentModificationService.deleteEquipment(equipmentId, token);
        cache.remove(equipmentId);
        return message;
    }

    @Override
    public Equipment updateEquipment(Equipment equipment, String token, long id) {
        validateAdminAccess(token);
        //###   start L6 Observer (part 7)
        notificationSystem.notifyObservers("updated", equipment);
        //###   end L6 Observer (part 7)
        Equipment eq = equipmentModificationService.updateEquipment(equipment, token, id);
        cache.remove(id);
        return eq;
    }

    @Override
    public List<Equipment> getAllEquipment() {
        return equipmentQueryService.getAllEquipment();
    }

    @Override
    public void printAllEquipment() {

    }

    @Override
    public Equipment getEquipmentById(Long equipmentId) {
        return cache.computeIfAbsent(equipmentId, equipmentQueryService::getEquipmentById);
    }

    @Override
    public Equipment undoLastEquipmentEdit() {
        return equipmentModificationService.undoLastEquipmentEdit();
    }

    @Override
    public void createEquipmentByMediator(Equipment equipment, String token) {
        equipmentCreationService.createEquipmentByMediator(equipment, token);

    }

    private void validateAdminAccess(String token) {
        String username = jwtUtil.extractUsername(token.substring(7));
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getRole().equals(Role.EMPLOYEE)) {
            throw new RuntimeException("Access denied: You are not an admin.");
        }
    }

    //###   start L10, EquipmentFilter (part 2)
    public List<Equipment> getEquipmentFilteredByPrice(double minPrice) {
        EquipmentFilter priceFilter = eq -> eq.getPrice() > minPrice;
        System.out.println("\nL10 | All equipment:");
        return getAllEquipment().stream()
                .filter(priceFilter::test)
                .toList();
    }
    //###   end L10, EquipmentFilter (part 2)

}
//###   end L3 Proxy -> Part 3, validating admin access