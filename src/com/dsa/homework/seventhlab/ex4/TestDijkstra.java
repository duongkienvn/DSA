package com.dsa.homework.seventhlab.ex4;

public class TestDijkstra {
    public static void main(String[] args) {
        Dijkstra graph = new Dijkstra(5);
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 7);
        graph.addEdge(2, 4, 3);
        graph.addEdge(3, 4, 2);

        System.out.println("Testing Dijkstra:");
        graph.shortestPath(0, 4); // Expected output: Shortest path from 0 to 4: 7
    }
}
