package com.jasper.learn.adapter.mapper;

import com.jasper.learn.common.mapper.ReferenceMapper;
import com.jasper.learn.domain.entity.Invoice;
import com.jasper.learn.dto.request.invoice.InvoiceRequestDto;
import com.jasper.learn.dto.response.InvoiceResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {InvoiceItemMapper.class, ReferenceMapper.class})
public interface InvoiceMapper {

    // Untuk Response (entity -> dto)
    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "customerName", source = "customer.name")
    @Mapping(target = "phoneNumber", source = "customer.phone")
    InvoiceResponseDto toResponse(Invoice invoice);

    @Mapping(target = "customer", source = "customerId")
    @Mapping(target = "items", source = "items")
    Invoice toEntity(InvoiceRequestDto requestDto);
}