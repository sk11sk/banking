package com.banking.dto;

import com.banking.entity.Transaction;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class TransactionDTO {

    private Transaction.TransactionType transactionType;


    private BigDecimal amount;
}
