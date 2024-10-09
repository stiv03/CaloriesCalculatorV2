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
        String query = "SELECT name, age, weight, height, userType FROM users WHERE id = ?";
        Users user = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new Users(
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getDouble("weight"),
                            resultSet.getInt("height"),
                            (UserType) resultSet.getObject("userType")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("User not found");
        }

        return user;
    }

    public Users login(String username, String password) {
        String query = "SELECT id, name, age, weight, height, userType FROM users WHERE username = ? AND password = ?";
        Users user = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {

                    Long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    double weight = resultSet.getDouble("weight");
                    int height = resultSet.getInt("height");
                    UserType userType = (UserType) resultSet.getObject("userType");

                    user = new Users(id, name, age, weight, height, userType);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error login ");

        }

        return user;
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
}
