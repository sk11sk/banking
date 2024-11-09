package com.banking.controller.report;

import com.banking.dao.CustomerRepository;
import com.banking.entity.Customer;

import com.banking.service.report.ExcelService;
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
@RequestMapping("/api/excel")
public class ExcelController {

    @Autowired
    ExcelService excelService;

    @Autowired
    CustomerRepository customerRepository;

    //http://localhost:8080/api/excel/download
    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadExcelReport() throws IOException {
        List<Customer> customerList = customerRepository.findAll();
        byte[] excelData = excelService.generateExcel(customerList);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "Customer_Report.xlsx");

        return new ResponseEntity<>(excelData, headers, HttpStatus.OK);
    }

}
