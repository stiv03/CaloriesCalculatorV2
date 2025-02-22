package client.command.user;

import client.UserSession;
import client.command.MenuCommand;
import controller.UserController;
import dto.UpdateUserMeasurementsRequestDTO;
import view.UserView;

public class AddMeasurementCommand implements MenuCommand {
    private final UserController userController;
    private final UserView userView;

    public AddMeasurementCommand(UserController userController, UserView userView) {
        this.userController = userController;
        this.userView = userView;
    }

    @Override
    public void execute() {
        Long userId = UserSession.checkUserLoggedIn();
        if (userId != null) {
            UpdateUserMeasurementsRequestDTO measurements = userView.getMeasurementInput();
            userController.addMeasurement(userId, measurements);
            userView.displayMessage("Measurement added successfully.");
        } else {
            userView.displayMessage("No user is currently logged in.");
        }
    }
}