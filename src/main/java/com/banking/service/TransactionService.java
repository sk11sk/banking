package com.banking.service;


import com.banking.bo.TransactionBO;
import com.banking.dto.TransactionRequestDTO;
import com.banking.dto.TransferDTO;
import com.banking.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {


    @Autowired
    TransactionBO transactionBO;

    public void doTransaction(TransactionRequestDTO transactionRequestDTO) {


        transactionBO.doTransaction(transactionRequestDTO);
    }

    public void amountTransfer(TransferDTO transferDTO) {
        transactionBO.amountTransfer(transferDTO);

    }
}
