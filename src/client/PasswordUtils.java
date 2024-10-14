package client;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public final class PasswordUtils {

    private PasswordUtils() {
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();

            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error while hashing the password", e);
        }
    }


}
