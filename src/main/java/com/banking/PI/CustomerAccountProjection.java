package com.banking.PI;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@JsonInclude(JsonInclude.Include.NON_NULL)
public interface CustomerAccountProjection {
    String getFirstName();
    Integer getAge();
    String getAccountNumber();


    LocalDateTime getCreatedAt();
    BigDecimal getAmount();
    LocalDateTime getTransactionDate();

    BigDecimal getAccountBalance();

    Long getCustomerId();
}
