/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.banking.datastructures;

import com.banking.models.Account;

public class AccountHashTable {
    // Saare accounts ko aik List mein nikalne ke liye method
public java.util.List<Account> getAllAccounts() {
    java.util.List<Account> accountList = new java.util.ArrayList<>();
    if (table == null) return accountList;
    
    for (int i = 0; i < table.length; i++) {
        HashNode current = table[i];
        while (current != null) {
            accountList.add(current.value);
            current = current.next;
        }
    }
    return accountList;
}
    // Internal Node class for Separate Chaining collision handling
    private static class HashNode {
        String key;     // Account ID
        Account value;  // Account Object
        HashNode next;

        public HashNode(String key, Account value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private HashNode[] table;
    private int capacity;
    private int size;

    // Constructor with default capacity
    public AccountHashTable(int capacity) {
        this.capacity = capacity;
        this.table = new HashNode[capacity];
        this.size = 0;
    }

    // Custom Hash Function for String Keys
    private int hash(String key) {
        int hashVal = 0;
        for (int i = 0; i < key.length(); i++) {
            hashVal = 31 * hashVal + key.charAt(i);
        }
        hashVal = hashVal % capacity;
        if (hashVal < 0) {
            hashVal += capacity;
        }
        return hashVal;
    }

    // Insert or update an account - O(1) average time
    public void put(String key, Account account) {
        int bucketIndex = hash(key);
        HashNode head = table[bucketIndex];

        // Check if key already exists, update value if found
        HashNode current = head;
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = account;
                return;
            }
            current = current.next;
        }

        // Insert new node at the head of the chain
        HashNode newNode = new HashNode(key, account);
        newNode.next = head;
        table[bucketIndex] = newNode;
        size++;
    }

    // Retrieve account by ID - O(1) average time (Crucial for Login)
    public Account get(String key) {
        int bucketIndex = hash(key);
        HashNode current = table[bucketIndex];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null; // Account not found
    }

    // Remove account from hash table
    public boolean remove(String key) {
        int bucketIndex = hash(key);
        HashNode head = table[bucketIndex];
        HashNode prev = null;
        HashNode current = head;

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    table[bucketIndex] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}