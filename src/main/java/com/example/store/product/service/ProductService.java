package com.example.store.product.service;

import com.example.store.product.domain.Product;
import com.example.store.product.dto.ProductCreateRequest;
import com.example.store.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Product create(ProductCreateRequest req) {
        Product product = new Product(
                req.sellerId(),
                req.name(),
                req.description(),
                req.price(),
                req.stock()
        );
        return productRepository.save(product);
    }
}
