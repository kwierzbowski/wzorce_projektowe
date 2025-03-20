package com.example.projback.wzorce.L7.OCP;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.function.Function;

//###   start L7 Open-Closed Principle -> Part 1 - interface (abstract)
public interface TokenProviderInterface {
    String generateToken(String username);
    String extractUsername(String token);
    Date extractExpiration(String token);
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
    Boolean validateToken(String token, UserDetails userDetails);
    Boolean isTokenExpired(String token);
}
//###   end L7 Open-Closed Principle -> Part 1