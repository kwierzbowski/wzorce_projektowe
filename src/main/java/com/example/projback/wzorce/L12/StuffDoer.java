package com.example.projback.wzorce.L12;

//###   start L12, 1.
// 1. utwórz klasę, która ma około 100 linii kodu, miesza odpowiedzialności, stosuje niewłaściwe nazwy, długie metody, dużo komentarzy, metody maja wiele argumentów
// 2. popraw tą klasę zgodnie z poznanymi zasadami

import com.example.projback.entity.User;
import com.example.projback.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Component
public class StuffDoer {

    private final UserRepository x;

    public StuffDoer(UserRepository x) {
        this.x = x;
    }

    public void doAllTheThings(String type, boolean includeDate, String pathToFile, boolean uppercaseHeaders, String separator, String fileExtension) {
        System.out.println("Rozpoczęcie.");

        List<User> y = x.findAll();
        Date z = new Date();

        if (includeDate) {
            System.out.println("Data generacji: " + z);
        }

        // drukuj do konsoli
        for (User a : y) {
            String s = a.getUsername();
            String r = a.getRole().toString();

            if (uppercaseHeaders) {
                System.out.println("LOGIN: " + s);
                System.out.println("ROLA: " + r);
            } else {
                System.out.println("login: " + s);
                System.out.println("rola: " + r);
            }
            System.out.println("------");
        }

        File file = new File(pathToFile + "." + fileExtension);
        file.getParentFile().mkdirs();

        // zapis do pliku
        try {
            FileWriter fw = new FileWriter(pathToFile + "." + fileExtension);
            if (uppercaseHeaders) {
                fw.write("LOGIN" + separator + "ROLA\n");
            } else {
                fw.write("login" + separator + "rola\n");
            }

            for (User u : y) {
                fw.write(u.getUsername() + separator + u.getRole().toString() + "\n");
            }

            fw.close();
        } catch (IOException e) {
            System.out.println("Cos poszło nie tak przy zapisie.");
        }

        System.out.println("Koniec.");
    }
}
//###   end L12, 1.