package com.example.projback.wzorce.L12;

import com.example.projback.entity.User;

import java.util.List;

//###   start L12, 2. (part 1)
public abstract class UserReportTemplate {

    // Template method
    public final byte[] generateReport(List<User> users) {
        List<String[]> rawData = prepareData(users);
        return formatAndExport(rawData);
    }

    // Krok wspólny dla wszystkich raportów
    protected List<String[]> prepareData(List<User> users) {
        return users.stream()
                .map(u -> new String[]{u.getUsername(), u.getRole().name()})
                .toList();
    }

    // Krok różny dla każdego formatu
    protected abstract byte[] formatAndExport(List<String[]> data);
}
//###   end L12, 2. (part 1)