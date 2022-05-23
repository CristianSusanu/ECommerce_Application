package com.vodafone.ecommerce.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreationDTO {

    private String name;
    private String description;
//    private String categoryName;
    private Long price;
}
