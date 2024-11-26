package com.dsa.homework.seventhlab.ex2;

import java.util.ArrayList;
import java.util.List;

public class DirectedWeightedGraph implements WeightedGraph {
    private final int[][] adjMatrix;
    private int edgeCount;

    public DirectedWeightedGraph(int n) {
        adjMatrix = new int[n][n];
        edgeCount = 0;
    }

    @Override
    public void addEdge(int u, int v, int weight) {
        if (adjMatrix[u][v] == 0) {
            adjMatrix[u][v] = weight;
            edgeCount++;
        }
    }

    @Override
    public void removeEdge(int u, int v) {
        if (adjMatrix[u][v] != 0) {
            adjMatrix[u][v] = 0;
            edgeCount--;
        }
    }

    @Override
    public boolean hasEdge(int u, int v) {
        return adjMatrix[u][v] != 0;
    }

    @Override
    public int getWeight(int u, int v) {
        return adjMatrix[u][v];
    }

    @Override
    public int inDegree(int u) {
        int inDegree = 0;
        for (int v = 0; v < adjMatrix.length; v++) {
            inDegree += adjMatrix[v][u] != 0 ? 1 : 0;
        }

        return inDegree;
    }

    @Override
    public int outDegree(int u) {
        int outDegree = 0;
        for (int v = 0; v < adjMatrix.length; v++) {
            outDegree += adjMatrix[u][v] != 0 ? 1 : 0;
        }

        return outDegree;
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
            if (adjMatrix[u][v] != 0) {
                neighbors.add(v);
            }
        }

        return neighbors;
    }
}
