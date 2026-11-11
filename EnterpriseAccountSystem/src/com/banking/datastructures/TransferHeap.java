/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.banking.datastructures;

import com.banking.models.Transaction;

public class TransferHeap {
    private Transaction[] heap;
    private int capacity;
    private int size;

    public TransferHeap(int capacity) {
        this.capacity = capacity;
        this.heap = new Transaction[capacity];
        this.size = 0;
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int leftChild(int i) { return (2 * i) + 1; }
    private int rightChild(int i) { return (2 * i) + 2; }

    private void swap(int i, int j) {
        Transaction temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Insert transaction into Max-Heap based on priorityScore - O(log N)
    public void insert(Transaction txn) {
        if (size == capacity) {
            capacity *= 2;
            Transaction[] newHeap = new Transaction[capacity];
            System.arraycopy(heap, 0, newHeap, 0, size);
            heap = newHeap;
        }

        int currentIndex = size;
        heap[currentIndex] = txn;
        size++;

        // Heapify up to maintain Max-Heap property
        while (currentIndex > 0 && heap[parent(currentIndex)].getPriorityScore() < heap[currentIndex].getPriorityScore()) {
            swap(currentIndex, parent(currentIndex));
            currentIndex = parent(currentIndex);
        }
    }

    // Extract highest priority transaction (Root) - O(log N)
    public Transaction extractMax() {
        if (size <= 0) return null;
        if (size == 1) {
            size--;
            return heap[0];
        }

        Transaction root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);

        return root;
    }

    private void heapifyDown(int i) {
        int maxIndex = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if (left < size && heap[left].getPriorityScore() > heap[maxIndex].getPriorityScore()) {
            maxIndex = left;
        }
        if (right < size && heap[right].getPriorityScore() > heap[maxIndex].getPriorityScore()) {
            maxIndex = right;
        }

        if (i != maxIndex) {
            swap(i, maxIndex);
            heapifyDown(maxIndex);
        }
    }

    public Transaction peekMax() {
        return size > 0 ? heap[0] : null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}