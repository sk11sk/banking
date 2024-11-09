package com.banking.config;




import com.banking.entity.Account;
import com.banking.entity.Customer;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;

public class CustomerProcessor implements ItemProcessor<Customer,Customer> {

    @Override
    public Customer process(Customer customer) throws Exception {


        LocalDate dateOfBirth = customer.getDateOfBirth();


        LocalDate currentDate = LocalDate.now();  // Get current date


        int  age = currentDate.getYear() - dateOfBirth.getYear();

        customer.setAge(age);

        String accountNumber = RandomStringUtils.randomNumeric(10);

        Account account = new Account();

        account.setAccountNumber(accountNumber);
        account.setCustomer(customer);
        account.setStatus(Account.AccountStatus.ACTIVE);
        customer.setAccount(account);
        return customer;

    }
}



//public class CustomerProcessor implements ItemProcessor<Customer, Customer> {
//    @Override
//    public Customer process(Customer customer) {
//        Account account = new Account();
//        account.setAccountNumber(generateAccountNumber()); // Generates an account number
//        customer.setAccount(account);
//        return customer;
//    }
//
//    private String generateAccountNumber() {
//        // Logic to generate an account number (e.g., using UUID)
//        return UUID.randomUUID().toString();
//    }
//}