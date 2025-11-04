package com.jasper.learn.adapter.controller;

import com.jasper.learn.domain.entity.Customer;
import com.jasper.learn.domain.service.CustomerService;
import com.jasper.learn.dto.request.CustomerRequestDto;
import com.jasper.learn.dto.request.CustomerUpdateRequestDto;
import com.jasper.learn.dto.response.CustomerResponseDto;
import com.jasper.learn.mapper.CustomerMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CustomerResponseDto>create(@RequestBody @Valid CustomerRequestDto requestDto) {
        Customer customer = customerMapper.toDomain(requestDto);
        Customer saved = customerService.create(customer);
        return ResponseEntity.ok(customerMapper.toResponse(saved));
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CustomerResponseDto>getbyId(@PathVariable Long id) {
        Customer customer = customerService.getById(id);
        return ResponseEntity.ok(customerMapper.toResponse(customer));
    }

    @PatchMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CustomerResponseDto>update(@RequestBody CustomerUpdateRequestDto requestDto, @PathVariable Long id){
        Customer existing = customerService.getById(id);
        customerMapper.updateCustomerFromDto(requestDto, existing);

        Customer saved = customerService.update(id, existing);
        return ResponseEntity.ok(customerMapper.toResponse(saved));
    }

}
