package controller;

import dto.DailyMacrosDTO;
import dto.MealResponseDTO;
import service.MealService;

import java.time.LocalDate;
import java.util.List;

public class MealController {
    private final MealService mealService = new MealService();

    public void addMealForUser(Long userId, long productId, int grams) {
        mealService.addMealForUser(userId, productId, grams);
    }

    public List<MealResponseDTO> findAllUserMealsForSpecificDay(Long userId, LocalDate date) {
        return mealService.findAllUserMealsRelForSpecificDay(userId, date);
    }

    public DailyMacrosDTO calculateAndDisplayDailyMacros(Long userId, LocalDate date) {
        return mealService.calculateDailyMacros(userId, date);
    }

    public void updateMealQuantity(long mealId, double newQuantity) {
        mealService.updateMealQuantity(mealId, newQuantity);
    }

    public void deleteMeal(long mealId) {
        mealService.deleteMeal(mealId);
    }

    public List<DailyMacrosDTO> fetchAllMacros(Long userId) {
        return mealService.fetchAllMacros(userId);
    }
}
