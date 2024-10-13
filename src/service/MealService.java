package service;

import dto.DailyMacrosDTO;
import dto.MealResponseDTO;
import entity.Meal;
import mapper.UserMealsMapper;
import repository.MealsRepository;
import repository.ProductRepository;
import repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MealService {

    public static final int HUNDRED_GRAMS_DENOMINATOR = 100;
    private final MealsRepository mealsRepository = new MealsRepository();
    private final UserRepository userRepository = new UserRepository();
    private final ProductRepository productRepository = new ProductRepository();


    public void addMealForUser(final Long userId, Long productId, Integer grams) {
        var newMeal = new Meal();

        var user = userRepository.getUserById(userId);
        var product = productRepository.getProductById(productId);


        newMeal.setUser(user);
        newMeal.setProduct(product);
        newMeal.setQuantity(grams);
        newMeal.setConsumedAt(LocalDate.now());

        mealsRepository.addNewMeal(newMeal);
    }


    public List<MealResponseDTO> findAllUserMealsRelForSpecificDay(final Long userId, LocalDate date) {

        var allMealsEaten = mealsRepository.findAllMealsByUserId(userId);

        List<MealResponseDTO> mealsEatenForGivenDay = new ArrayList<>();

        for (var meal : allMealsEaten) {
            if (date.equals(meal.getConsumedAt())) {
                mealsEatenForGivenDay.add(UserMealsMapper.mapToUserProductDTO(meal));
            }
        }
        return mealsEatenForGivenDay;
    }

    public DailyMacrosDTO calculateDailyMacros(Long userId, LocalDate date) {
        List<MealResponseDTO> allMealsForDay = findAllUserMealsRelForSpecificDay(userId, date);
        double totalCalories = 0;
        double totalProtein = 0;
        double totalCarbs = 0;
        double totalFats = 0;

        for (final var meal : allMealsForDay) {
            var product = meal.product();
            double quantity = meal.quantity();
            totalCalories += product.getCaloriesPer100Grams() * quantity / HUNDRED_GRAMS_DENOMINATOR;
            totalProtein += product.getProteinPer100Grams() * quantity / HUNDRED_GRAMS_DENOMINATOR;
            totalCarbs += product.getCarbsPer100Grams() * quantity / HUNDRED_GRAMS_DENOMINATOR;
            totalFats += product.getFatPer100Grams() * quantity / HUNDRED_GRAMS_DENOMINATOR;
        }
        return new DailyMacrosDTO(date.toString(), (int) totalCalories, totalProtein, totalFats, totalCarbs);
    }

    public void updateMealQuantity(final long id, double newQuantity) {
        mealsRepository.updateMealQuantity(id, newQuantity);
    }

    public void deleteMeal(final long mealId) {
        mealsRepository.deleteMeal(mealId);
    }

    public List<DailyMacrosDTO> fetchAllMacros(Long userId) {
        return mealsRepository.findAllMealsByUserId(userId)
                .stream()
                .collect(Collectors.groupingBy(Meal::getConsumedAt))
                .keySet().stream()
                .map(date -> calculateDailyMacros(userId, date))
                .toList();
    }
}
