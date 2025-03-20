package com.example.projback.wzorce.L2.Bridge;

import com.example.projback.entity.User;

public interface UserValidator {
    boolean validate(User user);
}