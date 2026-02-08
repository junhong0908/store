package com.example.store.product.controller;

import com.example.store.product.dto.ProductCreateRequest;
import com.example.store.product.dto.ProductResponse;
import com.example.store.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller/products")
public class SellerProductController {

    private final ProductService productService;

    public SellerProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@RequestBody @Valid ProductCreateRequest req) {
        return ProductResponse.from(productService.create(req));
    }
}
