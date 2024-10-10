package dto;

import entity.Product;

import java.time.LocalDate;


public record MealResponseDTO(Long mealId, Product product, double quantity, LocalDate consumedAt) {

}

