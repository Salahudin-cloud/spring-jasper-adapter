package com.jasper.learn.domain.repository;

import com.jasper.learn.domain.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface CustomerRepository{

    Optional<Customer> findById(Long id);

    Customer save(Customer customer);

    void deleteCustomer(Customer customer);

    Page<Customer> findAll(Pageable pageable);

}
