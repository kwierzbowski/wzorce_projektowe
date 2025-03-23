package com.example.projback.wzorce.L5.Mediator;

import com.example.projback.config.JwtUtil;
import com.example.projback.entity.Reservation;
import com.example.projback.entity.Room;
import com.example.projback.entity.User;
import com.example.projback.repository.ReservationRepository;
import com.example.projback.service.UserService;

//###   start L5 Mediator -> Reservation
public class ReservationMediator implements Mediator {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final ReservationRepository reservationRepository;

    public ReservationMediator(UserService userService, JwtUtil jwtUtil, ReservationRepository reservationRepository) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.reservationRepository = reservationRepository;
    }

    private User extractUser(String token) {
        String username = jwtUtil.extractUsername(token.substring(7));
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (jwtUtil.isTokenExpired(token.substring(7))) {
            throw new RuntimeException("Token is expired");
        }
        return user;
    }

    @Override
    public Room notify(Object sender, String action, String token) {
        try {
//            String username = jwtUtil.extractUsername(token.substring(7));
//            User user = userService.findByUsername(username)
//                    .orElseThrow(() -> new RuntimeException("User not found"));
//
//            if (jwtUtil.isTokenExpired(token.substring(7))) {
//                throw new RuntimeException("Token is expired");
//            }
            User user = extractUser(token);
            switch (action) {
                case "create":
                    createReservation((Reservation) sender, user);
                    break;
                default:
                    throw new IllegalArgumentException("Nieznana akcja");
            }

        } catch (Exception e) {
            throw new RuntimeException("Bład: " + e.getMessage(), e);
        }
        return null;
    }

    private void createReservation(Reservation reservation, User user) {
        reservation.setUserId(user.getId());
        reservationRepository.save(reservation);
    }
}
//###   end L5 Mediator -> Reservation