package client;

import controller.MealController;
import controller.ProductController;
import controller.UserController;

import java.sql.SQLException;
import java.util.Scanner;

public final class Menu {
    private static final UserController userController = new UserController();
    private static final MealController mealController = new MealController();
    private static final ProductController productController = new ProductController();
    private static final Scanner sc = new Scanner(System.in);

    private Menu() {
    }

    public static void welcomeMenu() {
        boolean exit = false;


        while (!exit) {
            System.out.println("Welcome! Please select an option:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    userController.registerUser();
                    break;

                case "2":
                    try {
                        Long loggedInUserId = userController.login();
                        if (loggedInUserId != null) {
                            mainMenu();
                        }
                    } catch (SQLException e) {
                        System.out.println("Error during login: " + e.getMessage());
                    }
                    break;

                case "3":
                    System.out.println("Thank you for using the application. Goodbye!");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void mainMenu() {
        boolean backToMain = false;

        while (!backToMain) {
            System.out.println("\nMain Menu:");
            System.out.println("1. User Profile Management");
            System.out.println("2. Meal Management");
            System.out.println("3. Product Management");
            System.out.println("4. Log out");

            System.out.print("Enter your choice: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    userProfileMenu();
                    break;

                case "2":
                    mealMenu();
                    break;

                case "3":
                    productMenu();
                    break;

                case "4":
                    UserSession.logout();
                    System.out.println("Logged out successfully.");
                    backToMain = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    private static void userProfileMenu() {
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
            System.out.println("8. View All Measurements ");
            System.out.println("9.  Back to Main Menu");

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

                    userController.displayAllUserMeasurements();
                    break;

                case "9":
                    backToMain = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void mealMenu() {
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
            System.out.println("8.  Back to Main Menu");

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
                    backToMain = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void productMenu() {
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
