package client.command.user;

import client.UserSession;
import client.command.MenuCommand;
import controller.UserController;
import dto.WeightRecordDTO;
import view.UserView;
import java.util.List;

public class DisplayWeightRecordsCommand implements MenuCommand {
    private final UserController userController;
    private final UserView userView;

    public DisplayWeightRecordsCommand(UserController userController, UserView userView) {
        this.userController = userController;
        this.userView = userView;
    }

    @Override
    public void execute() {
        Long userId = UserSession.checkUserLoggedIn();
        if (userId != null) {
            List<WeightRecordDTO> weightRecords = userController.getWeightRecords(userId);
            if (weightRecords != null && !weightRecords.isEmpty()) {
                userView.displayWeightRecords(weightRecords);
            } else {
                userView.displayMessage("No weight records found.");
            }
        } else {
            userView.displayMessage("No user is currently logged in.");
        }
    }
}