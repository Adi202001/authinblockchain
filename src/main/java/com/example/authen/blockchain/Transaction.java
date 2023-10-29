package com.example.authen.blockchain;

import java.security.*;
import java.util.Base64;

public class Transaction {
    private String sender; // Sender's public key (wallet address)
    private String recipient; // Recipient's public key (wallet address)
    private double amount;
    private String transactionId; // Transaction hash

    private static int sequence = 0;

    public Transaction(String sender, String recipient, double amount) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        this.transactionId = calculateHash();
    }

    public String calculateHash() {
        sequence++; // Increase the sequence to avoid two identical transactions having the same hash
        String data = sender + recipient + amount + sequence;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Sign the transaction with the sender's private key
    public byte[] generateSignature(PrivateKey privateKey) {
        String data = sender + recipient + amount;
        return applyECDSASig(privateKey, data);
    }

    // Verify the transaction's signature
    public boolean verifySignature(PublicKey publicKey, byte[] signature) {
        String data = sender + recipient + amount;
        return verifyECDSASig(publicKey, data, signature);
    }

    private static byte[] applyECDSASig(PrivateKey privateKey, String data) {
        try {
            Signature dsa = Signature.getInstance("ECDSA", "BC");
            dsa.initSign(privateKey);
            byte[] strByte = data.getBytes();
            dsa.update(strByte);
            return dsa.sign();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean verifyECDSASig(PublicKey publicKey, String data, byte[] signature) {
        try {
            Signature ecdsaVerify = Signature.getInstance("ECDSA", "BC");
            ecdsaVerify.initVerify(publicKey);
            ecdsaVerify.update(data.getBytes());
            return ecdsaVerify.verify(signature);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Getters
    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionId() {
        return transactionId;
    }
}

