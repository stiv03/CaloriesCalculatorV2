package client;

public final class UserSession {

    private UserSession() {
    }

    private static Long loggedInUserId;

    public static void setLoggedInUserId(Long userId) {
        loggedInUserId = userId;
    }

    public static Long getLoggedInUserId() {
        return loggedInUserId;
    }

    public static boolean isLoggedIn() {
        return loggedInUserId != null;
    }

    public static void logout() {
        loggedInUserId = null;
    }

}
