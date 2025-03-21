package com.example.projback.wzorce.L7.SRP;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

//###   start L7 Single responsibility principle, part 3 -> class for validate token
@Component
public class tokenValidator {
    private final tokenExtract tokenExtract;

    public tokenValidator(tokenExtract tokenExtract) {
        this.tokenExtract = tokenExtract;
    }

    public boolean isTokenExpired(String token) {
        return tokenExtract.getExtractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = tokenExtract.getExtractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
//###   start L7 Single responsibility principle, part 3 -> class for validate token