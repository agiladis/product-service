package com.example.productservice.controller.v1.client;

import com.example.productservice.dto.response.ApiResponse;
import com.example.productservice.dto.response.ProductResponseDTO;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/public/products")
@RequiredArgsConstructor
public class ProductClientController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponseDTO>>> getAll() {
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
