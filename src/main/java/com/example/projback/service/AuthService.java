package com.example.projback.service;

import com.example.projback.entity.User;
import com.example.projback.repository.UserRepository;
import com.example.projback.wzorce.L7.OCP.AuthenticationProviderInterface;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//###   start L7 Open-Closed Principle -> Part 2, code opened for extension, closed for modification
@Service
public class AuthService implements UserDetailsService, AuthenticationProviderInterface {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //###   start L1 Singleton -> Part 2 - Lazy Initialization
    private static AuthService instance;
    public static synchronized AuthService getInstance(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        if (instance == null) {
            instance = new AuthService(userRepository, passwordEncoder);
        }
        return instance;
    }
    //###   end L1 Singleton -> Part 2 - Lazy Initialization

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().toString())
                .build();
    }

    //###   start L9 c,d , Part 2
    @Override
    public void registerUser(User user) {
        if (isUsernameExist(user.getUsername())) {
            throw new IllegalArgumentException("User already exists");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(encodedPassword);
        userRepository.save(newUser);
    }

    public boolean isUsernameExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
    //###   end L9 c,d , Part 2
}
//###   end L7 Open-Closed Principle -> Part 2