package com.dsa.homework.seventhlab.ex4;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Prim {
    public int spanningTree(int V, int E, List<List<int[]>> adj) {
        Queue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        boolean[] visited = new boolean[V];
        int mstWeigth = 0;

        minHeap.add(new int[]{0, 0});
        while (!minHeap.isEmpty()) {
            int[] edge = minHeap.poll();
            int node = edge[0];
            int weigth = edge[1];

            if (visited[node]) {
                continue;
            }
            visited[node] = true;
            mstWeigth += weigth;

            for (int[] neighbor : adj.get(node)) {
                int neighborNode = neighbor[0];
                int neighborWeight = neighbor[1];

                if (!visited[neighborNode]) {
                    minHeap.add(new int[]{neighborNode, neighborWeight});
                }
            }
        }

        return mstWeigth;
    }
}
