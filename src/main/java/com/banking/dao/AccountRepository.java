package com.banking.dao;

import com.banking.PI.CustomerAccountProjection;
import com.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AccountRepository   extends JpaRepository<Account ,Long> {

    Optional<Account> findByAccountNumber(String accountNumber);
    @Query("SELECT  t.amount AS amount,t.balance as accountBalance, t.transactionDate AS transactionDate " +
            "FROM Account a " +
            "JOIN a.transactions t " +
            "WHERE a.accountNumber = :accountNumber " +
            "AND t.transactionDate BETWEEN :startDate AND :endDate")
    List<CustomerAccountProjection> findTransactionsForAccountWithinDateRange(
            @Param("accountNumber") String accountNumber,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);




    @Query("SELECT c.customerId AS customerId, c.firstName AS firstName, a.accountNumber AS accountNumber " +
            "FROM Customer c " +
            "JOIN c.account a " +
            "LEFT JOIN a.transactions t " +
            "WHERE a.balance = 0.0 " +
            "AND t.transactionDate IS NULL")
    List<CustomerAccountProjection> findCustomersWithZeroBalanceAndNoTransactions();




}
