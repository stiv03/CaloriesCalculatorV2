package client.command.meal;

import client.UserSession;
import client.command.MenuCommand;
import controller.MealController;
import dto.DailyMacrosDTO;
import view.MealView;
import java.time.LocalDate;

public class ViewTodaysMacrosCommand implements MenuCommand {
    private final MealController mealController;
    private final MealView mealView;

    public ViewTodaysMacrosCommand(MealController mealController, MealView mealView) {
        this.mealController = mealController;
        this.mealView = mealView;
    }

    @Override
    public void execute() {
        Long userId =  UserSession.checkUserLoggedIn();
        if (userId != null) {
            LocalDate today = LocalDate.now();
            DailyMacrosDTO dailyMacros = mealController.calculateAndDisplayDailyMacros(userId, today);
            if (dailyMacros != null) {
                mealView.displayDailyMacros(dailyMacros);
            } else {
                mealView.displayMessage("No macros data found for today.");
            }
        } else {
            mealView.displayMessage("No user is currently logged in.");
        }
    }
}