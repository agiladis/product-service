package com.example.productservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
class ProductVariantRequestDTO {
    @NotBlank
    private String sku;

    @NotBlank
    private String size;

    @NotNull
    @Positive
    private BigDecimal price;
}