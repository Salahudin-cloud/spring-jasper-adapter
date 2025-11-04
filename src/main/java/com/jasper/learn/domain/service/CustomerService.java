package com.jasper.learn.domain.service;

import com.jasper.learn.domain.entity.Customer;

public interface CustomerService {

    Customer create(Customer customer);
    Customer getById(Long id);
    Customer update(Long id , Customer customer);
}
