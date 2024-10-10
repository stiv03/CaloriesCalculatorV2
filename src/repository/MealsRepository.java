package repository;

import config.DatabaseConfig;
import entity.Meal;
import entity.Product;
import entity.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MealsRepository {

    private final ProductRepository productRepository = new ProductRepository();
    private final UserRepository userRepository = new UserRepository();

    private Connection getConnection() throws SQLException {
        return DatabaseConfig.getConnection();
    }

    public void addNewMeal(Meal meal) {
        String query = "INSERT INTO meals (user_id, product_id, quantity, consumed_at) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, meal.getUser().getId());
            statement.setLong(2, meal.getProduct().getId());
            statement.setDouble(3, meal.getQuantity());
            statement.setDate(4, java.sql.Date.valueOf(meal.getConsumedAt()));

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error creating new meal: " + e.getMessage());
        }
    }

    public List<Meal> findAllMealsByUserId(Long userId) {
        String query = "SELECT id, product_id, quantity, consumed_at FROM meals WHERE user_id = ?";
        List<Meal> meals = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Meal meal = new Meal();
                    meal.setId(resultSet.getLong("id"));

                    Product product = productRepository.getProductById(resultSet.getLong("product_id"));
                    meal.setProduct(product);

                    meal.setQuantity(resultSet.getDouble("quantity"));
                    meal.setConsumedAt(resultSet.getDate("consumed_at").toLocalDate());

                    meals.add(meal);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving meals for user ID: " + userId + " - " + e.getMessage());
        }

        return meals;
    }

    public Meal getMealById(Long id) {
        String query = "SELECT user_id, product_id, quantity, consumed_at FROM meals WHERE id = ?";
        Meal meal = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    meal = new Meal();


                    Long userId = resultSet.getLong("user_id");
                    Users user = userRepository.getUserById(userId);
                    meal.setUser(user);

                    Long productId = resultSet.getLong("product_id");
                    Product product = productRepository.getProductById(productId);
                    meal.setProduct(product);

                    meal.setQuantity(resultSet.getDouble("quantity"));
                    meal.setConsumedAt(resultSet.getDate("consumed_at").toLocalDate());
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving meal: " + e.getMessage());
        }

        return meal;
    }

    public void updateMealQuantity(Long mealId, Double newQuantity) {
        String query = "UPDATE meals SET quantity = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setDouble(1, newQuantity);
            statement.setLong(2, mealId);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating quantity ");
        }
    }

    public void deleteMeal(long mealId) {
        String query = "DELETE FROM meals WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, mealId);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Meal with ID " + mealId + " deleted successfully.");
            } else {
                System.out.println("No meal found with ID " + mealId);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting meal: " + e.getMessage());
        }
    }




}
