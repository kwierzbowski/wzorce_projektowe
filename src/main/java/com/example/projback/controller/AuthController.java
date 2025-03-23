package com.example.projback.controller;

import com.example.projback.config.JwtUtil;
import com.example.projback.dto.LoginRequestDTO;
import com.example.projback.dto.LoginResponseDTO;
import com.example.projback.dto.ResponseDTO;
import com.example.projback.entity.Role;
import com.example.projback.entity.User;
import com.example.projback.service.UserService;
import com.example.projback.wzorce.L10.UserFilter;
import com.example.projback.wzorce.L3.Facade.AuthFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//###   start L3 Facade -> Part 1 - adapting the code to use facade
@RestController
@RequestMapping("api/auth/")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthFacade authFacade;

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(AuthFacade authFacade, UserService userService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.authFacade = authFacade;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // Method to handle register request
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody User user){
        try {
            userService.registerUser(user);
            return ResponseEntity.ok("User registered successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // Method to handle login request
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<LoginResponseDTO>> login(@RequestBody LoginRequestDTO loginRequest) {
        return authFacade.login(loginRequest);
    }


//    @PostMapping("/login")
//    public ResponseEntity<ResponseDTO<LoginResponseDTO>> login(@RequestBody LoginRequestDTO loginRequest) {
//        String username = loginRequest.getUsername();
//        String password = loginRequest.getPassword();
//
//        try {
//            User user = userService.getUserByUsername(username)
//                    .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
//
//            if (!passwordEncoder.matches(password, user.getPassword())) {
//                return ResponseEntity.badRequest().body(
//                        new ResponseDTO<>(false, "Invalid username or password", null)
//                );
//            }
//
//            String token = jwtUtil.generateToken(user.getUsername());
//            LoginResponseDTO responseDTO = new LoginResponseDTO(token, user.getRole());
//            return ResponseEntity.ok(new ResponseDTO<>(true, "Login successful", responseDTO));
//
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(
//                    new ResponseDTO<>(false, e.getMessage(), null)
//            );
//        }
//    }

    //###   start L10, UserFilter (part 3)
    @GetMapping("/users-by-role")
    public ResponseEntity<List<User>> getUsersByRole(@RequestParam("role") Role role) {
        return ResponseEntity.ok(userService.getUsersByRole(role));
    }

    //###   end L10, UserFilter (part 3)
}

