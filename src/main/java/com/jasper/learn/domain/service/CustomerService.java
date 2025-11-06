package com.jasper.learn.domain.service;

import com.jasper.learn.domain.entity.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {

    void create(Customer customer);
    Customer getById(Long id);
    void update(Long id , Customer customer);
    Page<Customer> findAll(int page, int size);
    void delete(Long id, Customer customer);
}
