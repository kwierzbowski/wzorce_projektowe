package com.example.projback.wzorce.L3.Proxy;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.example.projback.entity.User;

//###   start L3 Proxy -> Part 1, interface
public interface AuthServiceInterface extends UserDetailsService {
    void registerUser(User user);
}
//###   end L3 Proxy -> Part 1, interface