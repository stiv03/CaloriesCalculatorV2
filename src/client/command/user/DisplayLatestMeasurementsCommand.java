package client.command.user;

import client.UserSession;
import client.command.MenuCommand;
import controller.UserController;
import dto.MeasurementsRecordDTO;
import view.UserView;

public class DisplayLatestMeasurementsCommand implements MenuCommand {
    private final UserController userController;
    private final UserView userView;

    public DisplayLatestMeasurementsCommand(UserController userController, UserView userView) {
        this.userController = userController;
        this.userView = userView;
    }

    @Override
    public void execute() {
        Long userId =  UserSession.checkUserLoggedIn();
        if (userId != null) {
            MeasurementsRecordDTO latestMeasurement = userController.getLastUserMeasurement(userId);
            if (latestMeasurement != null) {
                userView.displayMeasurementRecord(latestMeasurement);
            } else {
                userView.displayMessage("No measurements found.");
            }
        } else {
            userView.displayMessage("No user is currently logged in.");
        }
    }
}