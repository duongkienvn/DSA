package com.dsa.homework.seventhlab.ex4;

import java.util.ArrayList;
import java.util.List;

class Color {
    public List<List<Integer>> buildAdjList(int v, List<int[]> edges) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        return adjList;
    }

    public boolean isColoring(int v, int color, List<List<Integer>> adjList, int[] colors) {
        // check xem color co to duoc dinh v hay khong
        for (int neighbor : adjList.get(v)) {
            if (color == colors[neighbor]) {
                return false;
            }
        }

        return true;
    }

    public int solve(int color, int[] colors, List<List<Integer>> adjList) {
        // dem so luong dinh toi da ma mau color co the to duoc
        int result = 0;
        for (int i = 0; i < colors.length; i++) {
            if (colors[i] == 0 && isColoring(i, color, adjList, colors)) {
                colors[i] = color;
                result++;
            }
        }

        return result;
    }

    public boolean graphColoring(int v, List<int[]> edges, int m) {
        List<List<Integer>> adjList = buildAdjList(v, edges);
        int[] colors = new int[v];
        int color = 1;
        int vNum = 0;
        while (vNum < v) {
            if (color > m) {
                return false;
            }
            vNum += solve(color++, colors, adjList);
        }

        return true;
    }
}
