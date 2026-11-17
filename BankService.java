/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.banking.services;

import com.banking.datastructures.*;
import com.banking.models.Account;
import com.banking.models.Transaction;

import java.util.List;

public class BankService {
    private AccountHashTable accountTable;
    private TransactionLedger ledger;
    private RollbackStack rollbackStack;
    private TransferHeap transferHeap;
    private BalanceBST balanceBST;
    private TransferGraph transferGraph;

    public BankService() {
        // Initialize our custom data structures with default capacities
        this.accountTable = new AccountHashTable(100);
        this.ledger = new TransactionLedger();
        this.rollbackStack = new RollbackStack();
        this.transferHeap = new TransferHeap(50);
        this.balanceBST = new BalanceBST();
        this.transferGraph = new TransferGraph();

        // Seed default accounts for testing your GUI right away
        seedDefaultAccounts();
    }

    private void seedDefaultAccounts() {
        // Create an Admin and a couple of User accounts
        Account admin = new Account("admin", "admin123", "ADMIN", 50000.0);
        Account user1 = new Account("ahmed", "1234", "USER", 15000.0);
        Account user2 = new Account("ali", "1234", "USER", 8500.0);

        // Register in Hash Table
        accountTable.put(admin.getAccountId(), admin);
        accountTable.put(user1.getAccountId(), user1);
        accountTable.put(user2.getAccountId(), user2);

        // Register in BST for range searches
        balanceBST.insert(admin);
        balanceBST.insert(user1);
        balanceBST.insert(user2);

        // Register vertices in Graph
        transferGraph.addVertex(admin.getAccountId());
        transferGraph.addVertex(user1.getAccountId());
        transferGraph.addVertex(user2.getAccountId());
    }

    // 1. Authentication via Hash Table - O(1)
    public Account authenticate(String accountId, String password) {
        Account acc = accountTable.get(accountId);
        if (acc != null && acc.verifyPassword(password) && acc.isActive()) {
            return acc;
        }
        return null;
    }

    // 2. Register a new account
    public boolean registerAccount(String accountId, String password, String role, double initialBalance) {
        if (accountTable.get(accountId) != null) {
            return false; // Account already exists
        }
        Account newAcc = new Account(accountId, password, role, initialBalance);
        accountTable.put(accountId, newAcc);
        balanceBST.insert(newAcc);
        transferGraph.addVertex(accountId);
        return true;
    }

    // 3. Process a money transfer
    public boolean processTransfer(String senderId, String recipientId, double amount, int priorityScore) {
        Account sender = accountTable.get(senderId);
        Account recipient = accountTable.get(recipientId);

        if (sender == null || recipient == null || sender.getBalance() < amount) {
            return false; // Invalid transfer
        }

        // Update balances
        sender.setBalance(sender.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);

        // Create transaction record
        String txnId = "TXN-" + System.currentTimeMillis();
        Transaction txn = new Transaction(txnId, senderId, recipientId, amount, priorityScore);
        txn.setStatus("COMPLETE");

        // Log into Doubly Linked List and Rollback Stack
        ledger.append(txn);
        rollbackStack.push(txn);

        // Push to Priority Queue (Max-Heap) if it's a priority transfer
        transferHeap.insert(txn);

        // Add edge to Graph for network / fraud checking
        transferGraph.addEdge(senderId, recipientId);

        return true;
    }

    // Getters for data structures to be used by UI dashboards
    public TransactionLedger getLedger() { return ledger; }
    public TransferHeap getTransferHeap() { return transferHeap; }
    public BalanceBST getBalanceBST() { return balanceBST; }
    public TransferGraph getTransferGraph() { return transferGraph; }
    public AccountHashTable getAccountTable() { return accountTable; }
}