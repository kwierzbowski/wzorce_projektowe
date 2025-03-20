package com.example.projback.wzorce.L5.Mediator;

import com.example.projback.entity.Room;

//###   start L5 Mediator
public interface Mediator {
    Room notify(Object sender, String event, String token);
}
//###   end L5 Mediator