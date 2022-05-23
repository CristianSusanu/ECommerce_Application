package com.vodafone.ecommerce.api.repositories;

import com.vodafone.ecommerce.models.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);

//    Object save(Product product);

    Page<Product> findByName(String name, Pageable pageable);

    Page<Product> findByCategory(String category, Pageable pageable);

    Page<Product> findByDescription(String description, Pageable pageable);

    Page<Product> findByPrice(Long price, Pageable pageable);
}
