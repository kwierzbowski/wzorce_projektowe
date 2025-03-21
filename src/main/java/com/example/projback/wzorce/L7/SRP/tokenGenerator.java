package com.example.projback.wzorce.L7.SRP;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

//###   start L7 Single responsibility principle, part 2 -> class for generate token
@Component
public class tokenGenerator {
    private final String SECRET_KEY = "s3cr3tKeyGeneratedUsingBase64Tool==1231231231231231231231231";

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 godzin
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
//###   start L7 Single responsibility principle, part 2 -> class for generate token