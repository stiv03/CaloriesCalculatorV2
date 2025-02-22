package client.command.user;

import client.UserSession;
import client.command.MenuCommand;
import controller.UserController;
import dto.UserDTO;
import view.UserView;

public class DisplayUserProfileCommand implements MenuCommand {
    private final UserController userController;
    private final UserView userView;

    public DisplayUserProfileCommand(UserController userController, UserView userView) {
        this.userController = userController;
        this.userView = userView;
    }

    @Override
    public void execute() {
        Long userId = UserSession.checkUserLoggedIn();
        if (userId != null) {
            UserDTO userDTO = userController.displayUserProfile(userId);
            if (userDTO != null) {
                userView.displayUserProfile(userDTO);
            } else {
                userView.displayMessage("User not found.");
            }
        }
    }
}