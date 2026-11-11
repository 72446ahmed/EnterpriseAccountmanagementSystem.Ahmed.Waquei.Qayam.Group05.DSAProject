/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.banking.models;

public class Transaction {
    private String transactionId;
    private String senderId;
    private String recipientId;
    private double amount;
    private int priorityScore; // Used for Max-Heap Priority Queue ranking
    private String status;     // "PENDING", "COMPLETE", "ROLLED_BACK"

    // Constructor
    public Transaction(String transactionId, String senderId, String recipientId, double amount, int priorityScore) {
        this.transactionId = transactionId;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.amount = amount;
        this.priorityScore = priorityScore;
        this.status = "PENDING";
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

    @Override
    public String toString() {
        return "Transaction [ID=" + transactionId + ", From=" + senderId + ", To=" + recipientId + ", Amount=" + amount + ", Priority=" + priorityScore + ", Status=" + status + "]";
    }
}