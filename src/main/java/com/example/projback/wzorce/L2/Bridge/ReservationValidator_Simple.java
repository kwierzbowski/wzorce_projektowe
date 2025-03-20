package com.example.projback.wzorce.L2.Bridge;

import com.example.projback.dto.MakeReservationDTO;
import com.example.projback.entity.Room;
//import com.example.projback.wzorce.L8.OdwracanieZaleznosci.IRoomService;
import com.example.projback.wzorce.L8.OdwracanieZaleznosci.SegragacjaInterfejsow.Room.IRoomService_Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("simpleValidator_Reservation")
public class ReservationValidator_Simple implements ReservationValidator {
//    private final IRoomService roomService;
    private final IRoomService_Query roomServiceQuery;

    @Autowired
    public ReservationValidator_Simple(IRoomService_Query roomServiceQuery) {
        this.roomServiceQuery = roomServiceQuery;
    }

    @Override
    public boolean validate(MakeReservationDTO reservation) {
        if (!reservation.getStartTime().before(reservation.getEndTime())) {
            return false;
        }

        Room room = roomServiceQuery.findRoomById(reservation.getRoom());
        boolean available = roomServiceQuery.findAvailableRooms(reservation.getStartTime(), reservation.getEndTime()).contains(room);
        return available;
    }
}
