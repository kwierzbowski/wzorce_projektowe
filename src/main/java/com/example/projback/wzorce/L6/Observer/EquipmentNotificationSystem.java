package com.example.projback.wzorce.L6.Observer;

import com.example.projback.entity.Equipment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//###   start L6 Observer

//###   start L9, 1.  znaczących (jasnych i zrozumiałych) nazw do klas, metod i zmiennych, znaczących w całym programie to samo (bez synonimów)
//@Component
//public class ItemNotificationSystem {
//    private final List<ItemObserver> observers = new ArrayList<>();
//
//    public ItemNotificationSystem(ItemLogger itemLogger) {
//        observers.add(itemLogger);
//    }
//
//    public void notifyObservers(String eventType, Item item) {
//        for (ItemObserver observer : observers) {
//            observer.onItemChange(itemType, item);
//        }
//    }
//}

@Component
public class EquipmentNotificationSystem {
    private final List<EquipmentObserver> observers = new ArrayList<>();

    public EquipmentNotificationSystem(EquipmentLogger equipmentLogger) {
        observers.add(equipmentLogger);
    }

    public void notifyObservers(String eventType, Equipment equipment) {
        for (EquipmentObserver observer : observers) {
            observer.onEquipmentChange(eventType, equipment);
        }
    }
}
//###   end L9, 1.  znaczących (jasnych i zrozumiałych) nazw do klas, metod i zmiennych, znaczących w całym programie to samo (bez synonimów)

//###   end L6 Observer