package com.inventory.backend.inventory.domain.ports.out;

import com.inventory.backend.inventory.domain.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {
    Product save(Product product);
    List<Product> findAll();
    Optional<Product> findById(Long id);
}
