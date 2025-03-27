package com.example.projback.wzorce.L12;

public class UserReportGenerator {

    //###   start L12, 2. (part 4)
    public static UserReportTemplate getStrategy(String format) {
        return switch (format.toLowerCase()) {
            case "csv" -> new CsvUserReport();
            case "xlsx" -> new XlsxUserReport();
            default -> throw new IllegalArgumentException("Nieobsługiwany format: " + format);
        };
    }
    //###   start L12, 2. (part 4)
}