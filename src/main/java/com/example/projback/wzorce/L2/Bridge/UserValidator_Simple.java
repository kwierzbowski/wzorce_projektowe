package com.example.projback.wzorce.L2.Bridge;

import com.example.projback.entity.User;
import com.example.projback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("simpleValidator_User")
public class UserValidator_Simple implements UserValidator {
    private final UserRepository userRepository;

    @Autowired
    public UserValidator_Simple(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean validate(User user) {
        return userRepository.findByUsername(user.getUsername()).isEmpty();
    }
}