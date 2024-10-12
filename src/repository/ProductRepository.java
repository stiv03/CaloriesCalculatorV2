package repository;

import config.DatabaseConfig;
import entity.Product;
import entity.enums.ProductType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private Connection getConnection() throws SQLException {
        return DatabaseConfig.getConnection();
    }



    public void addNewProduct(Product product) {
        String query = "INSERT INTO products (name, product_type, calories_per_100_grams, protein_per_100_grams," +
                                             " fat_per_100_grams, carbs_per_100_grams) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, product.getName());
            statement.setString(2, product.getProductType().toString());
            statement.setDouble(3, product.getCaloriesPer100Grams());
            statement.setDouble(4, product.getProteinPer100Grams());
            statement.setDouble(5, product.getFatPer100Grams());
            statement.setDouble(6, product.getCaloriesPer100Grams());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error creating new product");
        }
    }

    public Product getProductById(Long id) {
        String query = "SELECT id, name, product_type, calories_per_100_grams, protein_per_100_grams, " +
                "fat_per_100_grams, carbs_per_100_grams FROM products WHERE id = ?";
        Product product = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    product = new Product(
                            resultSet.getLong("id"),
                    resultSet.getString("name"),
                    ProductType.valueOf(resultSet.getString("product_type")),
                    resultSet.getDouble("calories_per_100_grams"),
                    resultSet.getDouble("protein_per_100_grams"),
                    resultSet.getDouble("fat_per_100_grams"),
                    resultSet.getDouble("carbs_per_100_grams"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving product: " + e.getMessage());

        }

        return product;
    }


    public List<Product> findByNameContainingIgnoreCase(String name) {
        String query = "SELECT id, name, product_type, calories_per_100_grams, protein_per_100_grams, " +
                "fat_per_100_grams, carbs_per_100_grams " +
                "FROM products " +
                "WHERE LOWER(name) LIKE LOWER(?)";

        List<Product> products = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, "%" + name + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setId(resultSet.getLong("id"));
                    product.setName(resultSet.getString("name"));
                    product.setProductType(ProductType.valueOf(resultSet.getString("product_type")));
                    product.setCaloriesPer100Grams(resultSet.getDouble("calories_per_100_grams"));
                    product.setProteinPer100Grams(resultSet.getDouble("protein_per_100_grams"));
                    product.setFatPer100Grams(resultSet.getDouble("fat_per_100_grams"));
                    product.setCarbsPer100Grams(resultSet.getDouble("carbs_per_100_grams"));
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error searching for products: " + e.getMessage());
        }

        return products;
    }

}
