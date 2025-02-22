package client.command.user;

import client.UserSession;
import client.command.MenuCommand;
import controller.UserController;
import view.UserView;

public class UpdateWeightCommand implements MenuCommand {
    private final UserController userController;
    private final UserView userView;

    public UpdateWeightCommand(UserController userController, UserView userView) {
        this.userController = userController;
        this.userView = userView;
    }

    @Override
    public void execute() {
        Long userId = UserSession.checkUserLoggedIn();
        if (userId != null) {
            double newWeight = userView.getPositiveDouble("Enter new weight");
            userController.updateWeight(userId, newWeight);
            userView.displayMessage("Weight updated successfully.");
        } else {
            userView.displayMessage("No user is currently logged in.");
        }
    }
}