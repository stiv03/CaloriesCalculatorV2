package client.command.welcome;

import client.command.MenuCommand;
import client.MenuV2;
import controller.UserController;
import view.UserView;

public class LoginCommand implements MenuCommand {
    private final UserController userController;
    private final UserView userView;

    public LoginCommand(UserController userController, UserView userView) {
        this.userController = userController;
        this.userView = userView;
    }

    @Override
    public void execute() {
        String username = userView.getUsernameInput();
        String password = userView.getPasswordInput();
        Long loggedInUserId = userController.login(username, password);

        if (loggedInUserId != null) {
            userView.displayLoginSuccess(loggedInUserId);
            MenuV2.mainMenu();


        } else {
            userView.displayLoginFailure();
        }
    }
}
