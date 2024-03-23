package Hospital;

public class LoginValidator {
    public boolean validateCredentials(String username, char[] password) {
        // Placeholder validation logic
        return "admin".equals(username) && "password".equals(new String(password));
    }
}
