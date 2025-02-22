package client.command.user;

import client.UserSession;
import client.command.MenuCommand;
import controller.UserController;
import view.UserView;

public class UpdateHeightCommand implements MenuCommand {
    private final UserController userController;
    private final UserView userView;

    public UpdateHeightCommand(UserController userController, UserView userView) {
        this.userController = userController;
        this.userView = userView;
    }

    @Override
    public void execute() {
        Long userId = UserSession.checkUserLoggedIn();
        if (userId != null) {
            int newHeight = userView.getPositiveInt("Enter new height");
            userController.updateHeight(userId, newHeight);
            userView.displayMessage("Height updated successfully.");
        } else {
            userView.displayMessage("No user is currently logged in.");
        }
    }
}