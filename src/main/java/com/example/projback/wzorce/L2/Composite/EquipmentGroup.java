package com.example.projback.wzorce.L2.Composite;

import java.util.ArrayList;
import java.util.List;

//###   start L2 Composite -> Part 2
public class EquipmentGroup implements EquipmentComponent {

    private String groupName;
    private List<EquipmentComponent> components = new ArrayList<>();

    public EquipmentGroup(String groupName) {
        this.groupName = groupName;
    }

    public void addComponent(EquipmentComponent component) {
        components.add(component);
    }

    public void removeComponent(EquipmentComponent component) {
        components.remove(component);
    }

    @Override
    public String getName() {
        return groupName;
    }

    @Override
    public Double getPrice() {
        return components.stream().mapToDouble(EquipmentComponent::getPrice).sum();
    }

    @Override
    public void displayInfo() {
        System.out.println("Grupa elementów wyposażenia: " + groupName + " cena: " + getPrice());
        for (EquipmentComponent component : components) {
            component.displayInfo();
        }
    }
}
//###   end L2 Composite -> Part 2