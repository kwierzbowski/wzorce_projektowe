package com.example.projback.wzorce.L8.Liskov.Equipment.Klasy_Pochodne;

import com.example.projback.entity.Equipment;

//###   start L8 Liskov -> Klasa pochodna
public class Speaker extends Equipment {
    public Speaker() {
        this.setName("Speaker");
        this.setPrice(100.0);
    }
}
//###   end L8 Liskov -> Klasa pochodna