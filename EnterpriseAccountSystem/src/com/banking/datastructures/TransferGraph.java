/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.banking.datastructures;

import java.util.*;

public class TransferGraph {
    // Adjacency list: Account ID -> List of connected Account IDs (transfer recipients)
    private Map<String, List<String>> adjList;

    public TransferGraph() {
        this.adjList = new HashMap<>();
    }

    // Add account vertex
    public void addVertex(String accountId) {
        adjList.putIfAbsent(accountId, new ArrayList<>());
    }

    // Add directed edge representing a transfer from sender to recipient
    public void addEdge(String senderId, String recipientId) {
        addVertex(senderId);
        addVertex(recipientId);
        adjList.get(senderId).add(recipientId);
    }

    // Check for cycles using DFS (useful for audit / suspicious loop detection)
    public boolean hasCycle() {
        Set<String> visited = new HashSet<>();
        Set<String> recStack = new HashSet<>();

        for (String vertex : adjList.keySet()) {
            if (dfsCycleCheck(vertex, visited, recStack)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfsCycleCheck(String current, Set<String> visited, Set<String> recStack) {
        if (recStack.contains(current)) return true;
        if (visited.contains(current)) return false;

        visited.add(current);
        recStack.add(current);

        List<String> neighbors = adjList.get(current);
        if (neighbors != null) {
            for (String neighbor : neighbors) {
                if (dfsCycleCheck(neighbor, visited, recStack)) {
                    return true;
                }
            }
        }

        recStack.remove(current);
        return false;
    }

    public Map<String, List<String>> getGraph() {
        return adjList;
    }
}