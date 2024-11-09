package com.banking.controller;

import com.banking.PI.CustomerAccountProjection;
import com.banking.dto.CustomerDTO;
import com.banking.service.AccountService;
import com.banking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    private Date convertToDateViaInstant(LocalDate dateToConvert) {
        return Date.from(dateToConvert.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    //http://localhost:8080/api/accounts/getTransactionHistory
    @GetMapping("/getTransactionHistory")
    public ResponseEntity<List<CustomerAccountProjection>> getTransactionHistory(
            @RequestParam("accountNumber") String accountNumber,
            @RequestParam("startDate")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate  start,
            @RequestParam("endDate")   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end){


        Date startDate = convertToDateViaInstant(start);
        Date endDate = convertToDateViaInstant(end);

        List<CustomerAccountProjection>  transactionHistory = accountService.getTransactionHistory(accountNumber,startDate,endDate);

        return  new ResponseEntity<>(transactionHistory , HttpStatus.CREATED);
    }


    //http://localhost:8080/api/accounts/getlistOfInactiveAccounts
    @GetMapping("/getlistOfInactiveAccounts")
    public ResponseEntity<List<CustomerAccountProjection>> getlistOfInactiveAccounts() {
        List<CustomerAccountProjection>  transactionHistory = accountService.getlistOfInactiveAccounts();

        return  new ResponseEntity<>(transactionHistory , HttpStatus.CREATED);
    }


}



