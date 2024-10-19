package view;
import client.PasswordUtils;
import dto.RegisterDTO;
import dto.UserDTO;
import entity.enums.UserType;
import java.util.List;
import java.util.Scanner;
import dto.*;

public class UserView {
    private final Scanner sc = new Scanner(System.in);

    public String getUserInput(String prompt) {
        System.out.print(prompt + ": ");
        return sc.nextLine().trim();
    }

    public String getUsernameInput() {
        return getUserInput("Username");
    }

    public String getPasswordInput() {
        return getUserInput("Password");
    }

    public void displayLoginSuccess(Long userId) {
        System.out.println("Login successful. User ID: " + userId);
    }

    public void displayLoginFailure() {
        System.out.println("Invalid username or password.");
    }

    public RegisterDTO getRegistrationInput() {
        System.out.println("Register a new user:");

        String name = getValidatedString("Enter Name", "[a-zA-Z ]+");
        int age = getPositiveInt("Enter Age");
        double weight = getPositiveDouble("Enter Weight (kg)");
        int height = getPositiveInt("Enter Height (cm)");
        String username = getValidatedString("Enter Username", ".+");
        String password = getValidatedString("Enter Password", ".+");

        String hashedPassword = PasswordUtils.hashPassword(password);

        return new RegisterDTO(name, age, weight, height, username, hashedPassword, UserType.USER);
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

    public String getValidatedString(String prompt, String regex) {
        String value;
        while (true) {
            System.out.print(prompt + ": ");
            value = sc.nextLine().trim();
            if (!value.isEmpty() && value.matches(regex)) break;
            System.out.println("Invalid input. Please try again.");
        }
        return value;
    }

    public void displayUserProfile(UserDTO userDTO) {
        System.out.println("User Details:");
        System.out.println("Name: " + userDTO.name());
        System.out.println("Age: " + userDTO.age());
        System.out.println("Weight: " + userDTO.weight());
        System.out.println("Height: " + userDTO.height());
        System.out.println("User Type: " + userDTO.userType());
    }


    public void displayWeightRecords(List<WeightRecordDTO> records) {
        System.out.println("Weight Records:");
        for (WeightRecordDTO weightRecord : records) {
            System.out.println("Date: " + weightRecord.date() + ", Weight: " + weightRecord.weight());
        }
    }

    public UpdateUserMeasurementsRequestDTO getMeasurementInput() {
        System.out.println("Enter measurements:");

        double shoulder = getPositiveDouble("Shoulder");
        double chest = getPositiveDouble("Chest");
        double biceps = getPositiveDouble("Biceps");
        double waist = getPositiveDouble("Waist");
        double hips = getPositiveDouble("Hips");
        double thigh = getPositiveDouble("Thigh");
        double calf = getPositiveDouble("Calf");

        return new UpdateUserMeasurementsRequestDTO(shoulder, chest, biceps, waist, hips, thigh, calf);
    }

    public void displayMeasurementRecord(MeasurementsRecordDTO dto) {
        System.out.println("Measurement Record:");

        System.out.println("Date: " + dto.date() + ", Shoulder: " + dto.shoulder() +
                ", Chest: " + dto.chest() + ", Biceps: " + dto.biceps() +
                ", Waist: " + dto.waist() + ", Hips: " + dto.hips() +
                ", Thigh: " + dto.thigh() + ", Calf: " + dto.calf());
    }

    public void displayAllMeasurementRecords(List<MeasurementsRecordDTO> records) {
        for (MeasurementsRecordDTO measurementsRecord : records) {
            displayMeasurementRecord(measurementsRecord);
            System.out.println("---");
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}

