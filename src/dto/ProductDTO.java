package dto;

import entity.enums.ProductType;


public record ProductDTO(String name,

                         ProductType productType,
                         double caloriesPer100Grams,
                         double proteinPer100Grams,
                         double fatPer100Grams,
                         double carbsPer100Grams) {
}
