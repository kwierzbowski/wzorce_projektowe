package com.example.projback.wzorce.L8.Liskov.User.Klasy_Pochodne;

import com.example.projback.entity.Role;
import com.example.projback.entity.User;

//###   start L8 Liskov -> Klasa pochodna
public class Employee extends User {
    public Employee() {
        this.setUsername("EmployeeName");
        this.setRole(Role.EMPLOYEE);
    }
}

//###   end L8 Liskov -> Klasa pochodna