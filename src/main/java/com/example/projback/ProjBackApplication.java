package com.example.projback;

import com.example.projback.config.JwtUtil;
import com.example.projback.repository.EquipmentRepository;
import com.example.projback.repository.ReservationRepository;
import com.example.projback.service.UserService;
import com.example.projback.wzorce.L5.Mediator.EquipmentMediator;
import com.example.projback.wzorce.L5.Mediator.ReservationMediator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjBackApplication.class, args);
	}

	@Bean
	public ReservationMediator reservationMediator(UserService userService, JwtUtil jwtUtil, ReservationRepository reservationRepository) {
		return new ReservationMediator(userService, jwtUtil, reservationRepository);
	}
	@Bean
	public EquipmentMediator equipmentMediator(UserService userService, JwtUtil jwtUtil, EquipmentRepository equipmentRepository) {
		return new EquipmentMediator(userService, jwtUtil, equipmentRepository);
	}

}
