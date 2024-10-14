import client.Menu;
import config.DatabaseInitializer;

public class Main {
    public static void main(String[] args) {
        DatabaseInitializer dbInitializer = new DatabaseInitializer();
        dbInitializer.initializeDatabaseFromFile();

        Menu.welcomeMenu();
    }
}

