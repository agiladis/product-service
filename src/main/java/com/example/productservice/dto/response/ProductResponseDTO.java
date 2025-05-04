package com.example.productservice.dto.response;

import com.example.productservice.constant.ProductStatus;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
public class ProductResponseDTO {
    private UUID id;
    private String name;
    private String description;
    private UUID categoryId;
    private ProductStatus status;
    private List<ProductVariantResponseDTO> variants;
    private Instant createdAt;
}
