package com.jasper.learn.dto.request.product;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductUpdateRequestDto {
    private String code;
    private String name;
    private String category;
    private String unit;
    private BigDecimal price;
}
