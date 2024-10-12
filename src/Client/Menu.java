package Client;

import controller.MealController;
import controller.ProductController;
import controller.UserController;

import java.util.Scanner;

public class Menu {
    private static final UserController userController = new UserController();
    private static final MealController mealController = new MealController();
    private static final ProductController productController = new ProductController();
    private static final Scanner sc = new Scanner(System.in);

    public static void userProfileMenu() {
        boolean backToMain = false;

        while (!backToMain) {
            System.out.println("\nUser Menu:");
            System.out.println("1. View Profile");
            System.out.println("2. Update Height");
            System.out.println("3. Update Age");
            System.out.println("4. Update Weight");
            System.out.println("5. View Weight Records");
            System.out.println("6. Add Measurement");
            System.out.println("7. View Latest Measurements");
            System.out.println("8. Logout");

            System.out.print("Enter your choice: ");
            String userChoice = sc.nextLine().trim();

            switch (userChoice) {
                case "1":
                    userController.displayUserProfile();
                    break;

                case "2":
                    userController.updateHeight();
                    break;

                case "3":
                    userController.updateAge();
                    break;

                case "4":

                    userController.updateWeight();
                    break;

                case "5":
                    userController.displayWeightRecords();
                    break;

                case "6":
                    userController.addMeasurement();
                    break;

                case "7":

                    userController.displayLastUserMeasurements();
                    break;

                case "8":
                    UserSession.logout();
                    System.out.println("Logged out successfully.");
                    backToMain = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void mealMenu() {
        boolean backToMain = false;

        while (!backToMain) {
            System.out.println("\nMeal Menu:");
            System.out.println("1. Add Meal");
            System.out.println("2. Search in product library");
            System.out.println("3. View Meals for Today");
            System.out.println("4. Update Meal Quantity");
            System.out.println("5. Delete Meal");
            System.out.println("6. View Today's Macros");
            System.out.println("7. View All Macros");
            System.out.println("8. Logout");

            System.out.print("Enter your choice: ");
            String mealChoice = sc.nextLine().trim();

            switch (mealChoice) {
                case "1":
                    mealController.addMealForUser();
                    break;
                case "2":

                    productController.searchProducts();
                    break;

                case "3":
                    mealController.findAllUserMealsForSpecificDay();
                    break;

                case "4":
                    mealController.updateMealQuantity();
                    break;

                case "5":
                    mealController.deleteMeal();
                    break;

                case "6":
                    mealController.calculateAndDisplayDailyMacros();
                    break;

                case "7":
                    mealController.fetchAllMacros();
                    break;

                case "8":
                    UserSession.logout();
                    System.out.println("Logged out successfully.");
                    backToMain = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void productMenu() {
        boolean backToMain = false;

        while (!backToMain) {
            System.out.println("\nProduct Menu:");
            System.out.println("1. Add New Product");
            System.out.println("2. Back to Main Menu");

            System.out.print("Enter your choice: ");
            String productChoice = sc.nextLine().trim();

            switch (productChoice) {
                case "1":
                    productController.addNewProduct();
                    break;

                case "2":
                    backToMain = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
