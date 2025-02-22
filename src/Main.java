import client.MenuV2;
import config.DatabaseInitializer;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DatabaseInitializer dbInitializer = new DatabaseInitializer();
        try {
            dbInitializer.initializeDatabaseFromFile();
            System.out.println("Database initialized successfully.");

            MenuV2.welcomeMenu();

        } catch (SQLException | IOException e) {
            System.err.println("Failed to initialize the database. Program will exit.");
            System.exit(1);
        }
    }
}

