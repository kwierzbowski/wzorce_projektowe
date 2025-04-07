package com.example.projback.wzorce.L2.Bridge;

import com.example.projback.entity.User;
import com.example.projback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;


@Component("advancedValidator_User")
public class UserValidator_Advanced implements UserValidator {
    private final UserRepository userRepository;

    @Autowired
    public UserValidator_Advanced(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private boolean isValidPassword(String password) {
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return Pattern.matches(passwordPattern, password);
    }

    @Override
    public boolean validate(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return false;
        }
        if (!isValidPassword(user.getPassword())) {
            return false;
        }
        return true;
    }
}
