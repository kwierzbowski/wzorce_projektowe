package com.example.projback.wzorce.L10;

import com.example.projback.entity.Equipment;

//###   start L10, EquipmentFilter (part 1)
// 1. utwórz 3 interfejsy funkcyjne, wykonaj ich implementacje i napisz kod używający instancji tych interfejsów poprzez zastosowanie wyrażenia lambda
// 2. pokaż na przykładzie wykorzystanie programowania funkcyjnego w strumieniowym przetwarzaniu 3 kolekcji

@FunctionalInterface
public interface EquipmentFilter {
    boolean test(Equipment equipment);
}
//###   end L10, EquipmentFilter (part 1)