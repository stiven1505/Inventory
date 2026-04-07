package com.inventory.backend.inventory.infrastructure.adapters.out;

import com.inventory.backend.inventory.domain.model.Product;
import com.inventory.backend.inventory.domain.ports.out.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductRepositoryPort {

    private final ProductJpaRepository jpaRepository;

    @Override
    public Product save(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
        entity.setDescription(product.getDescription());
        entity.setStock(product.getStock());
        
        ProductEntity saved = jpaRepository.save(entity);
        product.setId(saved.getId());
        return product;
    }

    @Override
    public List<Product> findAll() {
        return jpaRepository.findAll().stream()
            .map(ent -> new Product(ent.getId(), ent.getName(), ent.getDescription(), ent.getPrice(), ent.getStock()))
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jpaRepository.findById(id)
            .map(ent -> new Product(ent.getId(), ent.getName(), ent.getDescription(), ent.getPrice(), ent.getStock()));
    }


}
