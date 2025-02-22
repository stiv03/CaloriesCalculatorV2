package view;

import dto.DailyMacrosDTO;
import dto.MealDTO;
import dto.MealResponseDTO;

import java.util.List;
import java.util.Scanner;

public class MealView {
    private final Scanner sc = new Scanner(System.in);


    public MealDTO getMealInput() {
        long productId = getLongInput("Enter Product ID");
        int grams = getPositiveInt("Enter quantity in grams");
        return new MealDTO(productId, grams);
    }


    public long getLongInput(String prompt) {
        long value;
        while (true) {
            System.out.print(prompt + ": ");
            try {
                value = Long.parseLong(sc.nextLine().trim());
                if (value > 0) break;
                System.out.println("Value must be positive.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a valid number.");
            }
        }
        return value;
    }


    public int getPositiveInt(String prompt) {
        int value;
        while (true) {
            System.out.print(prompt + ": ");
            try {
                value = Integer.parseInt(sc.nextLine().trim());
                if (value > 0) break;
                System.out.println("Value must be positive.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a valid integer.");
            }
        }
        return value;
    }


    public double getPositiveDouble(String prompt) {
        double value;
        while (true) {
            System.out.print(prompt + ": ");
            try {
                value = Double.parseDouble(sc.nextLine().trim());
                if (value > 0) break;
                System.out.println("Value must be positive.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a valid number.");
            }
        }
        return value;
    }


    public void displayMeals(List<MealResponseDTO> meals) {
        System.out.println("Meals for today " + ":");
        meals.forEach(meal -> {
            System.out.println("MealId & Product: " + meal.mealId() + " " + meal.product().getName() + " "
                    + meal.quantity() + " g "
                    + meal.product().getCaloriesPer100Grams() * meal.quantity() / 100 + " kcal "
                    + meal.product().getProteinPer100Grams() * meal.quantity() / 100 + " protein "
                    + meal.product().getFatPer100Grams() * meal.quantity() / 100 + " fat "
                    + meal.product().getCarbsPer100Grams() * meal.quantity() / 100 + " carb");
            System.out.println("---");
        });
    }


    public void displayDailyMacros(DailyMacrosDTO macros) {
        System.out.println("Date: " + macros.date());
        System.out.println("Calories: " + macros.calories() + " kcal");
        System.out.println("Protein: " + macros.protein() + " g");
        System.out.println("Carbs: " + macros.carb() + " g");
        System.out.println("Fats: " + macros.fat() + " g");
    }


    public void displayAllMacros(List<DailyMacrosDTO> macrosList) {
        for (DailyMacrosDTO macros : macrosList) {
            displayDailyMacros(macros);
            System.out.println("---");
        }
    }


    public void displayMessage(String message) {
        System.out.println(message);
    }
}
