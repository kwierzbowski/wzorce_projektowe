package com.example.projback.wzorce.L3.Facade;

import com.example.projback.config.JwtUtil;
import com.example.projback.dto.LoginRequestDTO;
import com.example.projback.dto.LoginResponseDTO;
import com.example.projback.dto.ResponseDTO;
import com.example.projback.entity.User;
import com.example.projback.wzorce.L3.Proxy.AuthServiceProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

//###   start L3 Facade -> Part 1
@Service
public class AuthFacade {
    /// ### start L3 Proxy -> Part 1, changing AuthFacade by adding AuthServiceProxy instead AuthService
    private final AuthServiceProxy authServiceProxy;
    private final JwtUtil jwtUtil;

    public AuthFacade(AuthServiceProxy authServiceProxy, JwtUtil jwtUtil) {
        this.authServiceProxy = authServiceProxy;
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity<ResponseDTO<LoginResponseDTO>> login(LoginRequestDTO loginRequest) {
        boolean success = authServiceProxy.login(loginRequest.getUsername(), loginRequest.getPassword());
        try {
            if (success) {
                String token = jwtUtil.generateToken(loginRequest.getUsername());
                return ResponseEntity.ok(new ResponseDTO<>(true, "Login successful1", new LoginResponseDTO(token, null)));
            } else {
                return ResponseEntity.status(401).body(new ResponseDTO<>(false, "Invalid credentials", null));
            }
        }catch (Exception e){
            return ResponseEntity.status(401).body(new ResponseDTO<>(false, "Invalid credentials", null));
        }
    }

    public ResponseEntity<String> register(User user) {
        authServiceProxy.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }
}
//###   end L3 Facade -> Part 1