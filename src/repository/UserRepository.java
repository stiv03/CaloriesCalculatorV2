package repository;

import config.DatabaseConfig;
import entity.Users;
import entity.enums.UserType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    private Connection getConnection() throws SQLException {
        return DatabaseConfig.getConnection();
    }

    public void registerUser(Users users) {
        String query = "INSERT INTO users (name, age, weight, height, username, password, userType) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, users.getName());
            statement.setInt(2, users.getAge());
            statement.setDouble(3, users.getWeight());
            statement.setInt(4, users.getHeight());
            statement.setString(5, users.getUsername());
            statement.setString(6, users.getPassword());
            statement.setString(7, users.getUserType().toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error creating new user");
        }
    }

    public Users getUserById(Long id) {
        String query = "SELECT id, name, age, weight, height, userType FROM users WHERE id = ?";
        Users user = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String userTypeString = resultSet.getString("userType");
                    UserType userType = UserType.valueOf(userTypeString);
                    user = new Users(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getDouble("weight"),
                            resultSet.getInt("height"),

                            userType
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("User not found");
        }

        return user;
    }

    public Long login(String username, String password) {
        String query = "SELECT id FROM users WHERE username = ? AND password = ?";
        Long userId = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    userId = resultSet.getLong("id");
                }
            }
        } catch (SQLException e) {
            System.out.println("User not found");
        }

        return userId;
    }

    public void updateUserWeight(Long userId, Double newWeight) {
        String query = "UPDATE users SET weight = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setDouble(1, newWeight);
            statement.setLong(2, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating weight ");
        }
    }

    public void updateUserHeight(Long userId, int newHeight) {
        String query = "UPDATE users SET height = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, newHeight);
            statement.setLong(2, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating height ");
        }
    }

    public void updateUserAge(Long userId, int newAge) {
        String query = "UPDATE users SET age = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, newAge);
            statement.setLong(2, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating age");
        }
    }

    public void deleteByUserID(long userId) {
        String query = "DELETE FROM users WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, userId);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User with ID " + userId + " deleted successfully.");
            } else {
                System.out.println("No user found with ID " + userId);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }
}
