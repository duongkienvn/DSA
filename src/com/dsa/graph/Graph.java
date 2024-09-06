package com.dsa.graph;

import java.util.*;

public class Graph {
    public int findCenter(int[][] edges) {
        int rows = edges.length;
        int count[] = new int[100001];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < 2; j++) {
                count[edges[i][j]]++;
                if (count[edges[i][j]] == rows) {
                    return edges[i][j];
                }
            }
        }

        return -1;
    }

    private boolean visited[] = new boolean[2 * 100000 + 1];
    private List<Integer> adj[] = new List[2 * 100000 + 1];

    public void dfs(int u) {
        visited[u] = true;
        for (int x : adj[u]) {
            if (!visited[x]) {
                dfs(x);
            }
        }
    }

    public boolean path(int source, int destination) {
        dfs(source);
        if (visited[destination]) {
            return true;
        }
        return false;
    }

    public void edgeListToAdjList(int[][] edges) {
        for (int i = 0; i < edges.length; i++) {
            int edge[] = edges[i];
            if (adj[edge[0]] == null) {
                adj[edge[0]] = new ArrayList<>();
            }
            if (adj[edge[1]] == null) {
                adj[edge[1]] = new ArrayList<>();
            }
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
    }

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        edgeListToAdjList(edges);

        return path(source, destination);
    }

    int dx[] = {-1, 0, 0, 1};
    int dy[] = {0, -1, 1, 0};

    public void dfsBoard(int i, int j, char[][] board) {
        int m = board.length;
        int n = board[0].length;
        board[i][j] = '#';

        for (int k = 0; k < 4; k++) {
            int i1 = i + dx[k];
            int j1 = j + dy[k];
            if (i1 >= 0 && i1 < m && j1 >= 0 && j1 < n && board[i1][j1] == 'O') {
                dfsBoard(i1, j1, board);
            }
        }
    }

    public void solve(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O') {
                dfsBoard(i, 0, board);
            }
            if (board[i][cols - 1] == 'O') {
                dfsBoard(i, cols - 1, board);
            }
        }

        for (int j = 0; j < cols; j++) {
            if (board[0][j] == 'O') {
                dfsBoard(0, j, board);
            }
            if (board[rows - 1][j] == 'O') {
                dfsBoard(rows - 1, j, board);
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public int longestIncreasingPath(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;


        return 1;
    }

    public void dfs(int[][] isConnected, boolean visited[], int city) {
        int n = isConnected.length;
        visited[city] = true;
        for (int i = 0; i < n; i++) {
            if (isConnected[city][i] == 1 && !visited[city]) {
                dfs(isConnected, visited, i);
            }
        }
    }

    public void bfs(int[][] isConnected, boolean[] visited, int city) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(city);
        visited[city] = true;
        while (!queue.isEmpty()) {
            int currentCity = queue.poll();
            for (int i = 0; i < isConnected.length; i++) {
                if (!visited[i] && isConnected[currentCity][i] == 1) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }

    public int findCircleNum(int[][] isConnected) {
        int provinces = 0;
        int length = isConnected.length;
        boolean visited[] = new boolean[length];

        for (int i = 0; i < length; i++) {
            if (!visited[i]) {
                bfs(isConnected, visited, i);
                provinces++;
            }
        }

        return provinces;
    }

    private Map<Integer, Set<Integer>> ancestors = new HashMap<>();

    public void edgeListToAdjListII(int[][] edges) {
        int length = edges.length;
        for (int i = 0; i < length; i++) {
            int edge[] = edges[i];
            if (!ancestors.containsKey(edge[1])) {
                ancestors.put(edge[1], new HashSet<>());
            }
            ancestors.get(edge[1]).add(edge[0]);
            if (ancestors.get(edge[0]) != null) {
                ancestors.get(edge[1]).addAll(ancestors.get(edge[0]));
            }
        }
    }

    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(edges, (a, b) -> Integer.compare(a[1], b[1]));
        edgeListToAdjListII(edges);
        for (int i = 0; i < n; i++) {
            if (!ancestors.containsKey(i)) {
                result.add(new ArrayList<>());
            } else {
                List<Integer> list = new ArrayList<>(ancestors.get(i));
                Collections.sort(list);
                result.add(list);
            }
        }

        return result;
    }
}
