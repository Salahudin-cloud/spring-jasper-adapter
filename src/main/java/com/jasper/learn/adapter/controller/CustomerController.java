package com.jasper.learn.adapter.controller;

import com.jasper.learn.common.api.*;
import com.jasper.learn.domain.entity.Customer;
import com.jasper.learn.domain.service.CustomerService;
import com.jasper.learn.dto.request.customer.CustomerRequestDto;
import com.jasper.learn.dto.request.customer.CustomerUpdateRequestDto;
import com.jasper.learn.dto.response.CustomerResponseDto;
import com.jasper.learn.mapper.CustomerMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(@RequestBody @Valid CustomerRequestDto dto) {
        customerService.create(customerMapper.toDomain(dto));
        return ApiResponseFactory.created("Customer created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponseDto>> findById(@PathVariable Long id) {
        var customer = customerService.getById(id);
        var response = customerMapper.toResponse(customer);
        return ApiResponseFactory.success("Customer retrieved successfully", response);
    }

    @PatchMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<Void>> update(
            @RequestBody @Valid CustomerUpdateRequestDto requestDto,
            @PathVariable Long id
    ) {
        Customer existing = customerService.getById(id);
        customerMapper.updateCustomerFromDto(requestDto, existing);
        customerService.update(id, existing);
        return ApiResponseFactory.success("Customer updated successfully");
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerResponseDto>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Customer> customers = customerService.findAll(page, size);
        List<CustomerResponseDto> responses = customers.getContent()
                .stream()
                .map(customerMapper::toResponse)
                .toList();

        PaginationMeta paginationMeta = PaginationFactory.from(customers);
        return ApiResponseFactory.paginated("Customers fetched successfully", responses, paginationMeta);
    }

    @DeleteMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        Customer customer = customerService.getById(id);
        customerService.delete(id, customer);
        return ApiResponseFactory.success("Customer deleted successfully");
    }

}
