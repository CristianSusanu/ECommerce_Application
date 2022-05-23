package com.vodafone.ecommerce.services;

import com.vodafone.ecommerce.api.repositories.ProductRepository;
import com.vodafone.ecommerce.models.dto.ProductDTO;
import com.vodafone.ecommerce.models.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

//    @Transactional
    public void save(Product product){
//        Objects.requireNonNull(product);
        productRepository.save(product);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
