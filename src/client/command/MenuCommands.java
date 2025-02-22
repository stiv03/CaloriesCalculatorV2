package client.command;


import client.command.meal.*;
import client.command.product.AddProductCommand;
import client.command.product.ProductMenuCommand;
import client.command.product.SearchProductCommand;
import client.command.user.*;
import client.command.welcome.LoginCommand;
import client.command.welcome.RegisterCommand;
import controller.UserController;
import controller.MealController;
import controller.ProductController;
import view.UserView;
import view.MealView;
import view.ProductView;
import java.util.HashMap;
import java.util.Map;

public final class MenuCommands {
    private static final UserController userController = new UserController();
    private static final MealController mealController = new MealController();
    private static final ProductController productController = new ProductController();
    private static final UserView userView = new UserView();
    private static final MealView mealView = new MealView();
    private static final ProductView productView = new ProductView();

    public static final Map<String, MenuCommand> welcomeCommands = new HashMap<>();
    public static final Map<String, MenuCommand> mainCommands = new HashMap<>();
    public static final Map<String, MenuCommand> userProfileCommands = new HashMap<>();
    public static final Map<String, MenuCommand> mealCommands = new HashMap<>();
    public static final Map<String, MenuCommand> productCommands = new HashMap<>();

    private MenuCommands() {}

    static {
        welcomeCommands.put("1", new RegisterCommand(userController, userView));
        welcomeCommands.put("2", new LoginCommand(userController, userView));


        mainCommands.put("1", new UserProfileMenuCommand());
        mainCommands.put("2", new MealMenuCommand());
        mainCommands.put("3", new ProductMenuCommand());

        userProfileCommands.put("1", new DisplayUserProfileCommand(userController, userView));
        userProfileCommands.put("2", new UpdateHeightCommand(userController, userView));
        userProfileCommands.put("3", new UpdateAgeCommand(userController, userView));
        userProfileCommands.put("4", new UpdateWeightCommand(userController, userView));
        userProfileCommands.put("5", new DisplayWeightRecordsCommand(userController, userView));
        userProfileCommands.put("6", new AddMeasurementCommand(userController, userView));
        userProfileCommands.put("7", new DisplayLatestMeasurementsCommand(userController, userView));
        userProfileCommands.put("8", new DisplayAllMeasurementsCommand(userController, userView));

        mealCommands.put("1", new AddMealCommand(mealController, mealView));
        mealCommands.put("2", new SearchProductCommand(productController, productView));
        mealCommands.put("3", new ViewMealsForTodayCommand(mealController, mealView));
        mealCommands.put("4", new UpdateMealQuantityCommand(mealController, mealView));
        mealCommands.put("5", new DeleteMealCommand(mealController, mealView));
        mealCommands.put("6", new ViewTodaysMacrosCommand(mealController, mealView));
        mealCommands.put("7", new ViewAllMacrosCommand(mealController, mealView));

        productCommands.put("1", new AddProductCommand(productController, productView));
    }
}
