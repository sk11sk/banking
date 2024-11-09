package com.banking.controller.report;
import com.banking.dao.CustomerRepository;
import com.banking.entity.Customer;
import com.banking.service.report.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

@RestController
@RequestMapping("/api/csv")
public class CSVController {

    @Autowired
    CSVService csvService;

    @Autowired
    CustomerRepository customerRepository;


    //http://localhost:8080/api/csv/download
    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadCsvReport() throws IOException {
        List<Customer> customerList = customerRepository.findAll();
        byte[] csvData = csvService.generateCsv(customerList);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setContentDispositionFormData("attachment", "Customer_Report.csv");

        return new ResponseEntity<>(csvData, headers, HttpStatus.OK);
    }

}
