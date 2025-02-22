package client.command.meal;

import client.command.MenuCommand;
import controller.MealController;
import view.MealView;

public class DeleteMealCommand implements MenuCommand {
    private final MealController mealController;
    private final MealView mealView;

    public DeleteMealCommand(MealController mealController, MealView mealView) {
        this.mealController = mealController;
        this.mealView = mealView;
    }

    @Override
    public void execute() {
        long mealId = mealView.getLongInput("Enter Meal ID to delete");
        mealController.deleteMeal(mealId);
        mealView.displayMessage("Meal deleted successfully.");
    }
}