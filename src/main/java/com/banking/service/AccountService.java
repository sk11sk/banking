package com.banking.service;


import com.banking.PI.CustomerAccountProjection;
import com.banking.bo.AccountBO;
import com.banking.dao.AccountRepository;
import com.banking.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class AccountService {


    @Autowired
    private AccountBO accountBO;
    public List<CustomerAccountProjection> getTransactionHistory(String accountNumber, Date startDate, Date endDate) {

        return  accountBO.findTransactionsForAccountWithinDateRange(accountNumber,startDate,endDate);


    }

    public List<CustomerAccountProjection> getlistOfInactiveAccounts() {

        return  accountBO.findCustomersWithZeroBalanceAndNoTransactions();
    }
}
