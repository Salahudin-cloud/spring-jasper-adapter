package com.jasper.learn.adapter.controller;

import com.jasper.learn.common.api.ApiResponse;
import com.jasper.learn.common.api.ApiResponseFactory;
import com.jasper.learn.common.api.PaginationFactory;
import com.jasper.learn.common.api.PaginationMeta;
import com.jasper.learn.domain.entity.Product;
import com.jasper.learn.domain.service.ProductService;
import com.jasper.learn.dto.request.product.ProductRequestDto;
import com.jasper.learn.dto.request.product.ProductUpdateRequestDto;
import com.jasper.learn.dto.response.ProductResponseDto;
import com.jasper.learn.mapper.ProductMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(@RequestBody @Valid ProductRequestDto requestDto) {
        productService.create(productMapper.toDomain(requestDto));
        return ApiResponseFactory.created("Product created successfully");
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDto>>getbyId(@PathVariable Long id) {
        var product = productService.getById(id);
        var response = productMapper.toResponse(product);
        return ApiResponseFactory.success( "Successfully get customer",response);
    }

    @PatchMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<Void>>update(@RequestBody ProductUpdateRequestDto requestDto, @PathVariable Long id){
        Product existing = productService.getById(id);
        productMapper.updateProductFromDto(requestDto, existing);
        productService.update(id, existing);
        return ApiResponseFactory.success("Product updated successfully");
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponseDto>>> all(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Product> products = productService.findAll(page, size);
        List<ProductResponseDto> response = products.getContent()
                .stream()
                .map(productMapper::toResponse)
                .toList();

        PaginationMeta paginationMeta = PaginationFactory.from(products);

        return ApiResponseFactory.paginated("Product fetched successfully", response, paginationMeta);
    }

    @DeleteMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<Void>>delete(
            @PathVariable Long id
    ){
        Product product = productService.getById(id);
        productService.delete(id, product);
        return ApiResponseFactory.success("Product deleted successfully");
    }
}
