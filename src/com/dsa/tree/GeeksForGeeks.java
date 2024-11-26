package com.dsa.tree;

import java.util.*;

public class GeeksForGeeks {
    private class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    class Tree {
        public void recurDiagonal(Node root, int level, Map<Integer, ArrayList<Integer>> map) {
            if (root == null) {
                return;
            }
            map.computeIfAbsent(level, key -> new ArrayList<>()).add(root.data);
            recurDiagonal(root.left, level + 1, map);
            recurDiagonal(root.right, level, map);
        }

        public ArrayList<Integer> diagonal(Node root) {
            Map<Integer, ArrayList<Integer>> map = new HashMap<>();
            int level = 0;
            recurDiagonal(root, level, map);
            ArrayList<Integer> arrayList = new ArrayList<>();
            map.entrySet().forEach(entry -> arrayList.addAll(entry.getValue()));

            return arrayList;
        }

        public ArrayList<Integer> leftView(Node root) {
            if (root == null) {
                return new ArrayList<>();
            }

            ArrayList<Integer> arrayList = new ArrayList<>();
            Queue<Node> queue = new LinkedList<>();

            queue.add(root);
            arrayList.add(root.data);
            while (!queue.isEmpty()) {
                int size = queue.size();
                boolean check = false;
                int view = -1;

                for (int i = 0; i < size; i++) {
                    Node current = queue.poll();
                    if (current.left != null) {
                        queue.add(current.left);
                        if (view == -1) {
                            view = current.left.data;
                        }
                    }
                    if (current.right != null) {
                        queue.add(current.right);
                        if (view == -1) {
                            view = current.right.data;
                        }
                    }
                }
                if (view != -1) {
                    arrayList.add(view);
                }
            }

            return arrayList;
        }

        public static void reverse(ArrayList<Integer> list) {
            int left = 0, right = list.size() - 1;
            while (left < right) {
                int temp = list.get(left);
                list.set(left, list.get(right));
                list.set(right, temp);
                left++;
                right--;
            }
        }

        static class Pair {
            Node node;
            int hd;

            public Pair(Node node, int hd) {
                this.node = node;
                this.hd = hd;
            }
        }

        public static ArrayList<Integer> topView(Node root) {
            ArrayList<Integer> result = new ArrayList<>();
            Queue<Pair> queue = new LinkedList<>();
            Map<Integer, Integer> topViewMap = new TreeMap<>();
            queue.add(new Pair(root, 0));

            while (!queue.isEmpty()) {
                Pair pair = queue.poll();
                Node node = pair.node;
                int hd = pair.hd;

                if (!topViewMap.containsKey(hd)) {
                    topViewMap.put(hd, node.data);
                }

                if (node.left != null) {
                    queue.add(new Pair(node.left, hd - 1));
                }
                if (node.right != null) {
                    queue.add(new Pair(node.right, hd + 1));
                }
            }

            topViewMap.entrySet().forEach(entry -> result.add(entry.getValue()));
            return result;
        }

        public int height(Node node) {
            if (node == null) {
                return 0;
            }
            int heigthLeft = height(node.left);
            int heightRight = height(node.right);

            return Math.max(heigthLeft, heightRight) + 1;
        }

        public int minOperations(int[] arr, int n, int k) {
            Queue<Integer> minHeap = new PriorityQueue<>();
            for (int i : arr) {
                minHeap.add(i);
            }
            if (minHeap.peek() >= k) {
                return 0;
            }

            int operation = 0;
            while (minHeap.size() > 1) {
                int num1 = minHeap.poll();
                if (num1 >= k) {
                    break;
                }
                int num2 = minHeap.poll();
                if (num1 < k || num2 < k) {
                    operation++;
                }
                int sum = num2 + num1;
                minHeap.add(sum);
            }

            if (!minHeap.isEmpty() && minHeap.peek() < k) {
                return -1;
            }

            return operation;
        }

        public static int heapHeight(int n, int arr[]) {
            int index = 0;
            int height = -1;
            while (n > 0) {
                int level = (int) Math.pow(2, index);
                n -= level;
                index++;
                height++;
            }

            return height;
        }

        public int[] mergeHeaps(int[] a, int[] b, int n, int m) {

            return null;
        }

        public static int sumBT(Node head) {
            if (head == null) {
                return 0;
            }

            int sumLeft = sumBT(head.left);
            int sumRight = sumBT(head.right);

            return head.data + sumLeft + sumRight;
        }

        public void permutation(StringBuilder current, String s, List<String> result, boolean visited[]) {
            if (current.length() == s.length()) {
                if (!result.contains(current.toString())) {
                    result.add(new String(current));
                }
                return;
            }

            for (int i = 0; i < s.length(); i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    current.append(s.charAt(i));
                    permutation(current, s, result, visited);
                    current.deleteCharAt(current.length() - 1);
                    visited[i] = false;
                }
            }

        }

        public List<String> findPermutation(String s) {
            List<String> result = new ArrayList<>();
            boolean visited[] = new boolean[s.length()];
            permutation(new StringBuilder(), s, result, visited);
            Collections.sort(result);
            return result;
        }

        private int leafLevel = -1;

        public boolean check(Node root) {
            return checkLevel(root, 0);
        }

        private boolean checkLevel(Node root, int level) {
            if (root == null) {
                return true;
            }

            if (root.left == null && root.right == null) {
                if (leafLevel == -1) {
                    leafLevel = level;
                }
                return leafLevel == level;
            }

            return checkLevel(root.left, level + 1)
                    && checkLevel(root.right, level + 1);
        }

        public ArrayList<String> huffmanCodes(String S, int f[], int N) {
            ArrayList<String> result = new ArrayList<>();


            return result;
        }
    }
}
