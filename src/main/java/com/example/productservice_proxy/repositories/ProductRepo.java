package com.example.productservice_proxy.repositories;

import com.example.productservice_proxy.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    Product save(Product product);
    Optional<Product> findById(Long productId);
    Product getProductById(Long id);

    @Override
    void deleteById(Long productId);
}
