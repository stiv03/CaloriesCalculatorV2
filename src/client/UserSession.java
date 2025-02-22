package client;


import view.UserView;

public final class UserSession {
    private static final UserView userView = new UserView();

    private UserSession() {
    }

    private static Long loggedInUserId;

    public static void setLoggedInUserId(Long userId) {
        loggedInUserId = userId;
    }

    public static Long getLoggedInUserId() {
        return loggedInUserId;
    }

    public static void logout() {
        loggedInUserId = null;
    }

    public static Long checkUserLoggedIn() {
        Long userId = UserSession.getLoggedInUserId();
        if (userId == null) {
            userView.displayMessage("No user is currently logged in.");
        }
        return userId;
    }
}


