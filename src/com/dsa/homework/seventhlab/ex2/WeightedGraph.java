package com.dsa.homework.seventhlab.ex2;

import java.util.List;

public interface WeightedGraph {
    void addEdge(int u, int v, int weight);
    void removeEdge(int u, int v);
    boolean hasEdge(int u, int v);
    int getWeight(int u, int v);
    int inDegree(int u);
    int outDegree(int u);
    int numVertices();
    int numEdges();
    List<Integer> adjacentVertices(int u);
}
