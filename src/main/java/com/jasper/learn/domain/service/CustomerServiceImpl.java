package com.jasper.learn.domain.service;

import com.jasper.learn.domain.entity.Customer;
import com.jasper.learn.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data customer tidak di temukan dengan id" + id ));
    }

    @Override
    public Customer update(Long id, Customer customer) {

        Customer existing = getById(id);

        Optional.ofNullable(customer.getName())
                .ifPresent(existing::setName);

        Optional.ofNullable(customer.getAddress())
                .ifPresent(existing::setAddress);

        Optional.ofNullable(customer.getPhone())
                .ifPresent(existing::setPhone);

        return customerRepository.save(existing);
    }
}
