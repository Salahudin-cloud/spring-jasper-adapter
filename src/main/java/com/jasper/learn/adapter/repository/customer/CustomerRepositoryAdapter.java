package com.jasper.learn.adapter.repository.customer;

import com.jasper.learn.domain.entity.Customer;
import com.jasper.learn.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepository {

    private final JpaCustomerRepository jpaRepo;

    @Override
    public Optional<Customer> findById(Long id) {
        return jpaRepo.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
        return jpaRepo.save(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        jpaRepo.delete(customer);
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return jpaRepo.findAll(pageable);
    }
}
