package com.example.projback.wzorce.L6.Observer;

import com.example.projback.entity.Equipment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//###   start L6 Observer
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
//###   end L6 Observer