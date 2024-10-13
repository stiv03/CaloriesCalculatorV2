package controller;

import client.UserSession;
import dto.DailyMacrosDTO;
import dto.MealResponseDTO;
import service.MealService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MealController {
    private final MealService mealService = new MealService();

    private final Scanner sc = new Scanner(System.in);

    public void addMealForUser() {
        if (!UserSession.isLoggedIn()) {
            System.out.println("No user is currently logged in. Please log in first.");
            return;
        }

        Long userId = UserSession.getLoggedInUserId();
        long productId;
        int grams;

        while (true) {
            try {
                System.out.print("Enter Product ID: ");
                productId = Long.parseLong(sc.nextLine().trim());
                if (productId > 0) break;

                System.out.println("Product ID must be a positive number. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for Product ID.");
            }
        }

        while (true) {
            try {
                System.out.print("Enter quantity in grams: ");
                grams = Integer.parseInt(sc.nextLine().trim());
                if (grams > 0) break;

                System.out.println("Quantity must be a positive");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for quantity.");
            }
        }

        try {
            mealService.addMealForUser(userId, productId, grams);
            System.out.println("Meal added successfully for user ID: " + userId);
        } catch (Exception e) {
            System.err.println("An error occurred " + e.getMessage());
        }
    }

    public void findAllUserMealsForSpecificDay() {
        Long userId = UserSession.getLoggedInUserId();
        if (userId == null) {
            System.out.println("No user is currently logged in.");
            return;
        }
        LocalDate today = LocalDate.now();

        try {
            List<MealResponseDTO> meals = mealService.findAllUserMealsRelForSpecificDay(userId, today);

            if (meals.isEmpty()) {
                System.out.println("No meals found for the specified date: " + today);
            } else {
                System.out.println("Meals for today " + today + ":");
                meals.forEach(meal -> {
                    System.out.println("& MealId & Product: " + meal.mealId() + " " + meal.product().getName() + " "
                            + meal.quantity() + " g "
                            + meal.product().getCaloriesPer100Grams() * meal.quantity() / 100 + " kcal "
                            + meal.product().getProteinPer100Grams() * meal.quantity() / 100 + " protein "
                            + meal.product().getFatPer100Grams() * meal.quantity() / 100 + " fat "
                            + meal.product().getCarbsPer100Grams() * meal.quantity() / 100 + " carb");
                    System.out.println("---");
                });
            }

        } catch (Exception e) {
            System.err.println("An error occurred" + e.getMessage());
        }
    }

    public void calculateAndDisplayDailyMacros() {
        Long userId = UserSession.getLoggedInUserId();
        if (userId == null) {
            System.out.println("No user is currently logged in.");
            return;
        }
        LocalDate today = LocalDate.now();

        try {
            DailyMacrosDTO dailyMacros = mealService.calculateDailyMacros(userId, today);

            if (dailyMacros == null) {
                System.out.println("No macros data found for user ID: " + userId);
            } else {
                System.out.println("Daily Macros for today (" + today + "):");
                System.out.println("Calories: " + dailyMacros.calories());
                System.out.println("Protein: " + dailyMacros.protein() + "g");
                System.out.println("Carbs: " + dailyMacros.carb() + "g");
                System.out.println("Fats: " + dailyMacros.fat() + "g");
            }
        } catch (Exception e) {
            System.err.println("An error occurred while fetching macros: " + e.getMessage());
        }
    }

    public void updateMealQuantity() {
        long mealId;
        double newQuantity;

        while (true) {
            System.out.print("Enter Meal ID: ");
            try {
                mealId = Long.parseLong(sc.nextLine().trim());
                if (mealId > 0) break;
                System.out.println(mealId + " must be a positive number. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for Meal ID.");
            }
        }

        while (true) {
            System.out.print("Enter new quantity (grams): ");
            try {
                newQuantity = Double.parseDouble(sc.nextLine().trim());
                if (newQuantity > 0) break;

                System.out.println(newQuantity + " must be a positive number. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for quantity.");
            }
        }

        try {
            mealService.updateMealQuantity(mealId, newQuantity);
            System.out.println("Meal quantity updated successfully.");
        } catch (Exception e) {
            System.err.println("An error occurred while updating the meal quantity: ");
        }
    }

    public void deleteMeal() {
        long mealId;

        while (true) {
            System.out.print("Enter Meal ID to delete: ");
            try {
                mealId = Long.parseLong(sc.nextLine().trim());
                if (mealId > 0) break;

                System.out.println("Meal ID must be a positive number. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for Meal ID.");
            }
        }

        try {
            mealService.deleteMeal(mealId);
        } catch (Exception e) {
            System.err.println("An error occurred while deleting the meal: " + e.getMessage());
        }
    }

    public void fetchAllMacros() {
        Long userId = UserSession.getLoggedInUserId();
        if (userId == null) {
            System.out.println("No user is currently logged in. Please log in first.");
            return;
        }

        try {
            List<DailyMacrosDTO> allMacros = mealService.fetchAllMacros(userId);

            if (allMacros.isEmpty()) {
                System.out.println("No macros data found for user ID: " + userId);
            } else {
                System.out.println("Daily Macros Summary:");
                allMacros.forEach(macros -> {
                    System.out.println("Date: " + macros.date());
                    System.out.println("Calories: " + macros.calories());
                    System.out.println("Protein: " + macros.protein() + "g");
                    System.out.println("Carbs: " + macros.carb() + "g");
                    System.out.println("Fats: " + macros.fat() + "g");
                    System.out.println("---");
                });
            }
        } catch (Exception e) {
            System.err.println("An error occurred while fetching macros: " + e.getMessage());
        }
    }

}

