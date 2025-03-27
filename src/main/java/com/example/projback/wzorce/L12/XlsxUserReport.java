package com.example.projback.wzorce.L12;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

//###   start L12, 2. (part 3)
public class XlsxUserReport extends UserReportTemplate {

    @Override
    protected byte[] formatAndExport(List<String[]> data) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Users");
            int rowIdx = 0;

            Row header = (Row) sheet.createRow(rowIdx++);
            header.createCell(0).setCellValue("Username");
            header.createCell(1).setCellValue("Role");

            for (String[] rowData : data) {
                Row row = (Row) sheet.createRow(rowIdx++);
                for (int i = 0; i < rowData.length; i++) {
                    row.createCell(i).setCellValue(rowData[i]);
                }
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return out.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Błąd podczas generowania XLSX", e);
        }
    }
}
//###   end L12, 2. (part 3)