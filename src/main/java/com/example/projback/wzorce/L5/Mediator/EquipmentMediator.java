package com.example.projback.wzorce.L5.Mediator;

import com.example.projback.config.JwtUtil;
import com.example.projback.entity.Equipment;
import com.example.projback.entity.Room;
import com.example.projback.entity.User;
import com.example.projback.repository.EquipmentRepository;
import com.example.projback.service.UserService;

//###   start L5 Mediator -> Equipment
public class EquipmentMediator implements Mediator {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final EquipmentRepository equipmentRepository;

    public EquipmentMediator(UserService userService, JwtUtil jwtUtil, EquipmentRepository equipmentRepository) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public Room notify(Object sender, String action, String token) {
        try {
            String username = jwtUtil.extractUsername(token.substring(7));
            User user = userService.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (jwtUtil.isTokenExpired(token.substring(7))) {
                throw new RuntimeException("Token is expired");
            }

            switch (action) {
                case "create":
                    createEquipment((Equipment) sender, user);
                    break;
                default:
                    throw new IllegalArgumentException("Nieznana akcja");
            }

        } catch (Exception e) {
            throw new RuntimeException("Bład: " + e.getMessage(), e);
        }
        return null;
    }
    private void createEquipment(Equipment equipment, User user) {
        equipmentRepository.save(equipment);
    }
}
//###   end L5 Mediator -> Equipment