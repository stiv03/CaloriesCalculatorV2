package client.command.user;

import client.UserSession;
import client.command.MenuCommand;
import controller.UserController;
import dto.MeasurementsRecordDTO;
import view.UserView;
import java.util.List;

public class DisplayAllMeasurementsCommand implements MenuCommand {
    private final UserController userController;
    private final UserView userView;

    public DisplayAllMeasurementsCommand(UserController userController, UserView userView) {
        this.userController = userController;
        this.userView = userView;
    }

    @Override
    public void execute() {
        Long userId = UserSession.checkUserLoggedIn();
        if (userId != null) {
            List<MeasurementsRecordDTO> allMeasurements = userController.getAllUserMeasurements(userId);
            if (allMeasurements != null && !allMeasurements.isEmpty()) {
                userView.displayAllMeasurementRecords(allMeasurements);
            } else {
                userView.displayMessage("No measurements found.");
            }
        } else {
            userView.displayMessage("No user is currently logged in.");
        }
    }
}