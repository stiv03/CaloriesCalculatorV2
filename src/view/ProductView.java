package view;
import dto.ProductDTO;
import dto.ProductResponseDTO;
import entity.enums.ProductType;

import java.util.List;
import java.util.Scanner;

public class ProductView {
    private final Scanner sc = new Scanner(System.in);

    public ProductDTO getProductInput() {
        System.out.println("Enter product details:");

        String name = getNonEmptyString("Product Name");
        ProductType productType = getProductType();
        double calories = getNonNegativeDouble("Calories per 100 grams");
        double protein = getNonNegativeDouble("Protein per 100 grams");
        double fat = getNonNegativeDouble("Fat per 100 grams");
        double carbs = getNonNegativeDouble("Carbs per 100 grams");

        return new ProductDTO(name, productType, calories, protein, fat, carbs);
    }

    public String getProductSearchQuery() {
        return getNonEmptyString("Search Product");
    }

    public void displayProducts(List<ProductResponseDTO> products) {
        for (ProductResponseDTO product : products) {
            System.out.println("ID: " + product.id() + ", Name: " + product.name());
            System.out.println("---");
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    private String getNonEmptyString(String prompt) {
        String value;
        while (true) {
            System.out.print(prompt + ": ");
            value = sc.nextLine().trim();
            if (!value.isEmpty()) break;
            System.out.println("Input cannot be empty.");
        }
        return value;
    }

    private ProductType getProductType() {
        while (true) {
            System.out.print("Product Type: ");
            String typeInput = sc.nextLine().trim().toUpperCase();
            try {
                return ProductType.valueOf(typeInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid product type. Please try again.");
            }
        }
    }

    private double getNonNegativeDouble(String prompt) {
        double value;
        while (true) {
            System.out.print(prompt + ": ");
            try {
                value = Double.parseDouble(sc.nextLine().trim());
                if (value >= 0) break;
                System.out.println("Value must be non-negative.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a valid number.");
            }
        }
        return value;
    }
}

