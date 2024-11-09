package com.banking.controller.report;


import com.banking.dao.CustomerRepository;
import com.banking.entity.Customer;
import com.banking.service.report.PDFService;
import com.itextpdf.text.DocumentException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/pdf")
public class PDFcontroller {// this is the get the pdf in table form

    @Autowired
    private PDFService pdfService;

    @Autowired
    private CustomerRepository customerRepository;

    //http://localhost:8080/api/pdf/download
    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadPdf() throws IOException, DocumentException {
        List<Customer> customersList = customerRepository.findAll();

        byte[] pdfBytes = pdfService.generatePdf(customersList);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "customer_list.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}