package com.jasper.learn.adapter.mapper;

import com.jasper.learn.domain.entity.Customer;
import com.jasper.learn.dto.request.customer.CustomerRequestDto;
import com.jasper.learn.dto.request.customer.CustomerUpdateRequestDto;
import com.jasper.learn.dto.response.CustomerResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    // --- Mapping for Response ---
    CustomerResponseDto toResponse(Customer customer); // Domain → Response DTO

    // --- Mapping for Request ---
    Customer toDomain(CustomerRequestDto request); // Request DTO → Domain

    void updateCustomerFromDto(CustomerUpdateRequestDto dto, @MappingTarget Customer customer);
}
