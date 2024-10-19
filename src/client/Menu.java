package client;

import view.MealView;
import view.ProductView;
import view.UserView;
import controller.MealController;
import controller.ProductController;
import controller.UserController;
import dto.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public final class Menu {
    private static final UserController userController = new UserController();
    private static final MealController mealController = new MealController();
    private static final ProductController productController = new ProductController();
    private static final UserView userView = new UserView();
    private static final MealView mealView = new MealView();
    private static final ProductView productView = new ProductView();
    private static final Scanner sc = new Scanner(System.in);

    private Menu() {}

    public static void welcomeMenu() {
        boolean exit = false;

        while (!exit) {
            displayWelcomeMenu();
            String choice = userView.getUserInput("Enter your choice");

            switch (choice) {
                case "1":
                    handleRegistration();
                    break;
                case "2":
                    handleLogin();
                    break;
                case "3":
                    userView.displayMessage("Thank you for using the application. Goodbye!");
                    exit = true;
                    break;
                default:
                    userView.displayMessage("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayWelcomeMenu() {
        System.out.println("Welcome! Please select an option:");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
    }

    private static void handleRegistration() {
        RegisterDTO registerDTO = userView.getRegistrationInput();
        userController.registerUser(registerDTO);
        userView.displayMessage("User registered successfully!");
    }

    private static void handleLogin() {
        String username = userView.getUsernameInput();
        String password = userView.getPasswordInput();
        Long loggedInUserId = userController.login(username, password);
        if (loggedInUserId != null) {
            userView.displayLoginSuccess(loggedInUserId);
            mainMenu();
        } else {
            userView.displayLoginFailure();
        }
    }

    private static void mainMenu() {
        boolean backToMain = false;

        while (!backToMain) {
            displayMainMenu();
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
                    handleLogout();
                    backToMain = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. User Profile Management");
        System.out.println("2. Meal Management");
        System.out.println("3. Product Management");
        System.out.println("4. Log out");
    }

    private static void handleLogout() {
        UserSession.logout();
        System.out.println("Logged out successfully.");
    }

    private static void userProfileMenu() {
        boolean backToMain = false;

        while (!backToMain) {
            displayUserProfileMenu();
            String userChoice = userView.getUserInput("Enter your choice");

            switch (userChoice) {
                case "1":
                    displayUserProfile();
                    break;
                case "2":
                    updateHeight();
                    break;
                case "3":
                    updateAge();
                    break;
                case "4":
                    updateWeight();
                    break;
                case "5":
                    displayWeightRecords();
                    break;
                case "6":
                    addMeasurement();
                    break;
                case "7":
                    displayLatestMeasurements();
                    break;
                case "8":
                    displayAllMeasurements();
                    break;
                case "9":
                    backToMain = true;
                    break;
                default:
                    userView.displayMessage("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayUserProfileMenu() {
        System.out.println("\nUser Menu:");
        System.out.println("1. View Profile");
        System.out.println("2. Update Height");
        System.out.println("3. Update Age");
        System.out.println("4. Update Weight");
        System.out.println("5. View Weight Records");
        System.out.println("6. Add Measurement");
        System.out.println("7. View Latest Measurements");
        System.out.println("8. View All Measurements");
        System.out.println("9. Back to Main Menu");
    }

    private static void displayUserProfile() {
        Long userId = checkUserLoggedIn();
        if (userId != null) {
            UserDTO userDTO = userController.displayUserProfile(userId);
            if (userDTO != null) {
                userView.displayUserProfile(userDTO);
            } else {
                userView.displayMessage("User not found.");
            }
        }
    }

    private static void updateHeight() {
        int newHeight = userView.getPositiveInt("Enter new height");
        userController.updateHeight(checkUserLoggedIn(), newHeight);
        userView.displayMessage("Height updated successfully.");
    }

    private static void updateAge() {
        int newAge = userView.getPositiveInt("Enter new age");
        userController.updateAge(checkUserLoggedIn(), newAge);
        userView.displayMessage("Age updated successfully.");
    }

    private static void updateWeight() {
        double newWeight = userView.getPositiveDouble("Enter new weight");
        userController.updateWeight(checkUserLoggedIn(), newWeight);
        userView.displayMessage("Weight updated successfully.");
    }

    private static void displayWeightRecords() {
        Long userId = checkUserLoggedIn();
        List<WeightRecordDTO> weightRecords = userController.getWeightRecords(userId);
        if (weightRecords != null && !weightRecords.isEmpty()) {
            userView.displayWeightRecords(weightRecords);
        } else {
            userView.displayMessage("No weight records found.");
        }
    }

    private static void addMeasurement() {
        UpdateUserMeasurementsRequestDTO measurements = userView.getMeasurementInput();
        userController.addMeasurement(checkUserLoggedIn(), measurements);
        userView.displayMessage("Measurement added successfully.");
    }

    private static void displayLatestMeasurements() {
        Long userId = checkUserLoggedIn();
        MeasurementsRecordDTO latestMeasurement = userController.getLastUserMeasurement(userId);
        if (latestMeasurement != null) {
            userView.displayMeasurementRecord(latestMeasurement);
        } else {
            userView.displayMessage("No measurements found.");
        }
    }

    private static void displayAllMeasurements() {
        Long userId = checkUserLoggedIn();
        List<MeasurementsRecordDTO> allMeasurements = userController.getAllUserMeasurements(userId);
        if (allMeasurements != null && !allMeasurements.isEmpty()) {
            userView.displayAllMeasurementRecords(allMeasurements);
        } else {
            userView.displayMessage("No measurements found.");
        }
    }

    private static void mealMenu() {
        boolean backToMain = false;

        while (!backToMain) {
            displayMealMenu();
            String choice = userView.getUserInput("Enter your choice");

            switch (choice) {
                case "1":
                    addMeal();
                    break;
                case "2":
                    searchProduct();
                    break;
                case "3":
                    viewMealsForToday();
                    break;
                case "4":
                    updateMealQuantity();
                    break;
                case "5":
                    deleteMeal();
                    break;
                case "6":
                    viewTodaysMacros();
                    break;
                case "7":
                    viewAllMacros();
                    break;
                case "8":
                    backToMain = true;
                    break;
                default:
                    mealView.displayMessage("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMealMenu() {
        System.out.println("\nMeal Menu:");
        System.out.println("1. Add Meal");
        System.out.println("2. Search in Product Library");
        System.out.println("3. View Meals for Today");
        System.out.println("4. Update Meal Quantity");
        System.out.println("5. Delete Meal");
        System.out.println("6. View Today's Macros");
        System.out.println("7. View All Macros");
        System.out.println("8. Back to Main Menu");
    }

    private static void addMeal() {
        Long userId = checkUserLoggedIn();
        MealDTO mealDTO = mealView.getMealInput();
        mealController.addMealForUser(userId, mealDTO.productId(), mealDTO.grams());
        mealView.displayMessage("Meal added successfully.");
    }

    private static void searchProduct() {
        String query = productView.getProductSearchQuery();
        List<ProductResponseDTO> products = productController.searchProducts(query);
        if (products != null && !products.isEmpty()) {
            productView.displayProducts(products);
        } else {
            productView.displayMessage("No products found.");
        }
    }

    private static void viewMealsForToday() {
        Long userId = checkUserLoggedIn();
        if (userId != null) {
            LocalDate today = LocalDate.now();
            List<MealResponseDTO> meals = mealController.findAllUserMealsForSpecificDay(userId, today);
            if (meals != null && !meals.isEmpty()) {
                mealView.displayMeals(meals);
            } else {
                mealView.displayMessage("No meals found for today.");
            }
        } else {
            mealView.displayMessage("No user is currently logged in.");
        }
    }

    private static void updateMealQuantity() {
        long mealId = mealView.getLongInput("Enter Meal ID");
        double newQuantity = mealView.getPositiveDouble("Enter new quantity (grams)");
        mealController.updateMealQuantity(mealId, newQuantity);
        mealView.displayMessage("Meal quantity updated successfully.");
    }

    private static void deleteMeal() {
        long mealId = mealView.getLongInput("Enter Meal ID to delete");
        mealController.deleteMeal(mealId);
        mealView.displayMessage("Meal deleted successfully.");
    }

    private static void viewTodaysMacros() {
        Long userId = checkUserLoggedIn();
        if (userId != null) {
            LocalDate today = LocalDate.now();
            DailyMacrosDTO dailyMacros = mealController.calculateAndDisplayDailyMacros(userId, today);
            if (dailyMacros != null) {
                mealView.displayDailyMacros(dailyMacros);
            } else {
                mealView.displayMessage("No macros data found for today.");
            }
        } else {
            mealView.displayMessage("No user is currently logged in.");
        }
    }

    private static void viewAllMacros() {
        Long userId = checkUserLoggedIn();
        if (userId != null) {
            List<DailyMacrosDTO> allMacros = mealController.fetchAllMacros(userId);
            if (allMacros != null && !allMacros.isEmpty()) {
                mealView.displayAllMacros(allMacros);
            } else {
                mealView.displayMessage("No macros data found.");
            }
        } else {
            mealView.displayMessage("No user is currently logged in.");
        }
    }

    private static void productMenu() {
        boolean backToMain = false;

        while (!backToMain) {
            displayProductMenu();
            String productChoice = userView.getUserInput("Enter your choice");

            switch (productChoice) {
                case "1":
                    addProduct();
                    break;
                case "2":
                    backToMain = true;
                    break;
                default:
                    productView.displayMessage("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayProductMenu() {
        System.out.println("\nProduct Menu:");
        System.out.println("1. Add New Product");
        System.out.println("2. Back to Main Menu");
    }

    private static void addProduct() {
        ProductDTO productDTO = productView.getProductInput();
        productController.addNewProduct(productDTO);
        productView.displayMessage("Product added successfully.");
    }

    private static Long checkUserLoggedIn() {
        Long userId = UserSession.getLoggedInUserId();
        if (userId == null) {
            userView.displayMessage("No user is currently logged in.");
        }
        return userId;
    }
}
