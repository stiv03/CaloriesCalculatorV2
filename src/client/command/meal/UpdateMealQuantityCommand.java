package client.command.meal;

import client.command.MenuCommand;
import controller.MealController;
import view.MealView;

public class UpdateMealQuantityCommand implements MenuCommand {
    private final MealController mealController;
    private final MealView mealView;

    public UpdateMealQuantityCommand(MealController mealController, MealView mealView) {
        this.mealController = mealController;
        this.mealView = mealView;
    }

    @Override
    public void execute() {
        long mealId = mealView.getLongInput("Enter Meal ID");
        double newQuantity = mealView.getPositiveDouble("Enter new quantity (grams)");
        mealController.updateMealQuantity(mealId, newQuantity);
        mealView.displayMessage("Meal quantity updated successfully.");
    }
}