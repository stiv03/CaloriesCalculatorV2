package client.command.welcome;

import client.command.MenuCommand;
import controller.UserController;
import dto.RegisterDTO;
import view.UserView;

public class RegisterCommand implements MenuCommand {
    private final UserController userController;
    private final UserView userView;

    public RegisterCommand(UserController userController, UserView userView) {
        this.userController = userController;
        this.userView = userView;
    }

    @Override
    public void execute() {
        RegisterDTO registerDTO = userView.getRegistrationInput();
        userController.registerUser(registerDTO);
        userView.displayMessage("User registered successfully!");
    }
}