package com.example.projback.wzorce.L2.Adapter;

import com.example.projback.entity.User;

//###   start L2 Adapter -> Part 4
public class UserAdapter implements AdapterInterface {
    private final User user;

    public UserAdapter(User user) {
        this.user = user;
    }

    @Override
    public String getName() {
        return user.getUsername();
    }

    @Override
    public Long getId() {
        return user.getId();
    }

}
//###   end L2 Adapter -> Part 4