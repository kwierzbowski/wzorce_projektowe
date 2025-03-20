package com.example.projback.wzorce.L3.Proxy;

import com.example.projback.entity.User;
import com.example.projback.repository.UserRepository;
import com.example.projback.service.AuthService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

//###   start L3 Proxy -> Part 1, additional security with limited
@Service
public class AuthServiceProxy implements AuthServiceInterface {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Przechowywanie liczby nieudanych prób logowania
    private final Map<String, Integer> failedLoginAttempts = new HashMap<>();
    private final int MAX_ATTEMPTS = 3; // Maksymalna liczba prób logowania

    public AuthServiceProxy(AuthService authService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authService.loadUserByUsername(username);
    }

    @Override
    public void registerUser(User user) {
        authService.registerUser(user);
    }

    // Nowa metoda sprawdzająca logowanie
    public boolean login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (failedLoginAttempts.getOrDefault(username, 0) >= MAX_ATTEMPTS) {
            throw new RuntimeException("Too many failed login attempts. Account locked.");
        }

        boolean isPasswordValid = passwordEncoder.matches(password, user.getPassword());
        if (isPasswordValid) {
            failedLoginAttempts.put(username, 0); // Reset prób po poprawnym logowaniu
            return true;
        } else {
            failedLoginAttempts.put(username, failedLoginAttempts.getOrDefault(username, 0) + 1);
            throw new RuntimeException("Invalid username or password.");
        }
    }
}
//###   start L3 Proxy -> Part 1