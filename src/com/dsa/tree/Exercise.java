package com.dsa.tree;

import com.sun.source.tree.Tree;

import java.util.*;

public class Exercise {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        ;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    List<Integer> result = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return result;
        }
        result.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);

        return result;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return result;
        }

        inorderTraversal(root.left);
        result.add(root.val);
        inorderTraversal(root.right);

        return result;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return result;
        }

        postorderTraversal(root.left);
        postorderTraversal(root.right);
        result.add(root.val);

        return result;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftTreeHeight = maxDepth(root.left);
        int rightTreeHeight = maxDepth(root.right);

        int result = Math.max(leftTreeHeight, rightTreeHeight) + 1;

        return result;
    }

    public boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    public boolean traversal(TreeNode curNode, int curSum, int target) {
        if (curNode == null) {
            return false;
        }

        curSum += curNode.val;
        if (isLeaf(curNode)) {
            return curSum == target;
        }

        boolean leftTreeResult = traversal(curNode.left, curSum, target);
        boolean rightTreeResult = traversal(curNode.right, curSum, target);

        return leftTreeResult || rightTreeResult;
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        return traversal(root, 0, targetSum);
    }

    private boolean isValidBST(TreeNode node, Integer lower, Integer upper) {
        if (node == null) {
            return true;
        }

        int val = node.val;
        if (lower != null && val < lower) {
            return false;
        }
        if (upper != null && val > upper) {
            return false;
        }

        if (!isValidBST(node.left, lower, val)) {
            return false;
        }
        if (!isValidBST(node.right, val, upper)) {
            return false;
        }

        return true;
    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        return (p.val == q.val
                && isSameTree(p.left, q.left)
                && isSameTree(p.right, q.right));
    }

    int count = 1;

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        if (root.left != null) {
            count++;
            countNodes(root.left);
        }
        if (root.right != null) {
            count++;
            countNodes(root.right);
        }

        return count;
    }

    private int checkHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = checkHeight(node.left);
        if (leftHeight == -1) {
            return -1;
        }

        int rightHeight = checkHeight(node.right);
        if (rightHeight == -1) {
            return -1;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }

        if (t1 == null || t2 == null) {
            return false;
        }

        return t1.val == t2.val
                && isMirror(t1.left, t2.right)
                && isMirror(t1.right, t2.left);
    }

    public boolean isSymmetricRecur(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isMirror(root.left, root.right);
    }

    public boolean isSymmetricIter(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);

        while (!queue.isEmpty()) {
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();

            if (t1 == null && t2 == null) {
                continue;
            }

            if (t1 == null || t2 == null || t1.val != t2.val) {
                return false;
            }

            queue.add(t1.left);
            queue.add(t2.right);
            queue.add(t1.right);
            queue.add(t2.left);
        }

        return true;
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeigtht = minDepth(root.left);
        int rightHeight = minDepth(root.right);

        int result = Math.min(leftHeigtht, rightHeight) + 1;

        return result >= 2 ? result : Math.max(leftHeigtht, rightHeight) + 1;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        System.out.println();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            result.add(currentLevel);
        }

        return result;
    }

    public TreeNode invertTreeRecursion(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTreeRecursion(root.left);
        invertTreeRecursion(root.right);

        return root;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            TreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;

            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }

        return root;
    }

    public int maxDepthIter(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            depth++;

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
        }

        return depth;
    }

    private int maxDiameter = 0;

    private int calulateDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftDepth = calulateDepth(node.left);
        int rightDepth = calulateDepth(node.right);

        maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);

        return Math.max(leftDepth, rightDepth) + 1;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        calulateDepth(root);
        return maxDiameter;
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }

        if (isSameTree(root, subRoot)) {
            return true;
        }

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode current = root;
        while (current != null) {
            if (current.val < p.val && current.val < q.val) {
                current = current.right;
            } else if (current.val > p.val && current.val > q.val) {
                current = current.left;
            } else {
                return current;
            }
        }

        return null;
    }

    public int goodNodes(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE);
    }

    private int dfs(TreeNode node, int maxVal) {
        if (node == null) {
            return 0;
        }

        int goodNodesCount = 0;
        if (node.val >= maxVal) {
            goodNodesCount = 1;
        }

        maxVal = Math.max(maxVal, node.val);

        goodNodesCount += dfs(node.left, maxVal);
        goodNodesCount += dfs(node.right, maxVal);

        return goodNodesCount;
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        result.add(root.val);
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            int size = deque.size();

            for (int i = 0; i < size; i++) {
                TreeNode current = deque.pollFirst();
                if (current.left != null) {
                    deque.add(current.left);
                }
                if (current.right != null) {
                    deque.add(current.right);
                }
            }

            if (!deque.isEmpty()) {
                result.add(deque.peekLast().val);
            }
        }

        return result;
    }

    Queue<Integer> queue = new PriorityQueue<>();

    public void traversal(TreeNode root) {
        if (root == null) {
            return;
        }

        traversal(root.left);
        queue.add(root.val);
        traversal(root.right);
    }


    public int kthSmallestBad(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        traversal(root);

        List<Integer> list = new ArrayList<>(queue);

        return list.get(k - 1);
    }

    int cnt = 0;
    int answer = 0;

    public void inOrderTraversal(TreeNode node, int k) {
        if (node == null) {
            return;
        }

        inOrderTraversal(node.left, k);

        count++;

        if (count == k) {
            answer = node.val;
            return;
        }

        inOrderTraversal(node.right, k);
    }

    public int kthSmallest(TreeNode root, int k) {
        inOrderTraversal(root, k);
        return answer;
    }

    public void backtrack(List<List<Integer>> result, int targetSum, List<Integer> current,
                          TreeNode root, int sum) {
        if (root == null) {
            return;
        }

        sum += root.val;
        current.add(root.val);
        if (sum == targetSum && root.left == null && root.right == null) {
            result.add(new ArrayList<>(current));
        }

        backtrack(result, targetSum, current, root.left, sum);
        backtrack(result, targetSum, current, root.right, sum);
        current.remove(current.size() - 1);
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, targetSum, new ArrayList<>(), root, 0);
        return result;
    }

    private TreeNode helper(int nums[], int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(nums[mid]);

        node.left = helper(nums, left, mid - 1);
        node.right = helper(nums, mid + 1, right);

        return node;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public TreeNode helper(List<Integer> list, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(list.get(mid));

        node.left = helper(list, left, mid - 1);
        node.right = helper(list, mid + 1, right);

        return node;
    }

    public TreeNode sortedListToBSTC1(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        return helper(list, 0, list.size() - 1);
    }

    public int getSize(ListNode head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }

        return size;
    }

    private ListNode current;

    public TreeNode buildTree(int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = start + (end - start) / 2;
        TreeNode leftChild = buildTree(start, mid - 1);

        TreeNode root = new TreeNode(current.val);
        current = current.next;

        TreeNode rightChild = buildTree(mid + 1, end);

        root.left = leftChild;
        root.right = rightChild;

        return root;
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        int size = getSize(head);
        current = head;

        return buildTree(0, size - 1);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {


        return null;
    }

    private Queue<TreeNode> treeNodeQueue = new LinkedList<>();

    public void convertToList(TreeNode root) {
        if (root == null) {
            return;
        }

        treeNodeQueue.add(root);
        convertToList(root.left);
        convertToList(root.right);
    }

    public void flatten(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }

        TreeNode current = root;
        convertToList(current);
        root.left = null;
        root.right = null;

        while (!treeNodeQueue.isEmpty()) {
            root.left = null;
            root.right = treeNodeQueue.poll();
            root = root.right;
        }
    }

    private TreeNode first = null;
    private TreeNode second = null;
    private TreeNode prev = new TreeNode(Integer.MIN_VALUE);

    public void recoverTree(TreeNode root) {
        inorderTraversall(root);

        if (first != null && second != null) {
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }

    public void inorderTraversall(TreeNode root) {
        if (root == null) {
            return;
        }

        inorderTraversall(root.left);

        if (prev.val > root.val) {
            if (first == null) {
                first = prev;
            }

            second = root;
        }

        prev = root;

        inorderTraversall(root.right);
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Node current = root;

        Queue<Node> queue = new LinkedList<>();
        queue.add(current);

        while (!queue.isEmpty()) {
            int size = queue.size();
            Node prevNode = null;

            for (int i = 0; i < size; i++) {
                Node currentNode = queue.poll();

                if (prevNode != null) {
                    prevNode.next = currentNode;
                }
                prevNode = currentNode;

                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }

            prevNode.next = null;
        }

        return root;
    }

    public void dfs(List<String> result, String path, TreeNode root) {
        if (root == null) {
            return;
        }

        path += String.valueOf(root.val);

        if (root.left == null && root.right == null) {
            result.add(path);
        } else {
            path += "->";
            dfs(result, path, root.left);
            dfs(result, path, root.right);
        }
    }

    public int backtrack(TreeNode node, int currentSum) {
        if (node == null) {
            return 0;
        }

        currentSum = currentSum * 10 + node.val;
        if (node.left == null && node.right == null) {
            return currentSum;
        }

        int leftSum = backtrack(node.left, currentSum);
        int rightSum = backtrack(node.right, currentSum);

        return leftSum + rightSum;
    }

    public int sumNumbers(TreeNode root) {
        return backtrack(root, 0);
    }

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();
        dfs(result, "", root);

        return result;
    }

    public void dfs(List<String> result, StringBuilder current, TreeNode root) {
        if (root == null) {
            return;
        }

        current.append((char) (root.val + 'a'));

        if (root.left == null && root.right == null) {
            result.add(new StringBuilder(current).reverse().toString());
        } else {
            dfs(result, current, root.left);
            dfs(result, current, root.right);
        }

        current.deleteCharAt(current.length() - 1);
    }

    public String smallestFromLeaf(TreeNode root) {
        List<String> result = new ArrayList<>();
        dfs(result, new StringBuilder(), root);

        Collections.sort(result);
        return result.get(0);
    }

    public boolean dfs(ListNode head, TreeNode node) {
        if (head == null) {
            return true;
        }

        if (node == null) {
            return false;
        }

        if (head.val == node.val) {
            return dfs(head.next, node.left) || dfs(head.next, node.right);
        }

        return false;
    }

    public boolean isSubPath(ListNode head, TreeNode root) {
        if (root == null) {
            return false;
        }

        return dfs(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(root, 1));
        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            int minIndex = queue.peek().getValue();
            int firstIndex = 0, lastIndex = 0;

            for (int i = 0; i < size; i++) {
                Pair<TreeNode, Integer> current = queue.poll();
                TreeNode node = current.key;
                int index = current.value - minIndex;

                if (i == 0) {
                    firstIndex = index;
                }
                if (i == size - 1) {
                    lastIndex = index;
                }

                if (node.left != null) {
                    queue.add(new Pair<>(node.left, 2 * index));
                }
                if (node.right != null) {
                    queue.add(new Pair<>(node.right, 2 * index + 1));
                }
            }

            maxWidth = Math.max(maxWidth, lastIndex - firstIndex + 1);
        }

        return maxWidth;
    }

    public TreeNode lowestCommonAncestorII(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        

        return null;
    }

    public static void main(String[] args) {
        System.out.println((char) (25 + 'a'));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(null);
        System.out.println(queue);
    }
}
