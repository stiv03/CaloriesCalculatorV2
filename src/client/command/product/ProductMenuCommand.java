package client.command.product;

import client.command.MenuCommand;
import client.MenuV2;

public class ProductMenuCommand implements MenuCommand {
    @Override
    public void execute() {
        MenuV2.productMenu();
    }
}