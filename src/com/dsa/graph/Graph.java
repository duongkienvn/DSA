package com.dsa.graph;

import java.util.*;
import java.util.stream.Collectors;

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


    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {


        return 1;
    }

    public void islandsAndTreasure(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;


    }

    public static void topoSort(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                topoSort(neighbor, adj, visited, stack);
            }
        }

        stack.push(node);
    }

    public static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> adj) {
        int vNum = adj.size();
        boolean[] visited = new boolean[vNum];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < vNum; i++) {
            if (!visited[i]) {
                topoSort(i, adj, visited, stack);
            }
        }

        ArrayList<Integer> topoOrder = new ArrayList<>();
        while (!stack.isEmpty()) {
            topoOrder.add(stack.pop());
        }

        return topoOrder;
    }

    public static ArrayList<Integer> topologicalSortII(ArrayList<ArrayList<Integer>> adj) {
        int vNum = adj.size();
        int[] inDegree = new int[vNum];

        for (int i = 0; i < vNum; i++) {
            for (int neighbor : adj.get(i)) {
                inDegree[neighbor]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < vNum; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            for (int neighbor : adj.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        return result;
    }

    public void dfsAdj(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfsAdj(neighbor, adj, visited, stack);
            }
        }
        stack.push(node);
    }

    public void dfsReveseAdj(int node, ArrayList<ArrayList<Integer>> rAdj, boolean[] visited) {
        visited[node] = true;
        for (int neighbor : rAdj.get(node)) {
            if (!visited[neighbor]) {
                dfsReveseAdj(neighbor, rAdj, visited);
            }
        }
    }

    public ArrayList<ArrayList<Integer>> reversedGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<ArrayList<Integer>> reversed = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            reversed.add(new ArrayList<>());
        }

        for (int i = 0; i < V; i++) {
            for (int neighbor : adj.get(i)) {
                reversed.get(neighbor).add(i);
            }
        }

        return reversed;
    }

    public int kosaraju(int V, ArrayList<ArrayList<Integer>> adj) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfsAdj(i, adj, visited, stack);
            }
        }

        Arrays.fill(visited, false);
        ArrayList<ArrayList<Integer>> reversed = reversedGraph(V, adj);
        int result = 0;

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                dfsReveseAdj(node, reversed, visited);
                result++;
            }
        }

        return result;
    }

    public boolean dfs(int node, int parent,
                       ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                if (dfs(neighbor, node, adj, visited)) {
                    return true;
                }
            } else {
                if (neighbor != parent) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isCycle(ArrayList<ArrayList<Integer>> adj) {
        int vNum = adj.size();
        boolean[] visited = new boolean[vNum];

        for (int i = 0; i < vNum; i++) {
            if (!visited[i]) {
                if (dfs(i, 0, adj, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    public List<List<Integer>> buildAdj(int[][] edges, int v) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        return adjList;
    }

    public boolean isFullyConnected(List<Integer> component, List<List<Integer>> adj) {
        int size = component.size();
        int requiredEdges = size * (size - 1) / 2;
        int actualEdges = 0;

        for (int i : component) {
            actualEdges += adj.get(i).size();
        }

        return requiredEdges == actualEdges / 2;
    }

    public void dfs(int node, List<List<Integer>> adj, List<Integer> component, boolean[] visited) {
        visited[node] = true;
        component.add(node);
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, component, visited);
            }
        }
    }

    public int findNumberOfGoodComponent(int e, int v, int[][] edges) {
        List<List<Integer>> adjList = buildAdj(edges, v);
        boolean[] visited = new boolean[v + 1];

        int result = 0;
        for (int i = 1; i <= v; i++) {
            if (!visited[i]) {
                List<Integer> component = new ArrayList<>();
                dfs(i, adjList, component, visited);

                if (isFullyConnected(component, adjList)) {
                    result++;
                }
            }
        }

        return result;
    }

    public boolean bfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        int[] parent = new int[adj.size()];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        visited[node] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int neighbor : adj.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                    parent[neighbor] = current;
                } else {
                    if (neighbor != parent[current]) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean isCycleII(ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[adj.size()];
        for (int i = 0; i < adj.size(); i++) {
            if (!visited[i]) {
                if (bfs(i, adj, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean dfs(int node, ArrayList<ArrayList<Integer>> adj, int[] color) {
        color[node] = 1;

        for (int neighbor : adj.get(node)) {
            if (color[neighbor] == 0) {
                if (dfs(neighbor, adj, color)) {
                    return true;
                }
            } else if (color[neighbor] == 1) {
                return true;
            }
        }

        color[node] = 2;
        return false;
    }

    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] color = new int[V];

        for (int i = 0; i < V; i++) {
            if (color[i] == 0) {
                if (dfs(i, adj, color)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean kahn(int node, ArrayList<ArrayList<Integer>> adj) {
        Queue<Integer> queue = new LinkedList<>();
        int[] inDegree = new int[adj.size()];

        for (int i = 0; i < adj.size(); i++) {
            for (int neighbor : adj.get(i)) {
                inDegree[neighbor]++;
            }
        }

        for (int i = 0; i < adj.size(); i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        int cnt = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            cnt++;

            for (int neighbor : adj.get(current)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        return cnt == adj.size() ? false : true;
    }

    public boolean isCyclicII(int V, ArrayList<ArrayList<Integer>> adj) {
        return kahn(0, adj);
    }

    class GfG {
        public int find(int A[], int X) {
            if (X == A[X]) {
                return X;
            }

            return A[X] = find(A, A[X]);
        }

        public void unionSet(int A[], int X, int Z) {
            int parentX = find(A, X);
            int parentZ = find(A, Z);

            if (parentX != parentZ) {
                A[parentX] = parentZ;
            }
        }
    }

    class Kruskal {
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

    class Prim {
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

    public boolean bfs(int node, ArrayList<ArrayList<Integer>> adj) {
        int[] color = new int[adj.size()];
        Queue<Integer> queue = new LinkedList<>();
        Arrays.fill(color, -1);
        queue.add(node);
        color[node] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int neighbor : adj.get(current)) {
                if (color[neighbor] == -1) {
                    queue.add(neighbor);
                    color[neighbor] = 1 - color[current];
                } else if (color[neighbor] == color[current]) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean dfs(int node, int parent, int[] color, ArrayList<ArrayList<Integer>> adj) {
        color[node] = 1 - color[parent];

        for (int neighbor : adj.get(node)) {
            if (color[neighbor] == -1) {
                if (!dfs(neighbor, node, color, adj)) {
                    return false;
                }
            } else if (color[neighbor] == color[node]) {
                return false;
            }
        }

        return true;
    }

    public boolean isBipartite(ArrayList<ArrayList<Integer>> adj) {
//        return bfs(0, adj);
        int[] color = new int[adj.size()];
        Arrays.fill(color, -1);

        return dfs(0, 0, color, adj);
    }

    public boolean dfs(int node, int parent, int[] color, int[][] graph) {
        color[node] = 1 - color[parent];

        for (int neighbor : graph[node]) {
            if (color[neighbor] == -1) {
                if (!dfs(neighbor, node, color, graph)) {
                    return false;
                }
            } else if (color[neighbor] == color[node]) {
                return false;
            }
        }

        return true;
    }

    public boolean isBipartite(int[][] graph) {
        int[] color = new int[graph.length];
        Arrays.fill(color, -1);

        for (int i = 0; i < graph.length; i++) {
             if (color[i] == -1) {
                 if (!dfs(i, 0, color, graph)) {
                     return false;
                 }
             }
        }

        return true;
    }

    class Dijkstra {

        class iPair {
            int first, second;

            iPair(int first, int second) {
                this.first = first;
                this.second = second;
            }
        }

        public ArrayList<Integer> dijkstra(ArrayList<ArrayList<iPair>> adj, int src) {
            ArrayList<Integer> result = new ArrayList<>();
            Queue<iPair> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.first));
            minHeap.add(new iPair(0, src));
            int[] minDistance = new int[adj.size()];
            Arrays.fill(minDistance, Integer.MAX_VALUE);
            minDistance[src] = 0;

            while (!minHeap.isEmpty()) {
                iPair iPair = minHeap.poll();
                int u = iPair.second;
                int distance = iPair.first;

                if (minDistance[u] < distance) {
                    continue;
                }

                for (iPair neighbor : adj.get(u)) {
                    int v = neighbor.first;
                    int weight = neighbor.second;

                    if (minDistance[v] > minDistance[u] + weight) {
                        minDistance[v] = minDistance[u] + weight;
                        minHeap.add(new iPair(minDistance[v], v));
                    }
                }
            }

            for (int i : minDistance) {
                result.add(i);
            }
            return result;
        }
    }

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

    public List<List<Integer>> printGraph(int V, int edges[][]) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        return adjList;
    }

    public void dfs(int node, boolean[] visited,
                    ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> result) {
        visited[node] = true;
        result.add(node);
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited, adj, result);
            }
        }
     }

    public ArrayList<Integer> dfsOfGraph(ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[adj.size()];
        dfs(0, visited, adj, result);

        return result;
    }

    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[V];
        queue.add(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            int v = queue.poll();
            result.add(v);

            for (int neighbor : adj.get(v)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        return result;
    }

    public void dfs(int node, Stack<Integer> stack, List<Integer>[] adjList, boolean[] visited) {
        visited[node] = true;

        for (int neighbor : adjList[node]) {
            if (!visited[neighbor]) {
                dfs(neighbor, stack, adjList, visited);
            }
        }

        stack.push(node);
    }

    public int findChampion(int n, int[][] edges) {
        int[] inDegree = new int[n];

        for (int[] edge : edges) {
            inDegree[edge[1]]++;
        }

        int cnt = 0;
        int champion = -1;
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                champion = i;
                cnt++;
            }
            if (cnt > 1) {
                return -1;
            }
        }

        return champion;
    }

    public int slidingPuzzle(int[][] board) {


        return 1;
    }

    public static void main(String[] args) {

    }
}