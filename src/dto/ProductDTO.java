package dto;

import entity.enums.ProductType;


public record ProductDTO(String name,
                         Long productId,
                         ProductType productType,
                         double caloriesPer100Grams,
                         double proteinPer100Grams,
                         double fatPer100Grams,
                         double carbsPer100Grams) {
}
