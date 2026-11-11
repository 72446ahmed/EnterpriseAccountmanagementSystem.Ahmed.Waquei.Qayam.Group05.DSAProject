/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.banking.datastructures;

import com.banking.models.Account;
import java.util.ArrayList;
import java.util.List;

public class BalanceBST {

    // Internal Node class for BST
    public static class BSTNode {
        Account account;
        BSTNode left;
        BSTNode right;

        public BSTNode(Account account) {
            this.account = account;
            this.left = null;
            this.right = null;
        }
    }

    private BSTNode root;

    public BalanceBST() {
        this.root = null;
    }

    // Insert account into BST based on account balance
    public void insert(Account account) {
        root = insertRec(root, account);
    }

    private BSTNode insertRec(BSTNode root, Account account) {
        if (root == null) {
            return new BSTNode(account);
        }
        if (account.getBalance() < root.account.getBalance()) {
            root.left = insertRec(root.left, account);
        } else {
            root.right = insertRec(root.right, account);
        }
        return root;
    }

    // Range search to find all accounts with balance between min and max
    public List<Account> rangeSearch(double minBalance, double maxBalance) {
        List<Account> result = new ArrayList<>();
        rangeSearchRec(root, minBalance, maxBalance, result);
        return result;
    }

    private void rangeSearchRec(BSTNode root, double min, double max, List<Account> result) {
        if (root == null) return;

        if (root.account.getBalance() > min) {
            rangeSearchRec(root.left, min, max, result);
        }

        if (root.account.getBalance() >= min && root.account.getBalance() <= max) {
            result.add(root.account);
        }

        if (root.account.getBalance() < max) {
            rangeSearchRec(root.right, min, max, result);
        }
    }
}