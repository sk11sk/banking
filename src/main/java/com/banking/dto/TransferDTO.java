package com.banking.dto;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class TransferDTO {

    private String fromAccount;

    private String toAccount;

    private   TransactionDTO transaction;

}
