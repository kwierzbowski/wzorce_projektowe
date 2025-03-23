package com.example.projback.controller;

import com.example.projback.entity.Equipment;
import com.example.projback.entity.ReservationStatus;
import com.example.projback.entity.Room;
//import com.example.projback.wzorce.L8.OdwracanieZaleznosci.IRoomService;
import com.example.projback.wzorce.L3.Facade.RoomFacade;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Equipment.IEquipmentService_Creating;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Equipment.IEquipmentService_Manipulating;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Equipment.IEquipmentService_Query;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Room.IRoomService_Creating;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Room.IRoomService_Manipulating;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Room.IRoomService_Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

//###   start L3 Facade -> Part 3 - adapting the code to use facade
@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "*")
public class RoomController {
//    @Autowired
//    private RoomService roomService;

    //###   start L8 Odwrocenie zaleznosci -> usage
//    @Autowired
//    private IRoomService roomService;
    private IRoomService_Manipulating roomServiceManipulating;
    private IRoomService_Creating roomServiceCreating;
    private IRoomService_Query roomServiceQuery;
    //###   end L8 Odwrocenie zaleznosci -> usage

    private final RoomFacade roomFacade;

    @Autowired
    public RoomController(RoomFacade roomFacade, IRoomService_Manipulating roomServiceManipulating,
                          IRoomService_Creating roomServiceCreating,
                          IRoomService_Query roomServiceQuery) {
        this.roomFacade = roomFacade;
        this.roomServiceManipulating = roomServiceManipulating;
        this.roomServiceCreating = roomServiceCreating;
        this.roomServiceQuery = roomServiceQuery;
    }

    @PostMapping("/employee/add")
    public ResponseEntity<Room> createRoom(@RequestBody Room room, @RequestHeader("Authorization") String token) {
        return roomFacade.createRoom(room, token);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Room>> getAllRooms() {
        return roomFacade.getAllRooms();
    }

    @GetMapping("/customer/available")
    public ResponseEntity<List<Room>> getAvailableRooms(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date startTime,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date endTime) {
        return roomFacade.getAvailableRooms(startTime, endTime);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        return roomFacade.getRoomById(id, token);
    }

    @DeleteMapping("/employee/delete/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        return roomFacade.deleteRoom(id, token);
    }

    @PutMapping("/employee/update/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room room, @RequestHeader("Authorization") String token) {
        return roomFacade.updateRoom(id, room, token);
    }

    @GetMapping("/undo")
    public Room undoRoom() {
        return roomServiceManipulating.undoLastRoomEdit();
    }

    //###   start L10, RoomFilter  (part 4)
    @GetMapping("/filteredByHourlyRate")
    public List<Room> getFilteredRooms(@RequestParam double minRate) {
        return roomServiceQuery.getRoomsFilteredByHourlyRate(minRate);
    }
    //###   start L10, RoomFilter  (part 4)

}
//###   end L3 Facade -> Part 3 - adapting the code to use facade