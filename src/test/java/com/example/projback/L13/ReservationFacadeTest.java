package com.example.projback.L13;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.projback.dto.MakeReservationDTO;
import com.example.projback.dto.UpdateReservationEmployeeDTO;
import com.example.projback.entity.Reservation;
import com.example.projback.entity.ReservationStatus;
import com.example.projback.wzorce.L3.Facade.ReservationFacade;
import com.example.projback.wzorce.L3.Proxy.ReservationServiceProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.List;
//###   start L13 testy jednostkowe, part 3
class ReservationFacadeTest {

    @Mock
    private ReservationServiceProxy reservationServiceProxy;

    @InjectMocks
    private ReservationFacade reservationFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateReservation() {
        MakeReservationDTO dto = new MakeReservationDTO();
        String token = "testToken";

        doNothing().when(reservationServiceProxy).createReservation(dto, token);

        ResponseEntity<String> response = reservationFacade.createReservation(dto, token);
        assertEquals("Reservation created", response.getBody());
    }

    @Test
    void testDeleteReservation() {
        Long reservationId = 1L;
        String token = "testToken";

        doNothing().when(reservationServiceProxy).deleteReservation(reservationId, token);

        ResponseEntity<String> response = reservationFacade.deleteReservation(reservationId, token);
        assertEquals("Reservation deleted", response.getBody());
    }


    @Test
    void testUpdateReservationStatusAndPrice() {
        Long reservationId = 1L;
        UpdateReservationEmployeeDTO dto = new UpdateReservationEmployeeDTO();
        String token = "testToken";

        doNothing().when(reservationServiceProxy).updateReservationStatusAndPrice(reservationId, dto, token);

        ResponseEntity<String> response = reservationFacade.updateReservationStatusAndPrice(reservationId, dto, token);
        assertEquals("Reservation status and price updated", response.getBody());
    }

    @Test
    void testGetReservationsByUser() {
        String token = "testToken";
        List<Reservation> reservations = List.of(new Reservation());

        when(reservationServiceProxy.getReservationsByUser(token)).thenReturn(reservations);

        ResponseEntity<List<Reservation>> response = reservationFacade.getReservationsByUser(token);
        assertEquals(reservations, response.getBody());
    }

    @Test
    void testGetReservationsByStatus() {
        ReservationStatus status = ReservationStatus.APPROVED;
        String token = "testToken";
        List<Reservation> reservations = List.of(new Reservation());

        when(reservationServiceProxy.getReservationsByStatusOrAll(status, token)).thenReturn(reservations);

        ResponseEntity<List<Reservation>> response = reservationFacade.getReservations(status, token);
        assertEquals(reservations, response.getBody());
    }
}
//###   end L13 testy jednostkowe, part 3