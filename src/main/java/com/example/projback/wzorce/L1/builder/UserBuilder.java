package com.example.projback.wzorce.L1.builder;

import com.example.projback.entity.Role;
import com.example.projback.entity.User;

//###   start L1 Builder -> Part 4 - declaration
public class UserBuilder {
    private User user = new User();

    public UserBuilder setUsername(String username) {
        user.setUsername(username);
        return this;
    }

    public UserBuilder setPassword(String password) {
        user.setPassword(password);
        return this;
    }

    public UserBuilder setRole(Role role) {
        user.setRole(role);
        return this;
    }

    public User build() {
        return user;
    }
}
//###   end L1 Builder -> Part 4 - declaration