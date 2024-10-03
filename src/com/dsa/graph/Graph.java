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

    public double dfs(String start, String end, Set<String> visited, Map<String, Map<String, Double>> graph) {
        if (!graph.containsKey(start)) {
            return -1;
        }
        if (graph.get(start).containsKey(end)) {
            return graph.get(start).get(end);
        }

        visited.add(start);

        for (Map.Entry<String, Double> neighbor : graph.get(start).entrySet()) {
            if (!visited.contains(neighbor.getKey())) {
                double result = dfs(neighbor.getKey(), end, visited, graph);
                if (result != -1) {
                    return result *= neighbor.getValue();
                }
            }
        }

        return -1;
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            String A = equations.get(i).get(0);
            String B = equations.get(i).get(1);
            double value = values[i];

            graph.computeIfAbsent(A, k -> new HashMap<>()).put(B, value);
            graph.computeIfAbsent(B, k -> new HashMap<>()).put(A, 1 / value);
        }

        double results[] = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            String C = queries.get(i).get(0);
            String D = queries.get(i).get(1);

            if (!graph.containsKey(C) || !graph.containsKey(D)) {
                results[i] = -1;
            } else if (C.equals(D)) {
                results[i] = 1;
            } else {
                Set<String> visited = new HashSet<>();
                results[i] = dfs(C, D, visited, graph);
            }
        }

        return results;
    }

    public void adjListBuilding(int[][] edges, Map<Integer, List<Integer>> adjList) {
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            adjList.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            adjList.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }
    }

    public int[] findRedundantConnection(int[][] edges) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        adjListBuilding(edges, adjList);

        List<int[]> removedEdgeList = new ArrayList<>();

        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            if (adjList.get(edge[0]).size() > 1 && adjList.get(edge[1]).size() > 1) {
                removedEdgeList.add(edge);
            }
        }

        removedEdgeList.forEach(num -> System.out.println(Arrays.toString(num)));

        return removedEdgeList.get(removedEdgeList.size() - 1);
    }

    public void convertToAdjList(int[][] graph, List<List<Integer>> adjList) {
        for (int i = 0; i < graph.length; i++) {
            if (graph[i].length == 0) {
                adjList.add(new ArrayList<>());
                continue;
            }
            if (adjList.size() == i) {
                adjList.add(new ArrayList<>());
            }
            for (int j = 0; j < graph[i].length; j++) {
                adjList.get(i).add(graph[i][j]);
            }
        }
    }

    public void dfs(List<List<Integer>> result, List<Integer> path, int current, int end, int[][] graph) {
        if (current == end) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int neighbor : graph[current]) {
            path.add(neighbor);
            dfs(result, path, neighbor, end, graph);
            path.remove(path.size() - 1);
        }
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        dfs(result, path, 0, graph.length - 1, graph);

        return result;
    }

    public int dfs(int[][] grid, int i, int j) {
        grid[i][j] = 0;
        int area = 1;
        for (int k = 0; k < 4; k++) {
            int i1 = i + dx[k];
            int j1 = j + dy[k];
            if (i1 >= 0 && i1 < grid.length && j1 >= 0 && j1 < grid[0].length && grid[i1][j1] == 1) {
                area += dfs(grid, i1, j1);
            }
        }

        return area;
    }

    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    int area = dfs(grid, i, j);
                    maxArea = Math.max(area, maxArea);
                }
            }
        }

        return maxArea;
    }

    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    perimeter += 4;

                    if (i > 0 && grid[i - 1][j] == 1) {
                        perimeter -= 2;
                    }
                    if (j > 0 && grid[i][j - 1] == 1) {
                        perimeter -= 2;
                    }
                }
            }
        }

        return perimeter;
    }

    public boolean containsPair(List<int[]> list, int i, int j) {
        for (int[] pair : list) {
            if (pair[0] == i && pair[1] == j) {
                return true;
            }
        }
        return false;
    }

    public void dfs(int[][] grid, int i, int j, boolean[][] visited, List<int[]> borders, int originalColor, int m, int n) {
        visited[i][j] = true;
        boolean isBorder = false;

        for (int k = 0; k < 4; k++) {
            int ni = i + dx[k];
            int nj = j + dy[k];
            if (ni < 0 || nj < 0 || ni >= m || nj >= n) {
                isBorder = true;
            } else if (!visited[ni][nj] && grid[ni][nj] == originalColor) {
                dfs(grid, ni, nj, visited, borders, originalColor, m, n);
            } else if (grid[ni][nj] != originalColor) {
                isBorder = true;
            }
        }

        if (isBorder) {
            borders.add(new int[]{i, j});
        }
    }

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        List<int[]> borders = new ArrayList<>();
        int originalColor = grid[row][col];

        dfs(grid, row, col, visited, borders, originalColor, m, n);

        for (int[] border : borders) {
            grid[border[0]][border[1]] = color;
        }

        return grid;
    }

    public int countPaths(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int result = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

            }
        }

        return 1;
    }

    public void dfs(char[][] grid, int i, int j) {
        grid[i][j] = '0';

        for (int k = 0; k < 4; k++) {
            int i1 = i + dx[k];
            int j1 = j + dy[k];
            if (i1 >= 0 && i1 < grid.length && j1 >= 0 && j1 < grid[0].length && grid[i1][j1] == '1') {
                dfs(grid, i1, j1);
            }
        }
    }

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    cnt++;
                }
            }
        }

        return cnt;
    }

    public boolean isValid(int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    public int bfs(int[][] grid, Queue<int[]> queue, int freshCount, int m, int n) {
        int minutes = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean hasRotten = false;

            for (int i = 0; i < size; i++) {
                int[] rotten = queue.poll();
                int x = rotten[0];
                int y = rotten[1];

                for (int k = 0; k < 4; k++) {
                    int newX = x + dx[k];
                    int newY = y + dy[k];

                    if (isValid(newX, newY, m, n) && grid[newX][newY] == 1) {
                        grid[newX][newY] = 2;
                        hasRotten = true;
                        freshCount--;
                        queue.add(new int[]{newX, newY});
                    }
                }
            }

            if (hasRotten) {
                minutes++;
            }
        }

        return freshCount == 0 ? minutes : -1;
    }

    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int freshCount = 0;
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    freshCount++;
                }
            }
        }

        return bfs(grid, queue, freshCount, m, n);
    }

    public List<Integer>[] buildAdjList(int[][] edges, int n) {
        List<Integer>[] adjList = new List[n];

        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            adjList[edge[0]].add(edge[1]);
            adjList[edge[1]].add(edge[0]);
        }

        return adjList;
    }

    public void dfs(boolean[] visited, List<Integer>[] adjList, int node) {
        visited[node] = true;

        for (int neighbor : adjList[node]) {
            if (!visited[neighbor]) {
                dfs(visited, adjList, neighbor);
            }
        }
    }

    public int countComponents(int n, int[][] edges) {
        List<Integer>[] adjList = buildAdjList(edges, n);
        int countComponents = 0;
        boolean visisted[] = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visisted[i]) {
                dfs(visisted, adjList, i);
                countComponents++;
            }
        }

        return countComponents;
    }

    public void dfsRooms(List<List<Integer>> rooms, Set<Integer> keys, int index) {
        if (keys.containsAll(rooms.get(index))) {
            return;
        }
        keys.addAll(rooms.get(index));

        for (Integer room : rooms.get(index)) {
            if (!keys.contains(room)) {
                dfsRooms(rooms, keys, room);
            }
        }
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> keys = new HashSet<>();
        int n = rooms.size();

        dfsRooms(rooms, keys, 0);
        for (int i = 1; i < n; i++) {
            if (!keys.contains(i)) {
                return false;
            }
        }

        return true;
    }

    public List<List<Integer>> constructAdjList(int[][] edges, int n) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        return graph;
    }

    public boolean hasCycle(List<List<Integer>> graph, boolean[] visited, int node, int parent) {
        visited[node] = true;

        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                if (hasCycle(graph, visited, neighbor, node)) {
                    return true;
                }
            } else if (neighbor != parent) {
                return true;
            }
        }

        return false;
    }

    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false;
        }

        List<List<Integer>> graph = constructAdjList(edges, n);
        boolean[] visited = new boolean[n];
        if (hasCycle(graph, visited, 0, -1)) {
            return false;
        }

        return true;
    }
}