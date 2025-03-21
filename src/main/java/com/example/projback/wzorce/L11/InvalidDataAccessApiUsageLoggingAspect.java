package com.example.projback.wzorce.L11;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Component;


//###   start L11 AspectJ, part 1 -> logging exception
@Aspect
@Component
public class InvalidDataAccessApiUsageLoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(InvalidDataAccessApiUsageLoggingAspect.class);

    @AfterThrowing(pointcut = "execution(* com.example.projback.repository.*.*(..))", throwing = "ex")
    public void logInvalidDataAccessApiUsageException(JoinPoint joinPoint, InvalidDataAccessApiUsageException ex) {
        logger.error("Wystąpił wyjątek InvalidDataAccessApiUsageException w metodzie {}: {}", joinPoint.getSignature().getName(), ex.getMessage());
    }
}
//###   end L11 AspectJ, part 1 -> logging exception