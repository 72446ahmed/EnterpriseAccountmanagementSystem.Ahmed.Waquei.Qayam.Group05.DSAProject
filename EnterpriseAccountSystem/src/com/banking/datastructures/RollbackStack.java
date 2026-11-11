/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.banking.datastructures;

import com.banking.models.Transaction;

public class RollbackStack {

    // Internal Node class for Stack
    private static class StackNode {
        Transaction transaction;
        StackNode next;

        public StackNode(Transaction transaction) {
            this.transaction = transaction;
            this.next = null;
        }
    }

    private StackNode top;
    private int size;

    public RollbackStack() {
        this.top = null;
        this.size = 0;
    }

    // Push uncommitted transaction step onto the stack - O(1) time
    public void push(Transaction transaction) {
        StackNode newNode = new StackNode(transaction);
        newNode.next = top;
        top = newNode;
        size++;
    }

    // Pop the most recent transaction for rollback/undo - O(1) time
    public Transaction pop() {
        if (isEmpty()) {
            return null;
        }
        Transaction txn = top.transaction;
        top = top.next;
        size--;
        return txn;
    }

    // Peek at the top transaction without removing it
    public Transaction peek() {
        if (isEmpty()) {
            return null;
        }
        return top.transaction;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }
}