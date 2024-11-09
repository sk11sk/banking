package com.banking.bo;


import com.banking.PI.CustomerAccountProjection;
import com.banking.dao.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;

@Configuration
public class AccountBO {

    @Autowired
    AccountRepository accountRepository;



    @Transactional(readOnly = true)
    public List<CustomerAccountProjection> findTransactionsForAccountWithinDateRange(String accountNumber, Date startDate, Date endDate) {

       return accountRepository.findTransactionsForAccountWithinDateRange(accountNumber ,startDate,endDate);

    }



    @Transactional(readOnly = true)
    public List<CustomerAccountProjection> findCustomersWithZeroBalanceAndNoTransactions() {

        return  accountRepository.findCustomersWithZeroBalanceAndNoTransactions();
    }
}
