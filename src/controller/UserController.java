package controller;

import Client.UserSession;
import dto.*;
import entity.enums.UserType;
import service.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserController {
    private final  UserService userService = new UserService();
    private final Scanner sc = new Scanner(System.in);

    public Long login() throws SQLException {
        System.out.println("Login:");

        Long loggedUserId;

        do {
            System.out.print("Username: ");
            String username = sc.nextLine().trim();

            System.out.print("Password: ");
            String password = sc.nextLine().trim();

            loggedUserId = userService.login(new LoginDTO(username, password));

            if (loggedUserId == null) {
                System.out.println("Invalid username or password.");
            }
        } while (loggedUserId == null);

        UserSession.setLoggedInUserId(loggedUserId);
        System.out.println("Login successful. User ID: " + loggedUserId);
        return loggedUserId;
    }

    public void registerUser() {
        System.out.println("Register a new user:");

        String name;
        while (true) {
            System.out.print("Enter Name: ");
            name = sc.nextLine().trim();
            if (!name.isEmpty() && name.matches("[a-zA-Z ]+")) break;
            System.out.println("Invalid name. Please enter a valid name");
        }

        int age;
        while (true) {
            System.out.print("Enter Age: ");
            try {
                age = Integer.parseInt(sc.nextLine().trim());
                if (age > 0) break;
                System.out.println("Age must be a positive number");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for age.");
            }
        }

        double weight;
        while (true) {
            System.out.print("Enter Weight (kg): ");
            try {
                weight = Double.parseDouble(sc.nextLine().trim());
                if (weight > 0) break;

                System.out.println("Weight must be a positive number. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for weight.");
            }
        }

        int height;
        while (true) {
            System.out.print("Enter Height (cm): ");
            try {
                height = Integer.parseInt(sc.nextLine().trim());
                if (height > 0) break;

                System.out.println("Height must be a positive number. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for height.");
            }
        }

        String username;
        while (true) {
            System.out.print("Enter Username: ");
            username = sc.nextLine().trim();
            if (!username.isEmpty()) break;

            System.out.println("Username cannot be empty. Please try again.");
        }

        String password;
        while (true) {
            System.out.print("Enter Password: ");
            password = sc.nextLine().trim();
            if (!password.isEmpty()) break;

            System.out.println("Password cannot be empty. Please try again.");
        }

        RegisterDTO registerDTO = new RegisterDTO(name, age, weight, height, username, password, UserType.USER);

        try {
            userService.register(registerDTO);
            System.out.println("User registered successfully!");
        } catch (SQLException e) {
            System.err.println("An error occurred while registering the user: " + e.getMessage());
        }
    }



    public void displayUserProfile(){
        Long userId = UserSession.getLoggedInUserId();
        if (userId == null) {
            System.out.println("No user is currently logged in.");
            return;
        }
        UserDTO userDTO = userService.getUserById(userId);
        if (userDTO == null) {
            System.out.println("User with ID " + userId + " not found.");
        } else {
            System.out.println("User Details:");
            System.out.println("Name: " + userDTO.name());
            System.out.println("Age: " + userDTO.age());
            System.out.println("Weight: " + userDTO.weight());
            System.out.println("Height: " + userDTO.height());
            System.out.println("User Type: " + userDTO.userType());
        }
    }

    public void updateHeight() {
        Long userId = UserSession.getLoggedInUserId();
        if (userId == null) {
            System.out.println("No user is currently logged in.");
            return;
        }
        System.out.print("Enter new height: ");
        int newHeight;
        while (true) {
            try {
                newHeight = Integer.parseInt(sc.nextLine());
                if (newHeight > 0) break;
                System.out.println("Height must be a positive integer. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for height.");
            }
        }
        userService.updateHeight(userId, newHeight);
        System.out.println("Height updated successfully.");
    }

    public void updateAge(){
        Long userId = UserSession.getLoggedInUserId();
        if (userId == null) {
            System.out.println("No user is currently logged in.");
            return;
        }
        System.out.print("Enter new age: ");
        int newAge;
        while (true) {
            try {
                newAge = Integer.parseInt(sc.nextLine());
                if (newAge > 0) break;
                System.out.println("Age must be a positive integer. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for age.");
            }
        }
        userService.updateAge(userId, newAge);
        System.out.println("Age updated successfully.");
    }

    public void updateWeight(){
        Long userId = UserSession.getLoggedInUserId();
        if (userId == null) {
            System.out.println("No user is currently logged in.");
            return;
        }
        System.out.print("Enter new weight: ");
        double newWeight;
        while (true) {
            try {
                newWeight = Double.parseDouble(sc.nextLine());
                if (newWeight > 0) break;
                System.out.println("Weight must be a positive number. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for weight.");
            }
        }
        userService.updateWeight(userId, newWeight);
        System.out.println("Weight updated successfully.");
    }

    public void displayWeightRecords(){
        Long userId = UserSession.getLoggedInUserId();
        if (userId == null) {
            System.out.println("No user is currently logged in.");
            return;
        }
        System.out.println("Weight record:");
        List<WeightRecordDTO> weightRecords = userService.getWeightRecords(userId);
        if (weightRecords.isEmpty()) {
            System.out.println("No weight records found for user ID: " + userId);
        } else {
            System.out.println("Weight records for user ID: " + userId);
            weightRecords.forEach(weightRecord-> System.out.println("Date: " + weightRecord.date() + " Weight: " + weightRecord.weight()));
        }
    }

    private double getMeasurementInput(String measurementName) {
        double value;
        while (true) {
            System.out.print("Enter " + measurementName + ": ");
            try {
                value = Double.parseDouble(sc.nextLine());
                if (value > 0) break;
                System.out.println(measurementName + " must be a positive number. Please try again.");

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for " + measurementName + ".");
            }
        }
        return value;
    }

    public void addMeasurement() {
        Long userId = UserSession.getLoggedInUserId();
        if (userId == null) {
            System.out.println("No user is currently logged in.");
            return;
        }
        System.out.println("Enter measurements for user ID: " + userId);

        double shoulder = getMeasurementInput("Shoulder");
        double chest = getMeasurementInput("Chest");
        double biceps = getMeasurementInput("Biceps");
        double waist = getMeasurementInput("Waist");
        double hips = getMeasurementInput("Hips");
        double thigh = getMeasurementInput("Thigh");
        double calf = getMeasurementInput("Calf");

        UpdateUserMeasurementsRequestDTO requestDTO = new UpdateUserMeasurementsRequestDTO(
                shoulder, chest, biceps, waist, hips, thigh, calf
        );

        userService.addMeasurement(userId, requestDTO);

        System.out.println("Measurement added successfully for user ID: " + userId);
    }

    public void displayAllUserMeasurements(long userId) {
        System.out.println("Measurements record:");
        List<MeasurementsRecordDTO> measurementsRecord = userService.getMeasurementsByUser(userId);
        if (measurementsRecord.isEmpty()) {
            System.out.println("No measurements records found for user ID: " + userId);
        } else {
            System.out.println("Measurements records for user ID: " + userId);
            measurementsRecord.forEach(mRecord -> {
                System.out.println("Date: " + mRecord.date() + ", Shoulder: " + mRecord.shoulder() +
                    ", Chest: " + mRecord.chest() + ", Biceps: " + mRecord.biceps() +
                    ", Waist: " + mRecord.waist() + ", Hips: " + mRecord.hips() +
                    ", Thigh: " + mRecord.thigh() + ", Calf: " + mRecord.calf());
                 System.out.println("---");
            });
        }
    }

        public void displayLastUserMeasurements(){
            Long userId = UserSession.getLoggedInUserId();
            if (userId == null) {
                System.out.println("No user is currently logged in.");
                return;
            }
            System.out.println("Latest Measurements:");
           MeasurementsRecordDTO lastsMeasurements = userService.getLatestMeasurement(userId);
            if (lastsMeasurements == null) {
                System.out.println("No measurements records found for user ID: " + userId);
            } else {
                System.out.println("Measurements records for user ID: " + userId);
                System.out.println("Date: " + lastsMeasurements.date() + ", Shoulder: " + lastsMeasurements.shoulder() +
                        ", Chest: " + lastsMeasurements.chest() + ", Biceps: " + lastsMeasurements.biceps() +
                        ", Waist: " + lastsMeasurements.waist() + ", Hips: " + lastsMeasurements.hips() +
                        ", Thigh: " + lastsMeasurements.thigh() + ", Calf: " + lastsMeasurements.calf());
            }
        }
}


