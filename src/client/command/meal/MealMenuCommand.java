package client.command.meal;

import client.command.MenuCommand;
import client.MenuV2;

public class MealMenuCommand implements MenuCommand {
    @Override
    public void execute() {
        MenuV2.mealMenu();
    }
}