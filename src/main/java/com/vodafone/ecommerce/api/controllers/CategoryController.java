package com.vodafone.ecommerce.api.controllers;

import com.vodafone.ecommerce.api.mapper.CategoryMapper;
import com.vodafone.ecommerce.models.dto.CategoryCreationDTO;
import com.vodafone.ecommerce.models.dto.CategoryDTO;
import com.vodafone.ecommerce.models.entities.Category;
import com.vodafone.ecommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    @PostMapping("/category")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryCreationDTO categoryCreationDTO) {
        Category category = categoryMapper.toCategory(categoryCreationDTO);
        CategoryDTO categoryDTO = categoryMapper.toDto(category);

        categoryService.save(category);
        return new ResponseEntity<>(categoryDTO, HttpStatus.CREATED);
    }
}
