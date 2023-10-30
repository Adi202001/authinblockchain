package com.example.authen.authentication;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Base64;
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
        SecureRandom secureRandom = new SecureRandom();
        byte[] saltBytes = new byte[16]; // 128 bits
        secureRandom.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
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
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048); // Adjust the key size as needed
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();

            // Encode the public key as a Base64 string
            return Base64.getEncoder().encodeToString(publicKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating public key", e);
        }
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

