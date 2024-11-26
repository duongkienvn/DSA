package com.dsa.homework.seventhlab.ex1;

import java.util.List;

public interface Graph {
    void addEdge(int u, int v);
    void removeEdge(int u, int v);
    boolean hasEdge(int u, int v);
    int degree(int u);
    int numVertices();
    int numEdges();
    List<Integer> adjacentVertices(int u);
}
