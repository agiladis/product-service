package com.example.productservice.controller.v1.client;

import com.example.productservice.dto.response.ApiResponse;
import com.example.productservice.dto.response.ProductResponseDTO;
import com.example.productservice.service.ProductService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/public/products")
@RequiredArgsConstructor
public class ProductClientController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponseDTO>>> getAll(
            @RequestParam(defaultValue = "1") @Min(0) int page,
            @RequestParam(defaultValue = "25") @Max(100) int size
    ) {
        List<ProductResponseDTO> products = productService.getAllProduct();

        return ResponseEntity.ok(
                ApiResponse.<List<ProductResponseDTO>>builder()
                        .status("success")
                        .message("Products fetched successfully")
                        .data(products)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> getById(@PathVariable UUID id) {
        ProductResponseDTO product = productService.getProductById(id);

        return ResponseEntity.ok(
                ApiResponse.<ProductResponseDTO>builder()
                        .status("success")
                        .message("Product fetched successfully")
                        .data(product)
                        .build()
        );
    }
}
