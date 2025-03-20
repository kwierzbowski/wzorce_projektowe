package com.example.projback.wzorce.L3.Facade;

import com.example.projback.entity.Room;
import com.example.projback.service.RoomServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

//###   start L3 Facade -> Part 3
@Component
public class RoomFacade {
    private final RoomServiceImpl roomService;

    public RoomFacade(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    public ResponseEntity<Room> createRoom(Room room, String token) {
        try {
            Room createdRoom = roomService.createRoom(room, token);
            return ResponseEntity.ok(createdRoom);
        } catch (RuntimeException e) {
            return ResponseEntity.status(403).body(null);
        }
    }

    public ResponseEntity<List<Room>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    public ResponseEntity<Room> getRoomById(Long id, String token) {
        try {
            Room room = roomService.getRoomById(id, token);
            return ResponseEntity.ok(room);
        } catch (RuntimeException e) {
            return ResponseEntity.status(403).body(null);
        }
    }

    public ResponseEntity<String> deleteRoom(Long id, String token) {
        return ResponseEntity.ok(roomService.deleteRoomById(id, token));
    }

    public ResponseEntity<Room> updateRoom(Long id, Room room, String token) {
        return ResponseEntity.ok(roomService.updateRoom(room, token, id));
    }

    public ResponseEntity<List<Room>> getAvailableRooms(Date startTime, Date endTime) {
        try {
            List<Room> availableRooms = roomService.findAvailableRooms(startTime, endTime);
            return ResponseEntity.ok(availableRooms);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(null);
        }
    }
}
//###   end L3 Facade -> Part 3