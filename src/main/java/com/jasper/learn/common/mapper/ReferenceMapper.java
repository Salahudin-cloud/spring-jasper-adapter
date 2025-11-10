package com.jasper.learn.common.mapper;

import com.jasper.learn.domain.entity.Customer;
import com.jasper.learn.domain.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReferenceMapper {
    default Customer mapCustomer(Long id) {
        if (id == null) return null;
        Customer c = new Customer();
        c.setId(id);
        return c;
    }

    default Product mapProduct(Long id) {
        if (id == null) return null;
        Product p = new Product();
        p.setId(id);
        return p;
    }
}
