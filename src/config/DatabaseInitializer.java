package config;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public final class DatabaseInitializer {

    //todo: It might be saved in .sql file and read from it

    private DatabaseInitializer() {
    }

    private void executeUpdate(String query) {
        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Executed: " + query);
        } catch (SQLException e) {
            System.err.println("Error executing query: " + e.getMessage());
        }
    }

    private void createUsersTable() {
        String query = "CREATE TABLE IF NOT EXISTS users (" +
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
    }

    private void createProductsTable() {
        String query = "CREATE TABLE IF NOT EXISTS products (" +
                "id SERIAL PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "product_type VARCHAR(50) NOT NULL, " +
                "calories_per_100_grams DECIMAL(10, 2) NOT NULL, " +
                "protein_per_100_grams DECIMAL(10, 2) NOT NULL, " +
                "fat_per_100_grams DECIMAL(10, 2) NOT NULL, " +
                "carbs_per_100_grams DECIMAL(10, 2) NOT NULL" +
                ");";
        executeUpdate(query);
    }

    private void createMealsTable() {
        String query = "CREATE TABLE IF NOT EXISTS meals (" +
                "id SERIAL PRIMARY KEY, " +
                "user_id INTEGER NOT NULL, " +
                "product_id INTEGER NOT NULL, " +
                "quantity DECIMAL(10, 2) NOT NULL, " +
                "consumed_at DATE NOT NULL, " +
                "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE, " +
                "FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE" +
                ");";
        executeUpdate(query);
    }

    private void createWeightRecordsTable() {
        String query = "CREATE TABLE IF NOT EXISTS weight_records (" +
                "id SERIAL PRIMARY KEY, " +
                "user_id INTEGER NOT NULL, " +
                "weight DECIMAL(5, 2) NOT NULL, " +
                "date DATE NOT NULL, " +
                "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE" +
                ");";
        executeUpdate(query);
    }

    private void createMeasurementsRecordsTable() {
        String query = "CREATE TABLE IF NOT EXISTS measurements_records (" +
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
    }

    public void initializeDatabase() {
        createUsersTable();
        createProductsTable();
        createMealsTable();
        createWeightRecordsTable();
        createMeasurementsRecordsTable();
    }
}
