package com.vodafone.ecommerce.api.controllers;

import com.vodafone.ecommerce.api.mapper.ProductMapper;
import com.vodafone.ecommerce.api.repositories.ProductRepository;
import com.vodafone.ecommerce.models.dto.ProductCreationDTO;
import com.vodafone.ecommerce.models.dto.ProductDTO;
import com.vodafone.ecommerce.models.entities.Product;
import com.vodafone.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    private static final int NUMBER_OF_ELEMENTS_PER_PAGE = 5;

    @GetMapping
    public ResponseEntity<Page<Product>> getProducts(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy
    ) {

        if (productRepository.findAll().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(productRepository.findAll(
                PageRequest.of(
                        page.orElse(0),NUMBER_OF_ELEMENTS_PER_PAGE,
                        Sort.Direction.ASC, sortBy.orElse("id")
                )
        ), HttpStatus.OK);
    }

    @GetMapping("/filterByName")
    public ResponseEntity<Page<Product>> getAllProductsByName(
            @RequestParam String name,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy) {
        try {
            Pageable pageable = PageRequest.of(
                    page.orElse(0),NUMBER_OF_ELEMENTS_PER_PAGE,
                    Sort.Direction.ASC, sortBy.orElse("id"));

                if (productRepository.findByName(name, pageable).isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }

                return new ResponseEntity<>(productRepository.findByName(name, pageable), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/filterByCategory")
    public ResponseEntity<Page<Product>> getAllProductsByCategory(
            @RequestParam String category,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy) {

        try {
            Pageable pageable = PageRequest.of(
                    page.orElse(0), NUMBER_OF_ELEMENTS_PER_PAGE,
                    Sort.Direction.ASC, sortBy.orElse("id"));

                if (productRepository.findByCategory(category, pageable).isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }

                return new ResponseEntity<>(productRepository.findByCategory(category, pageable), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/filterByDescription")
    public ResponseEntity<Page<Product>> getAllProductsByDescription(
            @RequestParam String description,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy) {

        try {
            Pageable pageable = PageRequest.of(
                    page.orElse(0), NUMBER_OF_ELEMENTS_PER_PAGE,
                    Sort.Direction.ASC, sortBy.orElse("id"));

                if (productRepository.findByDescription(description, pageable).isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }

                return new ResponseEntity<>(productRepository.findByDescription(description, pageable), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/filterByPrice")
    public ResponseEntity<Page<Product>> getAllProductsByPrice(
            @RequestParam Long price,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy) {

        try {
            Pageable pageable = PageRequest.of(
                    page.orElse(0), NUMBER_OF_ELEMENTS_PER_PAGE,
                    Sort.Direction.ASC, sortBy.orElse("id"));

                if (productRepository.findByPrice(price, pageable).isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }

                return new ResponseEntity<>(productRepository.findByPrice(price, pageable), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
//        return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
//    }
//    @PostMapping("/products")
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductCreationDTO productCreationDTO) {
        Product product = productMapper.toProduct(productCreationDTO);
        ProductDTO productDTO = productMapper.toDto(product);

        productService.save(product);
        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProductById(@PathVariable(name = "id") Long id) {
        try {
            productService.deleteProductById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
