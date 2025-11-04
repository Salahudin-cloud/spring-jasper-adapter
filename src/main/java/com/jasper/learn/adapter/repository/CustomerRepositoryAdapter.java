package com.jasper.learn.adapter.repository;

import com.jasper.learn.domain.entity.Customer;
import com.jasper.learn.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
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
    public void deleteById(Long id) {
        jpaRepo.deleteById(id);
    }
}
