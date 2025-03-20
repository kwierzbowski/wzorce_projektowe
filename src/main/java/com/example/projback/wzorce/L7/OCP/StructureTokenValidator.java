package com.example.projback.wzorce.L7.OCP;

import org.springframework.stereotype.Service;

//###   start L7 Open-Closed Principle -> Part 3 - example usage
@Service
public class StructureTokenValidator implements TokenValidatorInterface {
    @Override
    public boolean validate(String token) {
        return token.matches("^[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+$");
    }

}
//###   end L7 Open-Closed Principle -> Part 3