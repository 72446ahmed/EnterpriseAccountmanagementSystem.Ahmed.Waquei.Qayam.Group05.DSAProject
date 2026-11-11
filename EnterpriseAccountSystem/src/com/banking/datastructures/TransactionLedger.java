/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.banking.datastructures;

import com.banking.models.Transaction;

public class TransactionLedger {

    // Internal Node class for Doubly Linked List
    public static class DLLNode {
        Transaction transaction;
        DLLNode prev;
        DLLNode next;

        public DLLNode(Transaction transaction) {
            this.transaction = transaction;
            this.prev = null;
            this.next = null;
        }
    }

    private DLLNode head;
    private DLLNode tail;
    private int size;

    public TransactionLedger() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Append new transaction to the end of the ledger - O(1) time
    public void append(Transaction transaction) {
        DLLNode newNode = new DLLNode(transaction);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    // Get the most recent transaction
    public Transaction getRecent() {
        if (tail == null) return null;
        return tail.transaction;
    }

    // Display transactions from oldest to newest (Forward Traversal)
    public void displayForward() {
        DLLNode current = head;
        while (current != null) {
            System.out.println(current.transaction);
            current = current.next;
        }
    }

    // Display transactions from newest to oldest (Backward Traversal)
    public void displayBackward() {
        DLLNode current = tail;
        while (current != null) {
            System.out.println(current.transaction);
            current = current.prev;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
