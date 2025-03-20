package com.example.projback.wzorce.L7.OCP;

import com.example.projback.entity.User;

//###   start L7 Open-Closed Principle -> Part 2 - interface - abstract
public interface AuthenticationProviderInterface {
    void registerUser(User user);
}
//###   end L7 Open-Closed Principle -> Part 2