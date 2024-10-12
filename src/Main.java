import Client.Menu;
import Client.UserSession;
import config.DatabaseInitializer;
import controller.UserController;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    private static final UserController userController = new UserController();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        DatabaseInitializer dbInitializer = new DatabaseInitializer();
        dbInitializer.initializeDatabaseFromFile();

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
            System.out.println("4. Logout");

            System.out.print("Enter your choice: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    Menu.userProfileMenu();
                    break;

                case "2":
                    Menu.mealMenu();
                    break;

                case "3":
                    Menu.productMenu();
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



}

