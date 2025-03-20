package com.example.projback.wzorce.L5.Mediator;

import com.example.projback.config.JwtUtil;
import com.example.projback.entity.Room;
import com.example.projback.repository.RoomRepository;
import org.springframework.stereotype.Component;

@Component
//###   start L5 Mediator -> Equipment
public class RoomMediator implements Mediator {
    private final JwtUtil jwtUtil;
    private final RoomRepository roomRepository;

    public RoomMediator(JwtUtil jwtUtil, RoomRepository roomRepository) {
        this.jwtUtil = jwtUtil;
        this.roomRepository = roomRepository;
    }

    @Override
    public Room notify(Object sender, String action, String token) {
        try {
            String username = jwtUtil.extractUsername(token.substring(7));
            if (jwtUtil.isTokenExpired(token.substring(7))) {
                throw new RuntimeException("Token is expired");
            }

            switch (action) {
                case "create":
                    createRoom((Room) sender);
                    break;
                default:
                    throw new IllegalArgumentException("Nieznana akcja");
            }

        } catch (Exception e) {
            throw new RuntimeException("Bład: " + e.getMessage(), e);
        }
        return null;
    }
    private void createRoom(Room room) {
        roomRepository.save(room);
    }
}
//###   end L5 Mediator -> Equipment