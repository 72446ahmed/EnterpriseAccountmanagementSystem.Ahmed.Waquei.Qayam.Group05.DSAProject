package com.banking.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String transactionId;
    private String senderId;
    private String recipientId;
    private double amount;
    private int priorityScore; // Used for Max-Heap Priority Queue ranking
    private String status;     // "PENDING", "COMPLETE", "ROLLED_BACK"
    private String timestamp;  // Transaction ki exact Date & Time

    // Constructor
    public Transaction(String transactionId, String senderId, String recipientId, double amount, int priorityScore) {
        this.transactionId = transactionId;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.amount = amount;
        this.priorityScore = priorityScore;
        this.status = "PENDING";
        
        // Automatic current date aur time generate ho jayega
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    // Getters and Setters
    public String getTransactionId() {
        return transactionId;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public double getAmount() {
        return amount;
    }

    public int getPriorityScore() {
        return priorityScore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Transaction [ID=" + transactionId + ", From=" + senderId + ", To=" + recipientId + 
               ", Amount=" + amount + ", Priority=" + priorityScore + ", Status=" + status + ", Time=" + timestamp + "]";
    }
}