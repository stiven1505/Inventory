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

    public void deleteById(Long id) {
        productRepositoryPort.deleteById(id);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
    return productRepositoryPort.findById(id)
        .map(existingProduct -> {
            Product productToSave = new Product(
                id, 
                updatedProduct.getName(), 
                updatedProduct.getDescription(), 
                updatedProduct.getPrice(), 
                updatedProduct.getStock()
            );
            return productRepositoryPort.save(productToSave);
        })
        .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
}
}
