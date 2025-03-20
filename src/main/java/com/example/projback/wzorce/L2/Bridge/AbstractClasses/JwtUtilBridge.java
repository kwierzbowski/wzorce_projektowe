package com.example.projback.wzorce.L2.Bridge.AbstractClasses;

import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Date;

//###   start L2 Bridge -> JwtUtil -> Klasa abstrakcyjna
public abstract class JwtUtilBridge {

    protected TokenService tokenService;

    public JwtUtilBridge(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public abstract String generateToken(String username);

    public abstract String extractUsername(String token);

    public abstract Date extractExpiration(String token);

    public abstract boolean isTokenExpired(String token);

    public abstract boolean validateToken(String token, UserDetails userDetails);
}
//###   end L2 Bridge -> JwtUtil -> Klasa abstrakcyjna