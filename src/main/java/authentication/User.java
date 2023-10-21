package authentication;

public class User {
    private String username;
    private String passwordHash; // Store the password hash securely
    private String authToken; // Unique authentication token

    public User(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", authToken='" + authToken + '\'' +
                '}';
    }
}
