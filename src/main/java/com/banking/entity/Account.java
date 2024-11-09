package com.banking.entity;

import javax.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;



    @Column(unique = true, nullable = false)
    private String accountNumber;



    private BigDecimal balance = BigDecimal.ZERO;


    private BigDecimal minBalance = BigDecimal.valueOf(2000);


    private BigDecimal maxBalance = BigDecimal.valueOf(1000000);  // Example max balance



    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customerId")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt ;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions  = new ArrayList<>();




    public enum AccountStatus {
        ACTIVE, INACTIVE, CLOSED
    }



}
