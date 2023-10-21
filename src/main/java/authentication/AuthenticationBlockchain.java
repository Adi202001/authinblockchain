package authentication;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationBlockchain {
    private List<User> users;

    public AuthenticationBlockchain() {
        this.users = new ArrayList<>();
    }

    public boolean registerUser(String username, String password) {
        // Check if the username is already registered
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists. Registration failed.");
                return false;
            }
        }

        // Create a new user and add it to the blockchain
        User newUser = new User(username, password);
        users.add(newUser);
        System.out.println("User registered successfully.");
        return true;
    }

    public User authenticateUser(String username, String password) {
        // Check if the provided username and password match any registered user
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user; // User authenticated
            }
        }
        return null; // Authentication failed
    }
}
