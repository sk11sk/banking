package com.banking.dto;

import lombok.Data;

import java.time.LocalDate;



@Data
public class CustomerDTO {


    private Long customerId;

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    private int  age;
    private String address;
    private String email;
    private String phone;
}
