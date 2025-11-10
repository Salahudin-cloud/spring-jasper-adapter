package com.jasper.learn.adapter.mapper;

import com.jasper.learn.common.mapper.ReferenceMapper;
import com.jasper.learn.domain.entity.InvoiceItem;
import com.jasper.learn.dto.request.invoice.InvoiceItemRequestDto;
import com.jasper.learn.dto.response.InvoiceItemResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ReferenceMapper.class})
public interface InvoiceItemMapper {

    @Mapping(target = "invoiceId", source = "invoice.id")
    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "code", source = "product.code")
    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "category", source = "product.category")
    @Mapping(target = "unit", source = "product.unit")
    @Mapping(target = "price", source = "product.price")
    InvoiceItemResponseDto toResponse(InvoiceItem entity);

    // Mapping dari DTO request ke entity dasar (tanpa relasi)
    @Mapping(target = "invoice", ignore = true)
    @Mapping(target = "product", source = "productId")
    InvoiceItem toEntity(InvoiceItemRequestDto dto);

}
