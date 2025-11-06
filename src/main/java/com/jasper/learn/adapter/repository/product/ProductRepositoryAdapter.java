package com.jasper.learn.adapter.repository.product;

import com.jasper.learn.domain.entity.Product;
import com.jasper.learn.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {

    private final JpaProductRepository jpaRepo;

    @Override
    public Optional<Product> findById(Long id) {
        return jpaRepo.findById(id);
    }

    @Override
    public Product save(Product product) {
        return jpaRepo.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        jpaRepo.delete(product);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return jpaRepo.findAll(pageable);
    }
}
