package com.dsa.homework.seventhlab.ex1;

import java.util.ArrayList;
import java.util.List;

public class UndirectedGraph implements Graph {
    private final int[][] adjMatrix;
    private int edgeCount;

    public UndirectedGraph(int n) {
        adjMatrix = new int[n][n];
        edgeCount = 0;
    }

    @Override
    public void addEdge(int u, int v) {
        if (adjMatrix[u][v] == 0) {
            adjMatrix[u][v] = adjMatrix[v][u] = 1;
            edgeCount++;
        }
    }

    @Override
    public void removeEdge(int u, int v) {
        if (adjMatrix[u][v] == 1) {
            adjMatrix[u][v] = adjMatrix[v][u] = 0;
            edgeCount--;
        }
    }

    @Override
    public boolean hasEdge(int u, int v) {
        return adjMatrix[u][v] == 1;
    }

    @Override
    public int degree(int u) {
        int degree = 0;
        for (int v = 0; v < adjMatrix.length; v++) {
            degree += adjMatrix[u][v];
        }

        return degree;
    }

    @Override
    public int numVertices() {
        return adjMatrix.length;
    }

    @Override
    public int numEdges() {
        return edgeCount;
    }

    @Override
    public List<Integer> adjacentVertices(int u) {
        List<Integer> neighbors = new ArrayList<>();
        for (int v = 0; v < adjMatrix.length; v++) {
            if (adjMatrix[u][v] == 1) {
                neighbors.add(v);
            }
        }

        return neighbors;
    }
}
