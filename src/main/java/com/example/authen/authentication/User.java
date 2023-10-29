package com.example.authen.authentication;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

public class User {
    private String username;
    private String passwordHash; // Hashed password for security
    private String salt; // Random salt for password hashing
    private String publicKey; // Public key for blockchain interactions

    public User(String username, String password) {
        this.username = username;
        this.salt = generateSalt();
        this.passwordHash = hashPassword(password, salt);
        this.publicKey = generatePublicKey();
    }

    private String generateSalt() {
        // Implement a secure method to generate a random salt
        // For example, using a cryptographic library like Java's SecureRandom
        // This is a simplified example for educational purposes
        // Do not use this in a real system!
        return "random_salt";
    }

    private String hashPassword(String password, String salt) {
        try {
            String dataToHash = password + salt;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String generatePublicKey() {
        // Implement a method to generate a public key for blockchain interactions
        // This typically involves asymmetric key pair generation
        // Using a library like Java's KeyPairGenerator
        // This is a simplified example for educational purposes
        // Do not use this in a real system!
        return "public_key";
    }

    // Getters for user information
    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    public String getPublicKey() {
        return publicKey;
    }

    // Check if the provided password matches the user's password
    public boolean checkPassword(String password) {
        String hashedPassword = hashPassword(password, salt);
        return passwordHash.equals(hashedPassword);
    }
}

