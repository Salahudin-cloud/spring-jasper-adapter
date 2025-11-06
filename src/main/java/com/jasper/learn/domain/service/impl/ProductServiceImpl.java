package com.jasper.learn.domain.service.impl;

import com.jasper.learn.domain.entity.Product;
import com.jasper.learn.domain.repository.ProductRepository;
import com.jasper.learn.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Transactional
    @Override
    public void create(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data produk tidak ditemukan dengan id " + id ));
    }

    @Override
    public void update(Long id, Product product) {
        Product existing = getById(id);

        Optional.ofNullable(product.getCode())
                .ifPresent(existing::setCode);

        Optional.ofNullable(product.getName())
                .ifPresent(existing::setName);

        Optional.ofNullable(product.getCategory())
                .ifPresent(existing::setCategory);

        Optional.ofNullable(product.getUnit())
                .ifPresent(existing::setUnit);

        Optional.ofNullable(product.getPrice())
                .ifPresent(existing::setPrice);

        productRepository.save(existing);
    }

    @Override
    public Page<Product> findAll(int page, int size) {
        return productRepository.findAll(PageRequest.of(page,size));
    }

    @Override
    public void delete(Long id, Product product) {
        Product existing = getById(id);
        productRepository.deleteProduct(existing);
    }
}
