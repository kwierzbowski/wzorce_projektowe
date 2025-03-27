package com.example.projback.wzorce.L12;

import java.nio.charset.StandardCharsets;
import java.util.List;

//###   start L12, 2. (part 2)
public class CsvUserReport extends UserReportTemplate {

    @Override
    protected byte[] formatAndExport(List<String[]> data) {
        StringBuilder csv = new StringBuilder("Username,Role\n");
        data.forEach(row -> csv.append(String.join(",", row)).append("\n"));
        return csv.toString().getBytes(StandardCharsets.UTF_8);
    }
}
//###   end L12, 2. (part 2)