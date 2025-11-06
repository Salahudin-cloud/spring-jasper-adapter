package com.jasper.learn.domain.repository;

import com.jasper.learn.domain.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findById(Long id);

    Product save(Product product);

    void deleteProduct(Product product);

    Page<Product> findAll(Pageable pageable);
}
