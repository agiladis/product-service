package com.example.productservice.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProductVariantResponseDTO {
    private UUID id;
    private String sku;
    private String size;
    private String color;
    private BigDecimal price;
}
