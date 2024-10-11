package controller;

import dto.ProductDTO;
import dto.ProductResponseDTO;
import entity.enums.ProductType;
import service.ProductService;

import java.util.List;
import java.util.Scanner;

public class ProductController {

    private final ProductService productService = new ProductService();
    private final Scanner sc = new Scanner(System.in);


    public void addNewProduct() {
        System.out.println("Enter product details:");

        String name;
        while (true) {
            System.out.print("Product Name: ");
            name = sc.nextLine().trim();
            if (!name.isEmpty())
                break;
            System.out.println("Product name cannot be empty. Please try again.");
        }

        ProductType productType;
        while (true) {
            System.out.print("Product Type: ");
            String productTypeInput = sc.nextLine().trim().toUpperCase();
            try {
                productType = ProductType.valueOf(productTypeInput);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid product type. Please try again.");
            }
        }

        double caloriesPer100Grams = getInput("Calories per 100 grams");
        double proteinPer100Grams = getInput("Protein per 100 grams");
        double fatPer100Grams = getInput("Fat per 100 grams");
        double carbsPer100Grams = getInput("Carbs per 100 grams");

        ProductDTO productDTO = new ProductDTO(name, productType, caloriesPer100Grams, proteinPer100Grams, fatPer100Grams, carbsPer100Grams);

        productService.addNewProduct(productDTO);

        System.out.println("Product added successfully:");
    }

    public void searchProducts() {
        while (true) {
            System.out.print("Search Product (or type 'exit' to quit): ");
            String query = sc.nextLine().trim();

            if (query.equalsIgnoreCase("exit")) {
                System.out.println("Exiting search.");
                break;
            }

            List<ProductResponseDTO> products = productService.searchProducts(query);

            if (products.isEmpty()) {
                System.out.println("No products found matching the query: " + query);
            } else {
                System.out.println("Products found:");
                products.forEach(product -> {
                    System.out.println("Name: " + product.name() + ", ID: " + product.id());
                    System.out.println("---");
                });
            }
        }
    }

    private double getInput(String fieldName) {
        double value;
        while (true) {
            System.out.print("Enter " + fieldName + ": ");
            try {
                value = Double.parseDouble(sc.nextLine().trim());
                if (value >= 0) break;

                System.out.println(fieldName + " must be a non-negative number. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for " + fieldName + ".");
            }
        }
        return value;
    }

}