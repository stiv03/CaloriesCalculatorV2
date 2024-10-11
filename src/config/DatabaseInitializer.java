package config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public final class DatabaseInitializer {

    private DatabaseInitializer() {
    }

    private static final String FILE_PATH = "calories_calculator_DB.sql";

    private void executeSQLFromFile() {
        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement()) {

            String sql = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            String[] queries = sql.split(";");

            for (String query : queries) {
                if (!query.trim().isEmpty()) {
                    statement.executeUpdate(query.trim() + ";");
                    System.out.println("Executed: " + query.trim());
                }
            }

        } catch (SQLException e) {
            System.err.println("Error executing SQL from file: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading SQL file: " + e.getMessage());
        }
    }

    public void initializeDatabaseFromFile() {
        executeSQLFromFile();
    }
}
