package com.example.projback.wzorce.L8.Liskov.Equipment.Klasy_Pochodne;

import com.example.projback.entity.Equipment;

//###   start L8 Liskov -> Klasa pochodna
public class Chair  extends Equipment {
    public Chair() {
        this.setName("Chair");
        this.setPrice(20.0);
    }
}
//###   end L8 Liskov -> Klasa pochodna