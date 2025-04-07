//###   start L6 Observer
package com.example.projback.wzorce.L6.Observer;

import com.example.projback.entity.Equipment;
import org.springframework.stereotype.Component;

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
//###   end L6 Observer