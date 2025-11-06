package com.jasper.learn.domain.service;

import com.jasper.learn.domain.entity.Customer;
import com.jasper.learn.domain.entity.Product;
import org.springframework.data.domain.Page;

public interface ProductService {

    void create(Product product);
    Product getById(Long id);
    void update(Long id , Product product);
    Page<Product> findAll(int page, int size);
    void delete(Long id, Product product);
}
