package com.inventory.backend.inventory.application.usecase;

import com.inventory.backend.inventory.domain.model.Product;
import com.inventory.backend.inventory.domain.ports.out.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepositoryPort productRepositoryPort;

    public List<Product> getAll() {
        return productRepositoryPort.findAll();
    }

    public Product save(Product product) {
        return productRepositoryPort.save(product);
    }
}
