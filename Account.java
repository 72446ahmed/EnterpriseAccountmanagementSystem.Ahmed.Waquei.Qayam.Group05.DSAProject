package com.banking.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String accountId;
    private String fullName;
    private String cnic;
    private String phone;
    private String dob;
    private String address;
    private String accountType;
    private double balance;
    private String username;
    private String password;
    private String role; // "ADMIN" or "USER"
    private boolean isActive;
    
    // Transaction History List (Naya addition)
    private List<String> transactionHistory = new ArrayList<>();

    // 1. Purana Constructor (BankService ke default/seed accounts ke liye)
    public Account(String accountId, String password, String role, double balance) {
        this.accountId = accountId;
        this.password = password;
        this.role = role;
        this.balance = balance;
        this.isActive = true;
        this.fullName = "System User";
        this.cnic = "00000-0000000-0";
        this.phone = "00000000000";
        this.dob = "N/A";
        this.address = "N/A";
        this.accountType = "Standard";
        this.username = accountId;
    }

    // 2. Naya Constructor (Create Account Registration Form ke liye)
    public Account(String accountId, String fullName, String cnic, String phone, String dob, String address, String accountType, double balance, String username, String password) {
        this.accountId = accountId;
        this.fullName = fullName;
        this.cnic = cnic;
        this.phone = phone;
        this.dob = dob;
        this.address = address;
        this.accountType = accountType;
        this.balance = balance;
        this.username = username;
        this.password = password;
        this.role = "USER";
        this.isActive = true;
    }

    // Getters and Setters
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    // --- TRANSACTION HISTORY METHODS (Naya addition) ---
    public List<String> getTransactionHistory() {
        if (transactionHistory == null) {
            transactionHistory = new ArrayList<>();
        }
        return transactionHistory;
    }

    public void addTransaction(String transactionDetails) {
        if (transactionHistory == null) {
            transactionHistory = new ArrayList<>();
        }
        this.transactionHistory.add(transactionDetails);
    }

    // Password verification helper method
    public boolean verifyPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    // --- FIXES IMPLEMENTED HERE ---

    public String getHolderName() {
        return this.fullName; // Account holder ka real name return karega
    }

    public String getPin() {
        return this.password; // Login authentication ke liye password/PIN return karega
    }

    @Override
    public String toString() {
        return "Account [ID=" + accountId + ", Name=" + fullName + ", Role=" + role + ", Balance=" + balance + ", Active=" + isActive + "]";
    }
}