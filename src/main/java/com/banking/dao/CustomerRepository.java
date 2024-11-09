package com.banking.dao;

import com.banking.PI.CustomerAccountProjection;
import com.banking.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository <Customer,Long> {



    Optional<Customer> findByPhone(String phone);
    @Query("SELECT c.firstName AS firstName, c.age AS age, a.accountNumber AS accountNumber " +
            "FROM Customer c " +
            "JOIN c.account a " +
            "WHERE c.age BETWEEN :minAge AND :maxAge")
    List<CustomerAccountProjection> findCustomersWithinAgeRange(@Param("minAge") int minAge,
                                                                @Param("maxAge") int maxAge);




}
