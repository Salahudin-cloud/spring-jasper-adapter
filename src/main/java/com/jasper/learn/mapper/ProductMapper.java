package com.jasper.learn.mapper;

import com.jasper.learn.domain.entity.Product;
import com.jasper.learn.dto.request.product.ProductRequestDto;
import com.jasper.learn.dto.request.product.ProductUpdateRequestDto;
import com.jasper.learn.dto.response.ProductResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    // --- Mapping for Response ---
    ProductResponseDto toResponse(Product product); // Domain → Response DTO

    // --- Mapping for Request ---
    Product toDomain(ProductRequestDto request); // Request DTO → Domain

    void updateProductFromDto(ProductUpdateRequestDto dto, @MappingTarget Product product);

}
