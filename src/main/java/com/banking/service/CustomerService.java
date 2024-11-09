package com.banking.service;


import com.banking.PI.CustomerAccountProjection;
import com.banking.bo.CustomerBO;
import com.banking.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {


    @Autowired
    private CustomerBO customerBO;
    public void addCustomer(CustomerDTO customerDTO) {

        customerBO.addCustomer(customerDTO);
    }

    public List<CustomerAccountProjection> findCustomersWithinAgeRange(int minAge, int maxAge) {
        List<CustomerAccountProjection> customersWithinAgeRange = customerBO.findCustomersWithinAgeRange(minAge, maxAge);
        return customersWithinAgeRange;
    }
}
