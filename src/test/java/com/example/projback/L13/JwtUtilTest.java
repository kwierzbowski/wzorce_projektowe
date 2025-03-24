package com.example.projback.L13;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.projback.config.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
//###   start L13 testy jednostkowe, part 2
class JwtUtilTest {
    private JwtUtil jwtUtil;

    private final String SECRET_KEY = "s3cr3tKeyGeneratedUsingBase64Tool==1231231231231231231231231";
    private final String USERNAME = "testUser";
    private String token;

    @Mock
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtUtil = JwtUtil.getInstance();

        token = Jwts.builder()
                .setSubject(USERNAME)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 godzina
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    @Test
    void testGenerateToken() {
        String generatedToken = jwtUtil.generateToken(USERNAME);
        assertNotNull(generatedToken);
    }

    @Test
    void testExtractUsername() {
        String extractedUsername = jwtUtil.extractUsername(token);
        assertEquals(USERNAME, extractedUsername);
    }

    @Test
    void testTokenIsValid() {
        when(userDetails.getUsername()).thenReturn(USERNAME);
        assertTrue(jwtUtil.validateToken(token, userDetails));
    }

    @Test
    void testTokenIsExpired() throws InterruptedException {
        // token, który już wygasł
        String expiredToken = Jwts.builder()
                .setSubject(USERNAME)
                .setIssuedAt(new Date(System.currentTimeMillis() - 1000 * 60 * 61)) // 61min temu
                .setExpiration(new Date(System.currentTimeMillis() - 1000 *10)) // Wygasł 10sec temu
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        assertThrows(ExpiredJwtException.class, () -> jwtUtil.isTokenExpired(expiredToken));
    }

    @Test
    void testExtractExpiration() {
        Date expirationDate = jwtUtil.extractExpiration(token);
        assertNotNull(expirationDate);
        assertTrue(expirationDate.after(new Date())); // Sprawdzamy, czy data wygaśnięcia jest w przyszłości
    }
}
//###   start L13 testy jednostkowe, part 2