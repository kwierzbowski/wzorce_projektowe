package com.example.projback.wzorce.L8.Liskov.User.Klasy_Pochodne;

import com.example.projback.entity.Role;
import com.example.projback.entity.User;

//###   start L8 Liskov -> Klasa pochodna
public class Customer extends User {
    public Customer() {
        this.setUsername("CustomerName");
        this.setRole(Role.CUSTOMER);
    }
}
//###   end L8 Liskov -> Klasa pochodna