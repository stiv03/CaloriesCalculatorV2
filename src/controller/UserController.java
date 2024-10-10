package controller;

import dto.MeasurementsRecordDTO;
import dto.UpdateUserMeasurementsRequestDTO;
import dto.UserDTO;
import dto.WeightRecordDTO;
import service.UserService;

import java.util.List;
import java.util.Scanner;

public class UserController {
    private final  UserService userService = new UserService();
    private final Scanner sc = new Scanner(System.in);

    public void displayUserProfile(long userId){
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

    public void updateHeight(long userId) {
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

    public void updateAge(long userId){
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

    public void updateWeight(long userId){
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

    public void displayWeightRecords(long userId){
        System.out.println("Weight record:");
        List<WeightRecordDTO> weightRecords = userService.getWeightRecords(userId);
        if (weightRecords.isEmpty()) {
            System.out.println("No weight records found for user ID: " + userId);
        } else {
            System.out.println("Weight records for user ID: " + userId);
            weightRecords.forEach(weightRecord-> System.out.println("Date: " + weightRecord.date() + ", Weight: " + weightRecord.weight()));
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

    public void addMeasurement(long userId) {
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

        MeasurementsRecordDTO result = userService.addMeasurement(userId, requestDTO);

        System.out.println("Measurement added successfully for user ID: " + userId);
        System.out.println("Date: " + result.date()+ ", Shoulder: " + result.shoulder() +
                ", Chest: " + result.chest() + ", Biceps: " + result.biceps() +
                ", Waist: " + result.waist() + ", Hips: " + result.hips() +
                ", Thigh: " + result.thigh() + ", Calf: " + result.calf());
    }

    public void displayUserMeasurements(long userId) {
        System.out.println("Measurements record:");
        List<MeasurementsRecordDTO> measurementsRecord = userService.getMeasurementsByUser(userId);
        if (measurementsRecord.isEmpty()) {
            System.out.println("No measurements records found for user ID: " + userId);
        } else {
            System.out.println("Measurements records for user ID: " + userId);
            measurementsRecord.forEach(mRecord -> System.out.println("Date: " + mRecord.date() + ", Shoulder: " + mRecord.shoulder() +
                    ", Chest: " + mRecord.chest() + ", Biceps: " + mRecord.biceps() +
                    ", Waist: " + mRecord.waist() + ", Hips: " + mRecord.hips() +
                    ", Thigh: " + mRecord.thigh() + ", Calf: " + mRecord.calf()));
        }
    }

        public void displayLastUserMeasurements(long userId){
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


