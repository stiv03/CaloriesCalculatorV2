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
            System.out.println("Table created");
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

    private void createProductsTable() {
        if (!tableExists("products")) {
            String query = "CREATE TABLE products (" +
                    "id SERIAL PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "product_type VARCHAR(50) NOT NULL, " +
                    "calories_per_100_grams DECIMAL(10, 2) NOT NULL, " +
                    "protein_per_100_grams DECIMAL(10, 2) NOT NULL, " +
                    "fat_per_100_grams DECIMAL(10, 2) NOT NULL, " +
                    "carbs_per_100_grams DECIMAL(10, 2) NOT NULL" +
                    ");";
            executeUpdate(query);
        } else {
            System.out.println("Table 'products' already exists. Skipping creation.");
        }
    }

    private void createMealsTable() {
        if (!tableExists("meals")) {
            String query = "CREATE TABLE meals (" +
                    "id SERIAL PRIMARY KEY, " +
                    "user_id INTEGER NOT NULL, " +
                    "product_id INTEGER NOT NULL, " +
                    "quantity DECIMAL(10, 2) NOT NULL, " +
                    "consumed_at DATE NOT NULL, " +
                    "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE, " +
                    "FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE" +
                    ");";
            executeUpdate(query);
        } else {
            System.out.println("Table 'meals' already exists. Skipping creation.");
        }
    }
    private void createWeightRecordsTable() {
        if (!tableExists("weight_records")) {
            String query = "CREATE TABLE weight_records (" +
                    "id SERIAL PRIMARY KEY, " +
                    "user_id INTEGER NOT NULL, " +
                    "weight DECIMAL(5, 2) NOT NULL, " +
                    "date DATE NOT NULL, " +
                    "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE" +
                    ");";
            executeUpdate(query);
        } else {
            System.out.println("Table 'weight_records' already exists. Skipping creation.");
        }
    }
    private void createMeasurementsRecordsTable() {
        if (!tableExists("measurements_records")) {
            String query = "CREATE TABLE measurements_records (" +
                    "id SERIAL PRIMARY KEY, " +
                    "user_id INTEGER NOT NULL, " +
                    "shoulder DECIMAL(5, 2) NOT NULL, " +
                    "chest DECIMAL(5, 2) NOT NULL, " +
                    "biceps DECIMAL(5, 2) NOT NULL, " +
                    "waist DECIMAL(5, 2) NOT NULL, " +
                    "hips DECIMAL(5, 2) NOT NULL, " +
                    "thigh DECIMAL(5, 2) NOT NULL, " +
                    "calf DECIMAL(5, 2) NOT NULL, " +
                    "date DATE NOT NULL, " +
                    "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE" +
                    ");";
            executeUpdate(query);
        } else {
            System.out.println("Table 'measurements_records' already exists. Skipping creation.");
        }
    }

    public void initializeDatabase() {
        createUsersTable();
        createProductsTable();
        createMealsTable();
        createWeightRecordsTable();
        createMeasurementsRecordsTable();

    }

}
