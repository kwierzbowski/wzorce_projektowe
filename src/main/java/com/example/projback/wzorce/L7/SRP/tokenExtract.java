package com.example.projback.wzorce.L7.SRP;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;
//###   start L7 Single responsibility principle, part 1 -> class for extract token
@Component
public class tokenExtract {
    private final String SECRET_KEY = "s3cr3tKeyGeneratedUsingBase64Tool==1231231231231231231231231";

    public String getExtractUsername(String token) {
        return getExtractClaim(token, Claims::getSubject);
    }

    public Date getExtractExpiration(String token) {
        return getExtractClaim(token, Claims::getExpiration);
    }

    public <T> T getExtractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getExtractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims getExtractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
//###   end L7 Single responsibility principle, part 1 -> class for extract token