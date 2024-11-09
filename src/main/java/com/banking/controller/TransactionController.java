package com.banking.controller;


import com.banking.dto.CustomerDTO;
import com.banking.dto.TransactionRequestDTO;
import com.banking.dto.TransferDTO;
import com.banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/transaction")

public class TransactionController {


    @Autowired

    private TransactionService transactionService;

    //http://localhost:8080/api/transaction/doTransaction
    @PostMapping("/doTransaction")
    public ResponseEntity<String> doTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO){

            transactionService.doTransaction(transactionRequestDTO);

        return  new ResponseEntity<>("sucess " , HttpStatus.CREATED);
    }




    //http://localhost:8080/api/transaction/amountTransfer
    @PostMapping("/amountTransfer")
    public ResponseEntity<String> amountTransfer(@RequestBody TransferDTO transferDTO){

        transactionService.amountTransfer(transferDTO);

        return  new ResponseEntity<>("sucess " , HttpStatus.CREATED);
    }
}
