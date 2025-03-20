package com.example.projback.wzorce.L5.Command;

import com.example.projback.entity.Equipment;
import com.example.projback.entity.User;
import com.example.projback.repository.UserRepository;

//###   start L5 Command -> User
public class User_Create_Command implements Command {
    private User user;
    private UserRepository userRepository;

    public User_Create_Command(User user, UserRepository userRepository) {
        this.user = user;
        this.userRepository = userRepository;
    }

    @Override
    public Equipment execute() {
        userRepository.save(user);
        return null;
    }
}
//###   end L5 Command -> User