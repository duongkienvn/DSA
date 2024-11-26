package com.dsa.homework.seventhlab.ex4;

public class GraphTest {
    public static void main(String[] args) {
        GraphTraversal graph = new GraphTraversal(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);

        graph.dfs(0);
        graph.bfs(0);
    }
}

