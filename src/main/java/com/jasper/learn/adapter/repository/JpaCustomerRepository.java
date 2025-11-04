package com.jasper.learn.adapter.repository;

import com.jasper.learn.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCustomerRepository extends JpaRepository<Customer, Long> {
}
