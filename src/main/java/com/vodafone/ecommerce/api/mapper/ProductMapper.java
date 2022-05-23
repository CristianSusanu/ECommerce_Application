package com.vodafone.ecommerce.api.mapper;

import com.vodafone.ecommerce.models.dto.ProductCreationDTO;
import com.vodafone.ecommerce.models.dto.ProductDTO;
import com.vodafone.ecommerce.models.entities.Product;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

@Component
public class ProductMapper {

    ModelMapper modelMapper = new ModelMapper();

    public ProductDTO toDto(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    public Product toProduct(ProductCreationDTO productCreationDTO) {
        return modelMapper.map(productCreationDTO, Product.class);
    }
}
