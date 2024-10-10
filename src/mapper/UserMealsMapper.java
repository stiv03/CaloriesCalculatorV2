package mapper;

import dto.MealResponseDTO;
import entity.Meal;


public final class UserMealsMapper {

    private UserMealsMapper() {
    }

    public static MealResponseDTO mapToUserProductDTO(Meal userMeals) {
        return new MealResponseDTO(
                userMeals.getId(),
                userMeals.getProduct(),
                userMeals.getQuantity(),
                userMeals.getConsumedAt()
        );
    }

}

