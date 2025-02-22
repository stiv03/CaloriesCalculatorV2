package client.command.meal;

import client.UserSession;
import client.command.MenuCommand;
import controller.MealController;
import dto.DailyMacrosDTO;
import view.MealView;
import java.util.List;

public class ViewAllMacrosCommand implements MenuCommand {
    private final MealController mealController;
    private final MealView mealView;

    public ViewAllMacrosCommand(MealController mealController, MealView mealView) {
        this.mealController = mealController;
        this.mealView = mealView;
    }

    @Override
    public void execute() {
        Long userId = UserSession.checkUserLoggedIn();
        if (userId != null) {
            List<DailyMacrosDTO> allMacros = mealController.fetchAllMacros(userId);
            if (allMacros != null && !allMacros.isEmpty()) {
                mealView.displayAllMacros(allMacros);
            } else {
                mealView.displayMessage("No macros data found.");
            }
        } else {
            mealView.displayMessage("No user is currently logged in.");
        }
    }
}