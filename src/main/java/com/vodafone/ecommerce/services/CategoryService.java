package com.vodafone.ecommerce.services;

import com.vodafone.ecommerce.api.repositories.CategoryRepository;
import com.vodafone.ecommerce.models.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void save(Category category) {
        Objects.requireNonNull(category);
        categoryRepository.save(category);
    }
}
