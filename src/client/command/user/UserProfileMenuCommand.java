package client.command.user;

import client.command.MenuCommand;
import client.MenuV2;

public class UserProfileMenuCommand implements MenuCommand {
    @Override
    public void execute() {
        MenuV2.userProfileMenu();
    }
}