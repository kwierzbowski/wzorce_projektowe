package com.example.projback.wzorce.L7.OCP;

import com.example.projback.config.JwtUtil;

//###   start L7 Open-Closed Principle -> Part 3, example usage
public class ExpirationTokenValidator  implements TokenValidatorInterface {
    private final JwtUtil jwtUtil;

    public ExpirationTokenValidator(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean validate(String token) {
        return !jwtUtil.isTokenExpired(token);
    }
}
//###   end L7 Open-Closed Principle -> Part 3