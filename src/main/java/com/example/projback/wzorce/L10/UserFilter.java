package com.example.projback.wzorce.L10;

import com.example.projback.entity.User;

//###   start L10, UserFilter (part 1)
@FunctionalInterface
public interface UserFilter {
    boolean test(User user);
}
//###   end L10, UserFilter (part 1)