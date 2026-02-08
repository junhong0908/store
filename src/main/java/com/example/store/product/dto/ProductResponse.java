package com.example.store.product.dto;

import com.example.store.product.domain.Product;
import com.example.store.product.domain.ProductStatus;

public record ProductResponse(
        Long id,
        Long sellerId,
        String name,
        String description,
        long price,
        int stock,
        ProductStatus status
) {
    public static ProductResponse from(Product p) {
        return new ProductResponse(
                p.getId(),
                p.getSellerId(),
                p.getName(),
                p.getDescription(),
                p.getPrice(),
                p.getStock(),
                p.getStatus()
        );
    }
}
