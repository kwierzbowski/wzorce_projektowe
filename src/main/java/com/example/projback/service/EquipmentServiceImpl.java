package com.example.projback.service;

import com.example.projback.config.JwtUtil;
import com.example.projback.entity.Equipment;
import com.example.projback.entity.Role;
import com.example.projback.entity.User;
import com.example.projback.repository.EquipmentRepository;
import com.example.projback.wzorce.L1.builder.EquipmentBuilder;
import com.example.projback.wzorce.L2.Adapter.AdapterInterface;
import com.example.projback.wzorce.L2.Adapter.EquipmentAdapter;
import com.example.projback.wzorce.L2.Bridge.EquipmentValidator;
import com.example.projback.wzorce.L2.Bridge.EquipmentValidator_Advanced;
import com.example.projback.wzorce.L2.Decorator.EquipmentValidator_Description;
import com.example.projback.wzorce.L2.Decorator.EquipmentValidator_Equipment;
import com.example.projback.wzorce.L2.Decorator.EquipmentValidator_Role;
import com.example.projback.wzorce.L5.Command.Command;
import com.example.projback.wzorce.L5.Command.Equipment_Create_Command;
import com.example.projback.wzorce.L5.Iterator.EquipmentCollection;
import com.example.projback.wzorce.L5.Iterator.Iterator;
import com.example.projback.wzorce.L5.Mediator.EquipmentMediator;
import com.example.projback.wzorce.L5.Memento.Caretaker;
import com.example.projback.wzorce.L5.Memento.Memento;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.AbstractEquipmentService_Creating;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Equipment.IEquipmentService_Manipulating;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Equipment.IEquipmentService_Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

//###   start L2 Bridge -> UserService -> Usage
//###   start L8 Odwrocenie zaleznosci -> Usage
@Service
public class EquipmentServiceImpl extends AbstractEquipmentService_Creating implements IEquipmentService_Manipulating, IEquipmentService_Query {

    public EquipmentServiceImpl(EquipmentRepository equipmentRepository, JwtUtil jwtUtil, UserService userService,  @Qualifier("simpleValidator_Equipment") EquipmentValidator validatorBridge) {
        super(equipmentRepository, jwtUtil);
        this.userService = userService;
        this.validatorBridge = validatorBridge;
    }

    //###   end L2 Bridge -> UserService -> Usage
    //###   end L8 Odwrocenie zaleznosci -> Usage

    private final UserService userService;
    private final Caretaker<Equipment> equipmentCaretaker = new Caretaker<>();
    private final EquipmentValidator validatorBridge;

    //###   start L5 MEDIATOR -> field
    private EquipmentMediator equipmentMediator;
    @Autowired
    public void setMediator(EquipmentMediator equipmentMediator) {
        this.equipmentMediator = equipmentMediator;
    }
    //###   end L5 MEDIATOR -> field


    // Method that return equipment by Id
    @Override
    public Equipment getEquipmentById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Nieprawidłowy identyfikator wyposażenia.");
        }

        return equipmentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono wyposażenia o podanym ID."));
    }

    // Method that returns all available equipment
    @Override
    public List<Equipment> getAllEquipment() {
        List<Equipment> equipmentList = equipmentRepository.findAll();

        if (equipmentList.isEmpty()) {
            throw new IllegalStateException("Nie znaleziono żadnego wyposażenia.");
        }

        printAllEquipment();

        return equipmentList;
    }

    //###   start L9 c,d , Part 3
    @Override
    public Equipment saveEquipment(Equipment equipment, String token) {
        String username = extractUsernameFromToken(token);
        User user = findUserByUsername(username);
        validateTokenExpiration(token);

        validateEquipment(equipment, user);
        validateEquipmentBridge(equipment, user);

        Equipment newEquipment = buildNewEquipment(equipment);
        return executeCreateEquipmentCommand(newEquipment);
    }
    //###   end L9 c,d , Part 3

    //Method that saves equipment based on sent data about equipment
//    @Override
//    public Equipment saveEquipment(Equipment equipment, String token) {
//        String username = jwtUtil.extractUsername(token.substring(7));
//        User user = userService.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        if (jwtUtil.isTokenExpired(token.substring(7))) {
//            throw new IllegalArgumentException("Token się przedawnil");
//        }
//
//
//        //###   start L2 Decorator -> Usage
//        EquipmentValidator validator = new EquipmentValidator_Advanced();
//        validator = new EquipmentValidator_Role(validator);
//        validator = new EquipmentValidator_Equipment(validator);
//        validator = new EquipmentValidator_Description(validator);
//        if (!validator.validate(equipment, user)) {
//            throw new IllegalArgumentException("Wyposażenie nie spełnia wymagań.");
//        }
//        //###   end L2 Decorator -> Usage
//
//        //###   start L2 Bridge -> Equipment -> Part 4
//        if (!validatorBridge.validate(equipment, user)) {
//            throw new IllegalArgumentException("Element wyposażenia niepoprawny");
//        }
//        //###   end L2 Bridge -> Equipment -> Part 4
//
//        //###   start L1 Factory -> Part 1 - usage
////        Equipment newEquipment = EquipmentFactory.createEquipment(equipment.getName(), equipment.getPrice(), equipment.getDescription(), equipment.getImageName());
////        return equipmentRepository.save(newEquipment);
//        //###   end L1 Factory -> Part 1 - usage
//
//        //###   start L1 Builder -> Part 1 - usage
//        Equipment newEquipment = new EquipmentBuilder()
//                .setName(equipment.getName())
//                .setPrice(equipment.getPrice())
//                .setDescription(equipment.getDescription())
//                .setImageName(equipment.getImageName())
//                .build();
////        return equipmentRepository.save(newEquipment);
//
//        //###   start L5 Command -> Equipment - usage
//        Command createEquipmentCommand = new Equipment_Create_Command(newEquipment, equipmentRepository);
//        return createEquipmentCommand.execute();
//        //###   end L5 Command -> Equipment - usage
//
//        //###   end L1 Builder -> Part 1 - usage
//
//    }

    private String extractUsernameFromToken(String token) {
        return jwtUtil.extractUsername(token.substring(7));
    }

    private User findUserByUsername(String username) {
        return userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private void validateTokenExpiration(String token) {
        if (jwtUtil.isTokenExpired(token.substring(7))) {
            throw new IllegalArgumentException("Token się przedawnil");
        }
    }

    //###   start L2 Decorator -> Usage
    private void validateEquipment(Equipment equipment, User user) {
        EquipmentValidator validator = new EquipmentValidator_Advanced();
        validator = new EquipmentValidator_Role(validator);
        validator = new EquipmentValidator_Equipment(validator);
        validator = new EquipmentValidator_Description(validator);
        if (!validator.validate(equipment, user)) {
            throw new IllegalArgumentException("Wyposażenie nie spełnia wymagań.");
        }
    }
//###   end L2 Decorator -> Usage

    //###   start L2 Bridge -> Equipment -> Part 4
    private void validateEquipmentBridge(Equipment equipment, User user) {
        if (!validatorBridge.validate(equipment, user)) {
            throw new IllegalArgumentException("Element wyposażenia niepoprawny");
        }
    }
//###   end L2 Bridge -> Equipment -> Part 4

    //###   start L1 Builder -> Part 1 - usage
    private Equipment buildNewEquipment(Equipment equipment) {
        return new EquipmentBuilder()
                .setName(equipment.getName())
                .setPrice(equipment.getPrice())
                .setDescription(equipment.getDescription())
                .setImageName(equipment.getImageName())
                .build();
    }
//###   end L1 Builder -> Part 1 - usage

    //###   start L5 Command -> Equipment - usage
    private Equipment executeCreateEquipmentCommand(Equipment newEquipment) {
        Command createEquipmentCommand = new Equipment_Create_Command(newEquipment, equipmentRepository);
        return createEquipmentCommand.execute();
    }
//###   end L5 Command -> Equipment - usage


    //Method that saves equipment based on sent data about equipment with mediator
    @Override
    public void createEquipmentByMediator(Equipment equipment, String token) {

        Equipment newEquipment = new EquipmentBuilder()
                .setName(equipment.getName())
                .setPrice(equipment.getPrice())
                .setDescription(equipment.getDescription())
                .setImageName(equipment.getImageName())
                .build();

        //###   start L5 MEDIATOR -> Equipment - usage
        equipmentMediator.notify(newEquipment, "create", token);
        //###   end L5 MEDIATOR -> Equipment - usage
    }

    //Method that delete equipment based on id
    @Override
    public String deleteEquipment(Long id, String token) {
        String username = extractUsernameFromToken(token);
        User user = findUserByUsername(username);
        validateTokenExpiration(token);
        validateUserRole(user);

        return performEquipmentDeletion(id);
    }

    private void validateUserRole(User user) {
        if (user.getRole() != Role.EMPLOYEE) {
            throw new IllegalArgumentException("Tylko pracownik moze usunac wyposażenie");
        }
    }

    private String performEquipmentDeletion(Long id) {
        try {
            equipmentRepository.deleteById(id);
            return "Usunięto";
        } catch (Exception e) {
            return "Cos poszło nie tak z usuwaniem.";
        }
    }
//    @Override
//    public String deleteEquipment(Long id, String token) {
//        String username = jwtUtil.extractUsername(token.substring(7));
//
//        User user = userService.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        if (jwtUtil.isTokenExpired(token.substring(7))) {
//            throw new IllegalArgumentException("Token się przedawnil");
//        }
//        if(user.getRole() != Role.EMPLOYEE){
//            throw new IllegalArgumentException("Tylko pracownik moze usunac wyposażenie");
//        }
//        try {
//            equipmentRepository.deleteById(id);
//            return "Usunięto";
//        }catch (Exception e){
//            return "Cos poszło nie tak z usuwaniem.";
//        }
//    }

    @Override
    public Equipment updateEquipment(Equipment equipment, String token, long id) {
        String username = extractUsernameFromToken(token);
        User user = findUserByUsername(username);
        validateTokenExpiration(token);

        validateEquipment(equipment, user);
        validateEquipmentBridge(equipment, user);

        return updateExistingEquipment(equipment, id);
    }

    //###   start L5 MEMENTO -> Equipment -> Usage (Saving in history)
    private Equipment updateExistingEquipment(Equipment equipment, long id) {
        Equipment currentEquipment = getEquipmentById(id);
        equipmentCaretaker.save(new Memento<>(currentEquipment.clone()));

        currentEquipment.setName(equipment.getName());
        currentEquipment.setPrice(equipment.getPrice());
        currentEquipment.setDescription(equipment.getDescription());

        return equipmentRepository.save(currentEquipment);
    }
//###   end L5 MEMENTO -> Equipment -> Usage (Saving in history)

//    //Method that update equipment based on sent data about equipment, equipment id
//    @Override
//    public Equipment updateEquipment(Equipment equipment, String token, long id) {
//        String username = jwtUtil.extractUsername(token.substring(7));
//        User user = userService.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        if (jwtUtil.isTokenExpired(token.substring(7))) {
//            throw new IllegalArgumentException("Token się przedawnil");
//        }
//
//        //###   start L2 Decorator -> Usage
//        EquipmentValidator validator = new EquipmentValidator_Advanced();
//        validator = new EquipmentValidator_Role(validator);
//        validator = new EquipmentValidator_Equipment(validator);
//        validator = new EquipmentValidator_Description(validator);
//        if (!validator.validate(equipment, user)) {
//            throw new IllegalArgumentException("Wyposażenie nie spełnia wymagań.");
//        }
//        //###   end L2 Decorator -> Usage
//
//        //###   start L2 Bridge -> Equipment -> Part 4
//        if (!validator.validate(equipment, user)) {
//            throw new IllegalArgumentException("Element wyposażenia niepoprawny");
//        }
//        //###   end L2 Bridge -> Equipment -> Part 4
//
//
//        //###   start L5 MEMENTO -> Equipment -> Usage (Saving in history)
//        Equipment currentEquipment = getEquipmentById(id);
//        equipmentCaretaker.save(new Memento<>(currentEquipment.clone()));
//        //###   end L5 MEMENTO -> Equipment -> Usage (Saving in history)
//
//        currentEquipment.setName(equipment.getName());
//        currentEquipment.setPrice(equipment.getPrice());
//        currentEquipment.setDescription(equipment.getDescription());
//
//
//        return equipmentRepository.save(currentEquipment);
//    }

    //###   start L5 MEMENTO -> Equipment -> Usage (Undo changes)
    @Override
    public Equipment undoLastEquipmentEdit() {
        Memento<Equipment> memento = equipmentCaretaker.undo();
        if (memento != null) {
            Equipment restored = memento.getState();
            return equipmentRepository.save(restored);
        }
        return null;
    }
    //###   end L5 MEMENTO -> Equipment -> Usage (Undo changes)


    //###   start L5 ITERATOR -> Equipment -> Usage
    @Override
    public void printAllEquipment() {
        List<Equipment> equipmentList = equipmentRepository.findAll();
        EquipmentCollection collection = new EquipmentCollection(equipmentList);
        Iterator<Equipment> iterator = collection.createIterator();

        while (iterator.hasNext()) {
            Equipment equipment = iterator.next();
            System.out.println("Name: " + equipment.getName() + ", price: " + equipment.getPrice());
        }
    }
    //###   end L5 ITERATOR -> Equipment -> Usage


    //###   start L1 Prototype -> Part 1 -> Usage
    public Equipment duplicateEquipment(Long id) {

        Equipment existing = equipmentRepository.findById(id).orElseThrow();
        Equipment cloned = existing.clone();

        //###   start L2 Adapter -> Part 2 -> Usage
        AdapterInterface adapter = new EquipmentAdapter(cloned);
        String name = ("Cloned " + adapter.getName());
        //###   end L2 Adapter -> Part 2 -> Usage

        cloned.setName(name);
        return cloned;
    }
    //###   end L1 Prototype -> Part 1 -> Usage
}
