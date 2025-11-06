package com.jasper.learn.dto.request.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDto {

    @NotBlank(message = "Kode produk jangan sampai dilupakan")
    private String code;

    @NotBlank(message = "Nama produk jangan sampai dilupakan")
    private String name;

    @NotBlank(message = "Kategori produk jangan sampai dilupakan")
    private String category;

    @NotBlank(message = "Unit produk jangan sampai dilupakan")
    private String unit;

    @NotNull(message = "Harga produk jangan sampai dilupakan")
    private BigDecimal price;

}
