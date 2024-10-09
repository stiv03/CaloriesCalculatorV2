package config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public final class DatabaseInitializer {

    private DatabaseInitializer() {
    }

    private void executeUpdate(String query) {
        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Table created or already exists.");
        } catch (SQLException e) {
            System.err.println("Error executing table creation: " + e.getMessage());
        }
    }

    private boolean tableExists(String tableName) {
        String query = "SELECT EXISTS (" +
                "SELECT FROM information_schema.tables " +
                "WHERE table_schema = 'public' " +
                "AND table_name = '" + tableName + "');";
        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return resultSet.getBoolean(1);
            }
        } catch (SQLException e) {
            System.err.println("Error checking table existence: " + e.getMessage());
        }
        return false;
    }
    private void createUsersTable() {
        if (!tableExists("users")) {
            String query = "CREATE TABLE users (" +
                    "id SERIAL PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "age INTEGER NOT NULL, " +
                    "weight DECIMAL(5, 2) NOT NULL, " +
                    "height INTEGER NOT NULL, " +
                    "username VARCHAR(50) NOT NULL UNIQUE, " +
                    "password VARCHAR(255) NOT NULL, " +
                    "userType VARCHAR(50) NOT NULL" +
                    ");";
            executeUpdate(query);
        } else {
            System.out.println("Table 'users' already exists. Skipping creation.");
        }
    }
    public void initializeDatabase() {
        createUsersTable();
    }

}
