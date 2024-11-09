package com.banking.controller;


import com.banking.PI.CustomerAccountProjection;
import com.banking.dto.CustomerDTO;
import com.banking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {


    @Autowired
    private CustomerService customerService;


    //http://localhost:8080/api/customer/addCustomer
    @PostMapping("/addCustomer")
    public ResponseEntity <String> addCustomer(@RequestBody CustomerDTO customerDTO){


        customerService.addCustomer(customerDTO);

        return  new ResponseEntity<>("customer created " , HttpStatus.CREATED);
    }


    //http://localhost:8080/api/customer/?minAge=30&maxAge=35
    @GetMapping("/age-range")
    public ResponseEntity<List<CustomerAccountProjection>> getCustomersByAgeRange(
            @RequestParam("minAge") int minAge,
            @RequestParam("maxAge") int maxAge) {

        List<CustomerAccountProjection> customers = customerService.findCustomersWithinAgeRange(minAge, maxAge);

        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customers);
    }

}
