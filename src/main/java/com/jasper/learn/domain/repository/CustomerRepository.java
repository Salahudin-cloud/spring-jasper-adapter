package com.jasper.learn.domain.repository;

import com.jasper.learn.domain.entity.Customer;

import java.util.Optional;


public interface CustomerRepository{

    Optional<Customer> findById(Long id);

    Customer save(Customer customer);

    void deleteById(Long id);



}
