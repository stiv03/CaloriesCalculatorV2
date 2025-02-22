package client.command.user;

import client.UserSession;
import client.command.MenuCommand;
import controller.UserController;
import view.UserView;

public class UpdateAgeCommand implements MenuCommand {
    private final UserController userController;
    private final UserView userView;

    public UpdateAgeCommand(UserController userController, UserView userView) {
        this.userController = userController;
        this.userView = userView;
    }

    @Override
    public void execute() {
        Long userId = UserSession.checkUserLoggedIn();
        if (userId != null) {
            int newAge = userView.getPositiveInt("Enter new age");
            userController.updateAge(userId, newAge);
            userView.displayMessage("Age updated successfully.");
        } else {
            userView.displayMessage("No user is currently logged in.");
        }
    }
}