package com.banking.service.report;

import com.banking.entity.Customer;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {



    public byte[] generateExcel(List<Customer> customerList) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Customers");

        // Add header row
        Row headerRow = sheet.createRow(0);
        String[] columns = {"Customer_id", "address", "age", "created_at", "date_of_birth", "email", "first_name", "last_name", "phone"};
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        // Add data rows
        int rowNum = 1;
        for (Customer customer : customerList) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(customer.getCustomerId());
            row.createCell(1).setCellValue(customer.getAddress());
            row.createCell(2).setCellValue(customer.getAge());
            row.createCell(3).setCellValue(customer.getCreatedAt().toString());
            row.createCell(4).setCellValue(customer.getDateOfBirth().toString());
            row.createCell(5).setCellValue(customer.getEmail());
            row.createCell(6).setCellValue(customer.getFirstName());
            row.createCell(7).setCellValue(customer.getLastName());
            row.createCell(8).setCellValue(customer.getPhone());
        }

        // Auto-size columns
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write to ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);
        workbook.close();

        return byteArrayOutputStream.toByteArray();
    }

}
