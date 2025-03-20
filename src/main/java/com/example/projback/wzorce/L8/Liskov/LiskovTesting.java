package com.example.projback.wzorce.L8.Liskov;

import com.example.projback.entity.Equipment;
import com.example.projback.entity.Room;
import com.example.projback.entity.User;
import com.example.projback.wzorce.L8.Liskov.Equipment.Klasy_Pochodne.Chair;
import com.example.projback.wzorce.L8.Liskov.Equipment.Klasy_Pochodne.Speaker;
import com.example.projback.wzorce.L8.Liskov.Room.Klasy_Pochodne.ConferenceRoom;
import com.example.projback.wzorce.L8.Liskov.Room.Klasy_Pochodne.SmallRoom;
import com.example.projback.wzorce.L8.Liskov.User.Klasy_Pochodne.Customer;
import com.example.projback.wzorce.L8.Liskov.User.Klasy_Pochodne.Employee;


//###   start L8 Liskov -> Wykazanie zamienności obiektów (Klasa - klasapochodna)
public class LiskovTesting {
    public static void main(String[] args) {
        Room room1 = new SmallRoom();
        Room room2 = new ConferenceRoom();
        Equipment equipment1 = new Speaker();
        Equipment equipment2 = new Chair();
        User user1 = new Employee();
        User user2 = new Customer();

        System.out.println(room1.getType());
        System.out.println(room2.getType());

        System.out.println(equipment1.getName() + "   " + equipment1.getPrice());
        System.out.println(equipment2.getName() + "   " + equipment2.getPrice());

        System.out.println(user1.getUsername() + "   " + user1.getRole());
        System.out.println(user2.getUsername() + "   " + user2.getRole());

    }
}
//###   end L8 Liskov -> Wykazanie zamienności obiektów (Klasa - klasapochodna)
