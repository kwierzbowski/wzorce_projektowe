package com.example.projback.wzorce.L1.factory;

import com.example.projback.entity.Role;
import com.example.projback.entity.User;

//###   start L1 Factory -> Part 4 - declaration
public class UserFactory {
    public static User createUser(String username, String password, Role role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        return user;
    }
}
//###   start L1 Factory -> Part 4 - declaration