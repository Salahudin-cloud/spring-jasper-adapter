package com.jasper.learn.adapter.controller;


import com.jasper.learn.common.api.ApiResponse;
import com.jasper.learn.common.api.ApiResponseFactory;
import com.jasper.learn.domain.service.InvoiceService;
import com.jasper.learn.dto.request.invoice.InvoiceRequestDto;
import com.jasper.learn.adapter.mapper.InvoiceMapper;
import com.jasper.learn.dto.request.invoice.InvoiceUpdateRequestDto;
import com.jasper.learn.dto.response.InvoiceResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final InvoiceMapper invoiceMapper;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(@RequestBody @Valid InvoiceRequestDto requestDto) {
        invoiceService.create(invoiceMapper.toEntity(requestDto));
        return ApiResponseFactory.created("Product created successfully");
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<InvoiceResponseDto>>getById(
            @PathVariable Long id
    ){
        var invoiceItem = invoiceService.getById(id);
        var response = invoiceMapper.toResponse(invoiceItem);
        return ApiResponseFactory.success("Successfully get response", response);
    }

}
