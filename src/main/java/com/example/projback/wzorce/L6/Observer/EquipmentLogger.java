package com.example.projback.wzorce.L6.Observer;

import com.example.projback.entity.Equipment;
import org.springframework.stereotype.Component;

//###   start L6 Observer (part 2)

//###   start L9, 1. (part 1) znaczących (jasnych i zrozumiałych) nazw do klas, metod i zmiennych, znaczących w całym programie to samo (bez synonimów)

//@Component
//public class ItemLogger implements ItemObserver {
//
//    @Override
//    public void onItemChange(String eventType, Item item) {
//        String message = (item == null)
//                ? "Otrzymano powiadomienie: " + eventType + ", ale sprzęt jest NULL!"
//                : "L6 | OBSERVER | Equipment event: " + eventType + " -> " + item;
//
//        System.out.println(message);
//    }
//}

@Component
public class EquipmentLogger implements EquipmentObserver {

    @Override
    public void onEquipmentChange(String eventType, Equipment equipment) {
        String message = (equipment == null)
                ? "Otrzymano powiadomienie: " + eventType + ", ale sprzęt jest NULL!"
                : "L6 | OBSERVER | Equipment event: " + eventType + " -> " + equipment;

        System.out.println(message);
    }
}

//###   end L9, 1. (part 1) znaczących (jasnych i zrozumiałych) nazw do klas, metod i zmiennych, znaczących w całym programie to samo (bez synonimów)

//###   end L6 Observer (part 2)