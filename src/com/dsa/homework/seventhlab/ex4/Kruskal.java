package com.dsa.homework.seventhlab.ex4;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kruskal {
    class Edge {
        int u, v, weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    public void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }

    public int find(int[] parents, int v) {
        if (v == parents[v]) {
            return v;
        }

        return parents[v] = find(parents, parents[v]);
    }

    public boolean union(int a, int b, int[] size, int[] parents) {
        int parentA = find(parents, a);
        int parentB = find(parents, b);

        if (parentA == parentB) {
            return false;
        }
        if (size[parentA] < size[parentB]) {
            swap(parentA, parentB);
        }
        parents[parentB] = parentA;
        size[parentA] += size[parentB];
        return true;
    }

    public int spanningTree(int V, int E, List<List<int[]>> adj) {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            for (int[] neighbor : adj.get(i)) {
                if (i < neighbor[0]) {
                    edges.add(new Edge(i, neighbor[0], neighbor[1]));
                }
            }
        }

        Collections.sort(edges, (a, b) -> a.weight - b.weight);
        List<Edge> mst = new ArrayList<>();
        int minWeight = 0;
        int[] size = new int[V];
        int[] parents = new int[V];

        for (int i = 0; i < V; i++) {
            parents[i] = i;
            size[i] = 1;
        }

        for (int i = 0; i < E; i++) {
            if (mst.size() == V - 1) {
                break;
            }
            Edge edge = edges.get(i);
            if (union(edge.u, edge.v, size, parents)) {
                mst.add(edge);
                minWeight += edge.weight;
            }
        }

        return minWeight;
    }
}
