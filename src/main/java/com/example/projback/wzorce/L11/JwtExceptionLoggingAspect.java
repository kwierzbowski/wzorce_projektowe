package com.example.projback.wzorce.L11;

import io.jsonwebtoken.ExpiredJwtException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//###   start L11 AspectJ, part 2 -> logging expired token
@Aspect
@Component
public class JwtExceptionLoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(JwtExceptionLoggingAspect.class);

    @AfterThrowing(pointcut = "execution(* com.example.projback.config.JwtUtil.*(..))", throwing = "ex")
    public void logExpiredJwtException(JoinPoint joinPoint, ExpiredJwtException ex) {
        logger.warn("Wygasły token JWT! Metoda: {} - Wyjątek: {}", joinPoint.getSignature().getName(), ex.getMessage());
    }
}
//###   end L11 AspectJ, part 1 -> logging exception