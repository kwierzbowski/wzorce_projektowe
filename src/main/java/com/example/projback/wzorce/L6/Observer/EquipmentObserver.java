package com.example.projback.wzorce.L6.Observer;

import com.example.projback.entity.Equipment;

//###   start L6 Observer

//###   start L9, 1.  znaczących (jasnych i zrozumiałych) nazw do klas, metod i zmiennych, znaczących w całym programie to samo (bez synonimów)
//public interface ItemObserver {
//    void onItemChange(String eventType, Item item);
//}
//###   end L9, 1.  znaczących (jasnych i zrozumiałych) nazw do klas, metod i zmiennych, znaczących w całym programie to samo (bez synonimów)=

public interface EquipmentObserver {
    void onEquipmentChange(String eventType, Equipment equipment);
}
//###   end L6 Observer