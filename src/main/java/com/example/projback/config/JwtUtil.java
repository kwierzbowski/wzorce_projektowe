package com.example.projback.config;

import com.example.projback.wzorce.L7.OCP.TokenProviderInterface;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;
//###   start L7 Open-Closed Principle -> Part 1 - code opened for extension, closed for modification
//###   start L7 Single Responsibility -> Part 1 - only operations with JWT
@Component
public class JwtUtil implements TokenProviderInterface{
    private final String SECRET_KEY;

    //###   start L1 Singleton -> Part 1 - Eager Initialization
    public static final JwtUtil INSTANCE = new JwtUtil();
    public JwtUtil() {
        this.SECRET_KEY = "s3cr3tKeyGeneratedUsingBase64Tool==1231231231231231231231231";
    }
    public static JwtUtil getInstance() {
        return INSTANCE;
    }
    //###   end L1 Singleton -> Part 1 - Eager Initialization

//    private static final String SECRET_KEY = "s3cr3tKeyGeneratedUsingBase64Tool==1231231231231231231231231";

    // Method to return username
    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Method to return date of token expiration
    @Override
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Method to return required data form token
    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Method to return all data from token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Method to check if the token has not expired
    @Override
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Method to generate token
    @Override
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 godzin
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }


    // Method to validate token with userDetails
    @Override
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}

//###   end L7 Single Responsibility -> Part 1
//###   end L7 Open-Closed Principle -> Part 1
