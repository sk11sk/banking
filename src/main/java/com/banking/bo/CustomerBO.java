package com.banking.bo;


import com.banking.PI.CustomerAccountProjection;
import com.banking.dao.AccountRepository;
import com.banking.dao.CustomerRepository;
import com.banking.dto.CustomerDTO;
import com.banking.entity.Account;
import com.banking.entity.Customer;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Component
public class CustomerBO {

    @Autowired
    private CustomerRepository customerRepository;


    @Autowired
    private AccountRepository accountRepository;
    public void addCustomer(CustomerDTO customerDTO) {


        Customer customer  = new Customer();

        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setAddress(customerDTO.getAddress());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());
        customer.setDateOfBirth(customerDTO.getDateOfBirth());

        LocalDate dateOfBirth = customerDTO.getDateOfBirth();


        LocalDate currentDate = LocalDate.now();  // Get current date


        int  age = currentDate.getYear() - dateOfBirth.getYear();

        customer.setAge(age);



        String accountNumber = RandomStringUtils.randomNumeric(10);

        Account account = new Account();
        
        account.setAccountNumber(accountNumber);
        account.setCustomer(customer);
        account.setStatus(Account.AccountStatus.ACTIVE);



        customer.setAccount(account);

        customerRepository.save(customer);


        accountRepository.save(account);






    }


    @Transactional(readOnly = true)
    public List<CustomerAccountProjection> findCustomersWithinAgeRange(int minAge, int maxAge) {

       List<CustomerAccountProjection> accountsInAgeRange = customerRepository.findCustomersWithinAgeRange(minAge,maxAge);

       return accountsInAgeRange;

    }
}


