package client.command.meal;

import client.UserSession;
import client.command.MenuCommand;
import controller.MealController;
import dto.MealResponseDTO;
import view.MealView;
import java.time.LocalDate;
import java.util.List;

public class ViewMealsForTodayCommand implements MenuCommand {
    private final MealController mealController;
    private final MealView mealView;

    public ViewMealsForTodayCommand(MealController mealController, MealView mealView) {
        this.mealController = mealController;
        this.mealView = mealView;
    }

    @Override
    public void execute() {
        Long userId = UserSession.checkUserLoggedIn();
        if (userId != null) {
            LocalDate today = LocalDate.now();
            List<MealResponseDTO> meals = mealController.findAllUserMealsForSpecificDay(userId, today);
            if (meals != null && !meals.isEmpty()) {
                mealView.displayMeals(meals);
            } else {
                mealView.displayMessage("No meals found for today.");
            }
        } else {
            mealView.displayMessage("No user is currently logged in.");
        }
    }
}