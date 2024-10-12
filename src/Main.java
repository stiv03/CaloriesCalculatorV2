import Client.Menu;
import Client.UserSession;
import config.DatabaseInitializer;
import controller.UserController;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    private static final UserController userController = new UserController();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        DatabaseInitializer dbInitializer = new DatabaseInitializer();
        dbInitializer.initializeDatabaseFromFile();

        Menu.welcomeMenu();
    }
}

