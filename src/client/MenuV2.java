package client;


import client.command.MenuCommand;
import client.command.MenuCommands;
import view.UserView;
import java.util.Map;

public final class MenuV2 {
    private static final UserView userView = new UserView();

    private MenuV2() {}

    public static void welcomeMenu() {
        executeMenu("Welcome Menu", MenuCommands.welcomeCommands);
    }

    public static void mainMenu() {
        executeMenu("Main Menu", MenuCommands.mainCommands);
    }
    
    public static void userProfileMenu() {
        executeMenu("User Profile Menu", MenuCommands.userProfileCommands);
    }

    public static void mealMenu() {
        executeMenu("Meal Menu", MenuCommands.mealCommands);
    }

    public static void productMenu() {
        executeMenu("Product Menu", MenuCommands.productCommands);
    }

    private static String formatCommandName(String className)  {
        return className
                .replaceAll("([a-z])([A-Z])", "$1 $2") 
                .replaceAll("Command$", ""); 
    }

    private static void executeMenu(String menuTitle, Map<String, MenuCommand> commands) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n" + menuTitle + ":");
            commands.forEach((key, command) -> System.out.println(key + ". " + formatCommandName(command.getClass().getSimpleName())));
            System.out.println("0. Exit");
            String choice = userView.getUserInput("Enter your choice");

            if ("0".equals(choice)) {
                exit = true;
                continue;
            }

            MenuCommand command = commands.get(choice);
            if (command != null) {
                command.execute();
            } else {
                userView.displayMessage("Invalid choice. Please try again.");
            }
        }
    }
}