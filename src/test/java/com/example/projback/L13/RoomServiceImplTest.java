package com.example.projback.L13;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.projback.config.JwtUtil;
import com.example.projback.dto.RoomAvailableIgnoringReservationDTO;
import com.example.projback.entity.Role;
import com.example.projback.entity.Room;
import com.example.projback.entity.RoomType;
import com.example.projback.entity.User;
import com.example.projback.repository.ReservationRepository;
import com.example.projback.repository.RoomRepository;
import com.example.projback.service.RoomServiceImpl;
import com.example.projback.service.UserService;
import com.example.projback.wzorce.L5.Mediator.RoomMediator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

//###   start L13 testy jednostkowe, part 4
@ExtendWith(MockitoExtension.class)
class RoomServiceImplTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserService userService;

    @Mock
    private RoomMediator roomMediator;

    @InjectMocks
    private RoomServiceImpl roomService;

    private User employee;
    private Room testRoom;

    @BeforeEach
    void setUp() {
        employee = new User();
        employee.setRole(Role.EMPLOYEE);
        employee.setUsername("employeeUser");

        testRoom = new Room();
        testRoom.setId(1L);
        testRoom.setName("Conference Room");
        testRoom.setPricePerDay(100.0);
        testRoom.setPricePerHour(10.0);
    }

    @Test
    void findRoomById() {
        when(roomRepository.findById(1L)).thenReturn(Optional.of(testRoom));

        Room result = roomService.findRoomById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void deleteRoomById() {
        String token = "Bearer validToken";
        when(jwtUtil.extractUsername(anyString())).thenReturn("employeeUser");
        when(userService.findByUsername("employeeUser")).thenReturn(Optional.of(employee));
        when(jwtUtil.isTokenExpired(anyString())).thenReturn(false);
        doNothing().when(roomRepository).deleteById(1L);

        String result = roomService.deleteRoomById(1L, token);

        assertEquals("Usunięto", result);
    }

    @Test
    void updateRoom() {
        String token = "Bearer validToken";
        when(jwtUtil.extractUsername(anyString())).thenReturn("employeeUser");
        when(userService.findByUsername("employeeUser")).thenReturn(Optional.of(employee));
        when(jwtUtil.isTokenExpired(anyString())).thenReturn(false);
        when(roomRepository.findById(1L)).thenReturn(Optional.of(testRoom));
        when(roomRepository.save(any(Room.class))).thenReturn(testRoom);

        Room updatedRoom = new Room();
        updatedRoom.setName("Updated Room");
        updatedRoom.setPricePerDay(150.0);
        updatedRoom.setPricePerHour(15.0);
        updatedRoom.setType(RoomType.SMALL_ROOM);

        Room result = roomService.updateRoom(updatedRoom, token, 1L);

        assertNotNull(result);
        assertEquals("Updated Room", result.getName());
    }

    @Test
    void isEquipmentAvailable() {
        List<Long> equipment = Arrays.asList(1L, 2L);
        testRoom.setOptionalEquipment(equipment);
        when(roomRepository.findById(1L)).thenReturn(Optional.of(testRoom));

        boolean result = roomService.isEquipmentAvailable(1L, equipment);

        assertTrue(result);
    }

    @Test
    void isRoomAvailableIgnoringReservation() {
        RoomAvailableIgnoringReservationDTO dto = new RoomAvailableIgnoringReservationDTO();
        dto.setRoomId(1L);
        dto.setStartTime(new Date());
        dto.setEndTime(new Date(System.currentTimeMillis() + 3600000));
        dto.setReservationIdToIgnore(99L);

        when(reservationRepository.findReservationsForRoom(anyLong(), any(), any())).thenReturn(Collections.emptyList());

        boolean result = roomService.isRoomAvailableIgnoringReservation(dto);

        assertTrue(result);
    }
}
//###   end L13 testy jednostkowe, part 4