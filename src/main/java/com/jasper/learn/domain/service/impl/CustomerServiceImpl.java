package com.jasper.learn.domain.service.impl;

import com.jasper.learn.domain.entity.Customer;
import com.jasper.learn.domain.repository.CustomerRepository;
import com.jasper.learn.domain.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    @Override
    public void create(Customer customer) {
         customerRepository.save(customer);
    }

    @Override
    public Customer getById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data customer tidak di temukan dengan id" + id ));
    }

    @Transactional
    @Override
    public void update(Long id, Customer customer) {

        Customer existing = getById(id);

        Optional.ofNullable(customer.getName())
                .ifPresent(existing::setName);

        Optional.ofNullable(customer.getAddress())
                .ifPresent(existing::setAddress);

        Optional.ofNullable(customer.getPhone())
                .ifPresent(existing::setPhone);

        customerRepository.save(existing);
    }

    @Override
    public Page<Customer> findAll(int page, int size) {
        return customerRepository.findAll(PageRequest.of(page,size));
    }

    @Transactional
    @Override
    public void delete(Long id, Customer customer) {
        Customer existing = getById(id);
        customerRepository.deleteCustomer(existing);
    }
}
