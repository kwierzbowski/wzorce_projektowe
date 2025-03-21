package com.example.projback.wzorce.L11;

import com.example.projback.dto.LoginRequestDTO;
import com.example.projback.dto.LoginResponseDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

//###   start L11 AspectJ, part 3 -> logging user login
@Aspect
@Component
public class LoginAttemptLoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoginAttemptLoggingAspect.class);

    @Before("execution(* com.example.projback.controller.AuthController.login(..)) && args(loginRequest,..)")
    public void logLoginAttempt(JoinPoint joinPoint, LoginRequestDTO loginRequest) {
        if (loginRequest != null && loginRequest.getUsername() != null) {
            logger.info("Próba logowania użytkownika: {}", loginRequest.getUsername());
        } else {
            logger.warn("Próba logowania, ale brak danych użytkownika w żądaniu!");
        }
    }
}
//###   start L11 AspectJ, part 3 -> logging user login
