package config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class DatabaseConfig {
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    private DatabaseConfig() {
    }

    static {
        try (InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream("DB.properties")) {
            Properties prop = new Properties();
            if (input != null) {
                prop.load(input);
                URL = prop.getProperty("db.url");
                USER = prop.getProperty("db.user");
                PASSWORD = prop.getProperty("db.password");
            } else {
                System.out.println("Error: Configuration file 'DB.properties' not found.");
            }
        } catch (IOException e) {
            System.out.println("Error loading database configuration.");
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
