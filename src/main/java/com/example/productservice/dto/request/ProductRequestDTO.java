package com.example.productservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ProductRequestDTO {
    @NotBlank
    private String name;

    private String description;

    @NotNull
    private UUID categoryId;

    private List<ProductVariantRequestDTO> variants;
}