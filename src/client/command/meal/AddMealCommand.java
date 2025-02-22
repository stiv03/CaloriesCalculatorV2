package client.command.meal;

import client.UserSession;
import client.command.MenuCommand;
import controller.MealController;
import dto.MealDTO;
import view.MealView;

public class AddMealCommand implements MenuCommand {
    private final MealController mealController;
    private final MealView mealView;

    public AddMealCommand(MealController mealController, MealView mealView) {
        this.mealController = mealController;
        this.mealView = mealView;
    }

    @Override
    public void execute() {
        Long userId = UserSession.checkUserLoggedIn();
        if (userId != null) {
            MealDTO mealDTO = mealView.getMealInput();
            mealController.addMealForUser(userId, mealDTO.productId(), mealDTO.grams());
            mealView.displayMessage("Meal added successfully.");
        } else {
            mealView.displayMessage("No user is currently logged in.");
        }
    }
}