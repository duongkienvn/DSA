package com.dsa.homework.seventhlab.ex4;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Dijkstra {
    class Pair {
        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    private final int[][] adjMatrix;
    private final int vertices;

    public Dijkstra(int vertices) {
        this.vertices = vertices;
        adjMatrix = new int[vertices][vertices];
    }

    public void addEdge(int u, int v, int weight) {
        if (adjMatrix[u][v] == 0) {
            adjMatrix[u][v] = weight;
        }
    }

    public void shortestPath(int start, int end) {
        int[] minDist = new int[vertices];
        boolean[] visited = new boolean[vertices];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[start] = 0;
        Queue<Pair> minHeap = new PriorityQueue<>((a, b) -> a.first - b.first);
        minHeap.add(new Pair(0, start));

        while (!minHeap.isEmpty()) {
            Pair pair = minHeap.poll();
            int u = pair.second;
            int dist = pair.first;

            if (visited[u]) {
                continue;
            }
            visited[u] = true;
            for (int v = 0; v < vertices; v++) {
                if (adjMatrix[u][v] != 0 && !visited[v] && minDist[v] > minDist[u] + adjMatrix[u][v]) {
                    minDist[v] = minDist[u] + adjMatrix[u][v];
                    minHeap.add(new Pair(minDist[v], v));
                }
            }
        }

        System.out.println("Shortest path from " + start + " to " + end + ": " + minDist[end]);
    }
}
