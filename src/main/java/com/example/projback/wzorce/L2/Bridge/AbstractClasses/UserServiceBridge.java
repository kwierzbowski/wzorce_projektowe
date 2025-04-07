package com.example.projback.wzorce.L2.Bridge.AbstractClasses;


import com.example.projback.entity.User;
import com.example.projback.repository.UserRepository;
import com.example.projback.wzorce.L2.Bridge.UserValidator;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

//###   start L2 Bridge -> UserService -> Klasa abstrakcyjna
public abstract class UserServiceBridge {
    protected final UserRepository userRepository;
    protected final PasswordEncoder passwordEncoder;
    protected final UserValidator validator;

    public UserServiceBridge(UserRepository userRepository, PasswordEncoder passwordEncoder, UserValidator validator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
    }

    public abstract void registerUser(User user);

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
//###   end L2 Bridge -> UserService -> Klasa abstrakcyjna