package com.banking.service.report;

import com.banking.entity.Customer;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PDFService {


    public byte[] generatePdf(List<Customer> customerList) throws DocumentException {
        Document document = new Document(PageSize.A4);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, byteArrayOutputStream);

        document.open();

        PdfPTable table = new PdfPTable(9); // 9 columns for the customer data fields
        table.setWidthPercentage(100); // Table width to fill page
        table.setWidths(new float[] { 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f }); // Adjust column widths as needed

        addTableHeader(table);
        addUserRows(table, customerList);

        document.add(table);
        document.close();

        return byteArrayOutputStream.toByteArray();
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Customer_id", "address", "age", "created_at", "date_of_birth", "email", "first_name", "last_name", "phone")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setPhrase(new Phrase(columnTitle));
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    header.setPadding(5);
                    table.addCell(header);
                });
    }

    private void addUserRows(PdfPTable table, List<Customer> customerList) {
        for (Customer customer : customerList) {
            table.addCell(createCell(String.valueOf(customer.getCustomerId())));
            table.addCell(createCell(customer.getAddress()));
            table.addCell(createCell(String.valueOf(customer.getAge())));
            table.addCell(createCell(String.valueOf(customer.getCreatedAt())));
            table.addCell(createCell(String.valueOf(customer.getDateOfBirth())));
            table.addCell(createCell(customer.getEmail()));
            table.addCell(createCell(customer.getFirstName()));
            table.addCell(createCell(customer.getLastName()));
            table.addCell(createCell(customer.getPhone()));
        }
    }

    private PdfPCell createCell(String content) {
        PdfPCell cell = new PdfPCell(new Phrase(content));
       // cell.setNoWrap(true); // Prevent wrapping
        cell.setPadding(5);
        return cell;
    }

//    public byte[] generatePdf(List<Customer> customerList ) throws DocumentException {
//        Document document = new Document(PageSize.A4);
//
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        PdfWriter.getInstance(document, byteArrayOutputStream);
//
//        document.open();
//
//        PdfPTable table = new PdfPTable(9); // Adjust the number of columns based on your User entity
//        addTableHeader(table);
//        addUserRows(table, customerList);
//
//        document.add(table);
//        document.close();
//
//        return byteArrayOutputStream.toByteArray();
//    }
//
//    private void addTableHeader(PdfPTable table) {
//        table.addCell("Customer_id");
//        table.addCell("address");
//        table.addCell("age");
//        table.addCell("created_at");
//        table.addCell("date_of_birth");
//        table.addCell("email");
//        table.addCell("first_name");
//        table.addCell("last_name");
//        table.addCell("phone");
//    }
//
//    private void addUserRows(PdfPTable table, List<Customer> customerList) {
//        for (Customer customer : customerList) {
//            table.addCell(String.valueOf(customer.getCustomerId()));
//            table.addCell(customer.getAddress());
//            table.addCell(String.valueOf(customer.getAge()));
//            table.addCell(String.valueOf(customer.getCreatedAt()));
//            table.addCell(String.valueOf(customer.getDateOfBirth()));
//            table.addCell(customer.getEmail());
//            table.addCell(customer.getFirstName());
//            table.addCell(customer.getLastName());
//            table.addCell(customer.getPhone());
//        }
//    }
}