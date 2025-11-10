package com.jasper.learn.dto.request.invoice;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceRequestDto {

    @NotNull(message = "Customer id cannot be empty")
    private Long customerId;

    @NotBlank(message = "Invoice number cannot be empty")
    private String invoiceNumber;

    @NotNull(message = "Invoice date cannot be empty")
    private LocalDate invoiceDate;

    private LocalDate dueDate;

    @NotNull(message = "Invoice item cannot be null")
    private List<@Valid InvoiceItemRequestDto> items;

}
