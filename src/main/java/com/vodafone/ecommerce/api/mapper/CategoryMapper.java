package com.vodafone.ecommerce.api.mapper;

import com.vodafone.ecommerce.models.dto.CategoryCreationDTO;
import com.vodafone.ecommerce.models.dto.CategoryDTO;
import com.vodafone.ecommerce.models.entities.Category;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    ModelMapper modelMapper = new ModelMapper();

    public CategoryDTO toDto(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    public Category toCategory(CategoryCreationDTO categoryCreationDTO) {
        return modelMapper.map(categoryCreationDTO, Category.class);
    }
}
