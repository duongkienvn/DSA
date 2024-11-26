package com.dsa.tree;

import java.util.*;
import java.util.stream.Collectors;

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

    private int preorderIndex = 0;
    private Map<Integer, Integer> inorderIndexMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return helperTree(preorder, 0, inorder.length - 1);
    }

    private TreeNode helperTree(int[] preorder, int inorderLeft, int inorderRight) {
        if (inorderLeft > inorderRight) {
            return null;
        }

        int rootVal = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootVal);

        int inorderIndex = inorderIndexMap.get(rootVal);
        root.left = helperTree(preorder, inorderLeft, inorderIndex - 1);
        root.right = helperTree(preorder, inorderIndex + 1, inorderRight);

        return root;
    }

    private int postorderIndex = -1;

    public TreeNode buildTreeII(int[] inorder, int[] postorder) {
        postorderIndex = postorder.length - 1;
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return helperTreeII(0, inorder.length - 1, postorder);
    }

    private TreeNode helperTreeII(int inorderLeft, int inorderRight, int[] postorder) {
        if (inorderLeft > inorderRight) {
            return null;
        }

        int rootVal = postorder[postorderIndex--];
        TreeNode root = new TreeNode(rootVal);

        int inorderIndex = inorderIndexMap.get(rootVal);
        root.right = helperTreeII(inorderIndex + 1, inorderRight, postorder);
        root.left = helperTreeII(inorderLeft, inorderIndex - 1, postorder);

        return root;
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

        public Node() {
        }

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

    class BSTIterator {
        private Queue<TreeNode> queue;

        private void traversal(TreeNode root) {
            if (root.left != null) {
                traversal(root.left);
            }
            queue.add(root);
            if (root.right != null) {
                traversal(root.right);
            }
        }

        public BSTIterator(TreeNode root) {
            queue = new LinkedList<>();
            traversal(root);
        }

        public int next() {
            return queue.poll().val;
        }

        public boolean hasNext() {
            return !queue.isEmpty();
        }
    }

    class BSTIteratorII {
        private Stack<TreeNode> stack;

        public BSTIteratorII(TreeNode root) {
            stack = new Stack<>();
            pushLeft(root);
        }

        public int next() {
            TreeNode node = stack.pop();
            if (node.right != null) {
                pushLeft(node.right);
            }

            return node.val;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        private void pushLeft(TreeNode root) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }
    }

    public void countFrequency(TreeNode root, Map<Integer, Integer> frequency) {
        if (root == null) {
            return;
        }

        frequency.put(root.val, frequency.getOrDefault(root.val, 0) + 1);
        countFrequency(root.left, frequency);
        countFrequency(root.right, frequency);
    }

    public int[] findMode(TreeNode root) {
        if (root == null) {
            return null;
        }

        List<Integer> modes = new ArrayList<>();
        Map<Integer, Integer> frequency = new HashMap<>();
        countFrequency(root, frequency);
        int maxFreq = frequency.values().stream().mapToInt(freq -> freq).max().orElse(Integer.MIN_VALUE);

        //        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
//            if (entry.getValue() == maxFreq) {
//                modes.add(entry.getKey());
//            }
//        }

        modes = frequency.entrySet().stream().
                filter(entry -> entry.getValue() == maxFreq).
                map(Map.Entry::getKey).
                collect(Collectors.toList());

        return modes.stream().mapToInt(Integer::intValue).toArray();
    }

    private int totalTitle = 0;

    public int sumOfTreeNode(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftSum = sumOfTreeNode(root.left);
        int rightSum = sumOfTreeNode(root.right);

        int title = Math.abs(leftSum - rightSum);
        totalTitle += title;

        return leftSum + rightSum + root.val;
    }

    public int findTilt(TreeNode root) {
        sumOfTreeNode(root);
        return totalTitle;
    }

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        TreeNode newTree = new TreeNode(root1.val + root2.val);
        newTree.left = mergeTrees(root1.left, root2.left);
        newTree.right = mergeTrees(root1.right, root2.right);

        return newTree;
    }

    public void leafSequence(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            list.add(root.val);
            return;
        }
        leafSequence(root.left, list);
        leafSequence(root.right, list);
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leafSequenceRoot1 = new ArrayList<>();
        leafSequence(root1, leafSequenceRoot1);
        List<Integer> leafSequenceRoot2 = new ArrayList<>();
        leafSequence(root2, leafSequenceRoot2);

        return leafSequenceRoot1.equals(leafSequenceRoot2);
    }

    private int countSubTree = 0;

    public int[] countAverageNode(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }

        int[] left = countAverageNode(root.left);
        int[] right = countAverageNode(root.right);

        int sum = left[0] + right[0] + root.val;
        int count = left[1] + right[1] + 1;

        if (root.val == sum / count) {
            countSubTree++;
        }

        return new int[]{sum, count};
    }

    public int averageOfSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        countAverageNode(root);
        return countSubTree;
    }

    public boolean isIncreasingOrDecreasing(TreeNode left, TreeNode right, int level) {
        if (level % 2 == 0) {
            if (left.val >= right.val) {
                return false;
            }
        } else {
            if (left.val <= right.val) {
                return false;
            }
        }
        return true;
    }

    public boolean isLevel(TreeNode node, int level) {
        if (level % 2 == 0) {
            if (node.val % 2 == 0) {
                return false;
            }
        } else {
            if (node.val % 2 == 1) {
                return false;
            }
        }

        return true;
    }

    public boolean isEvenOddTree(TreeNode root) {
        if (root.val % 2 == 0) {
            return false;
        }

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        int level = -1;
        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                TreeNode leftNode = null;
                TreeNode rightNode = null;

                if (current.left != null) {
                    leftNode = current.left;
                    queue.add(current.left);
                }
                if (current.right != null) {
                    rightNode = current.right;
                    queue.add(current.right);
                }
                if (leftNode != null && rightNode != null) {
                    if (!isLevel(leftNode, level) || !isLevel(rightNode, level)) {
                        return false;
                    }
                    if (!isIncreasingOrDecreasing(leftNode, rightNode, level)) {
                        return false;
                    }
                }
                if (leftNode != null || rightNode != null) {
                    TreeNode temp = queue.peekLast();
                    if (leftNode != null) {
                        if (!isLevel(leftNode, level)) {
                            return false;
                        }
                        if (!isIncreasingOrDecreasing(temp, leftNode, level)) {
                            return false;
                        }
                    }
                    if (rightNode != null) {
                        if (!isLevel(rightNode, level)) {
                            return false;
                        }
                        if (!isIncreasingOrDecreasing(temp, rightNode, level)) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public long kthLargestLevelSum(TreeNode root, int k) {
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Long> priQueue = new PriorityQueue<>(Comparator.reverseOrder());

        queue.add(root);
        priQueue.add((long) root.val);
        while (!queue.isEmpty()) {
            int size = queue.size();
            long sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                if (current.left != null) {
                    queue.add(current.left);
                    sum += current.left.val;

                }
                if (current.right != null) {
                    queue.add(current.right);
                    sum += current.right.val;
                }
            }
            priQueue.add(sum);
            if (priQueue.size() > k) {
                priQueue.poll();
            }
        }

        return priQueue.size() < k ? -1 : priQueue.poll();
    }

    class Node1 {
        TreeNode key;
        int row;
        int col;

        public Node1(TreeNode key, int row, int col) {
            this.key = key;
            this.row = row;
            this.col = col;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        Map<Integer, List<Node1>> map = new TreeMap<>();
        Queue<Node1> queue = new LinkedList<>();
        Node1 node1 = new Node1(root, 0, 0);
        queue.add(node1);
        map.computeIfAbsent(node1.col, k -> new ArrayList<>()).add(node1);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Node1 current = queue.poll();
                TreeNode node = current.key;

                if (node.left != null) {
                    int row = current.row + 1;
                    int col = current.col - 1;
                    Node1 temp = new Node1(node.left, row, col);
                    queue.add(temp);
                    map.computeIfAbsent(temp.col, k -> new ArrayList<>()).add(temp);
                }
                if (node.right != null) {
                    int row = current.row + 1;
                    int col = current.col + 1;
                    Node1 temp = new Node1(node.right, row, col);
                    queue.add(temp);
                    map.computeIfAbsent(temp.col, k -> new ArrayList<>()).add(temp);
                }
            }
        }

        for (List<Node1> nodeList : map.values()) {
            Collections.sort(nodeList, new Comparator<Node1>() {
                @Override
                public int compare(Node1 o1, Node1 o2) {
                    if (o1.col == o2.col && o1.row == o2.row) {
                        return o1.key.val - o2.key.val;
                    }
                    return 0;
                }
            });
            List<Integer> list1 = nodeList.stream().
                    map(node -> node.key.val).collect(Collectors.toList());
            list.add(list1);
        }

        return list;
    }

    public int heightOfTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int heightLeft = heightOfTree(root.left);
        int heighttRight = heightOfTree(root.right);

        return Math.max(heightLeft, heighttRight) + 1;
    }

    public TreeNode findNode(TreeNode root, int value) {
        if (root == null || (root.left == null && root.right == null)) {
            return null;
        }

        if ((root.left != null && root.left.val == value)
                || (root.right != null && root.right.val == value)) {
            return root;
        }

        TreeNode leftResult = findNode(root.left, value);
        if (leftResult != null) {
            return leftResult;
        }
        return findNode(root.right, value);
    }

    public TreeNode cloneTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode newNode = new TreeNode(root.val);

        newNode.left = cloneTree(root.left);
        newNode.right = cloneTree(root.right);

        return newNode;
    }

    public int heightTreeExcluding(TreeNode root, int exclude) {
        if (root == null || root.val == exclude) {
            return -1;
        }
        int leftHeight = heightTreeExcluding(root.left, exclude);
        int rightHeight = heightTreeExcluding(root.right, exclude);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public int[] treeQueries(TreeNode root, int[] queries) {
        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            result[i] = heightTreeExcluding(root, queries[i]);
        }

        return result;
    }

    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode node = root;
        while (true) {
            if (root != null && val < root.val) {
                root = root.left;
            } else if (root != null && val > root.val) {
                root = root.right;
            }
            if (root == null) {
                return null;
            }
            if (root.val == val) {
                return root;
            }
        }
    }

    public TreeNode searchBSTRecur(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        if (val < root.val) {
            return searchBST(root.left, val);
        } else if (val > root.val) {
            return searchBST(root.right, val);
        }

        return root;
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode node = root;
        while (true) {
            if (root == null) {
                root = new TreeNode(val);
                return root;
            }

            if (node.val > val) {
                if (node.left == null) {
                    node.left = new TreeNode(val);
                    return root;
                }
                node = node.left;
            } else {
                if (node.right == null) {
                    node.right = new TreeNode(val);
                    return root;
                }
                node = node.right;
            }
        }
    }

    public TreeNode insertIntoBSTRecur(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        } else if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        }

        return root;
    }

    public TreeNode lowestCommonAncestorII(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestorII(root.left, p, q);
        TreeNode right = lowestCommonAncestorII(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        return left != null ? left : right;
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        Stack<List<Integer>> stack = new Stack<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        stack.add(List.of(root.val));

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                if (current.left != null) {
                    queue.add(current.left);
                    list.add(current.left.val);
                }
                if (current.right != null) {
                    queue.add(current.right);
                    list.add(current.right.val);
                }
            }
            if (!list.isEmpty()) {
                stack.push(list);
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> list = new ArrayList<>();

        while (!queue.isEmpty()) {
            int size = queue.size();
            list.add(queue.peek().val);

            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();

                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
        }

        return list.get(list.size() - 1);
    }

    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        result.add(root.val);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int maxValue = Integer.MIN_VALUE;
            boolean check = false;

            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                if (current.left != null) {
                    queue.add(current.left);
                    maxValue = Math.max(current.left.val, maxValue);
                    check = true;
                }
                if (current.right != null) {
                    queue.add(current.right);
                    maxValue = Math.max(maxValue, current.right.val);
                    check = true;
                }
            }
            if (check) {
                result.add(maxValue);
            }
        }

        return result;
    }

    public void convertToList(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        convertToList(root.left, list);
        convertToList(root.right, list);
    }

    public int getMinimumDifference(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        convertToList(root, list);
        Collections.sort(list);
        int minDiff = Integer.MAX_VALUE;

        for (int i = 1; i < list.size(); i++) {
            minDiff = Math.min(list.get(i) - list.get(i - 1), minDiff);
        }

        return minDiff;
    }

    public int sumOfNode(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int sumLeft = sumOfNode(root.left);
        int sumRight = sumOfNode(root.right);

        return root.val + sumLeft + sumRight;
    }

    public TreeNode buildBST(TreeNode root, TreeNode parent, TreeNode newTreeNode) {
        if (root == null) {
            return null;
        }

        int sum = sumOfNode(root.right);
        TreeNode newTree = null;
        if (newTreeNode != null && parent != null && root.val < parent.val) {
            newTree = new TreeNode(newTreeNode.val + sum + root.val);
        } else {
            newTree = new TreeNode(sum + root.val);
        }

        newTree.left = buildBST(root.left, root, newTree);
        newTree.right = buildBST(root.right, root, null);

        return newTree;
    }

    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);

            sum += root.val;
            root.val = sum;

            convertBST(root.left);
        }

        return root;
    }

    private int resultII = 0;

    public void countPath(TreeNode root, int sum, int targetSum) {
        if (root == null) {
            return;
        }

        if (sum == targetSum) {
            resultII++;
            return;
        }

        sum += root.val;
        countPath(root.left, sum, targetSum);
        countPath(root.right, sum, targetSum);
    }

    public void traversalII(TreeNode root, int sum, int targetSum) {
        if (root == null) {
            return;
        }

        countPath(root.left, sum, targetSum);
        countPath(root.right, sum, targetSum);
    }

    public int pathSumII(TreeNode root, int targetSum) {
        traversalII(root, 0, targetSum);
        return resultII;
    }

    public void inOrder(TreeNode root, TreeSet<Integer> set) {
        if (root == null) {
            return;
        }
        set.add(root.val);
        if (set.size() > 2) {
            set.pollFirst();
        }
        inOrder(root.left, set);
        inOrder(root.right, set);
    }

    public int findSecondMinimumValue(TreeNode root) {
        TreeSet<Integer> set = new TreeSet<>(Comparator.reverseOrder());

        inOrder(root, set);
        if (set.size() < 2) {
            return -1;
        }
        return set.getFirst();
    }

    private List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> result = new ArrayList<>();

        if (start > end) {
            result.add(null);
            return result;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = generateTrees(start, i - 1);
            List<TreeNode> rightTrees = generateTrees(i + 1, end);

            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }

        return result;
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }

        return generateTrees(1, n);
    }

    public int maxSubArray(List<Integer> list) {
        int length = list.size();
        int maxSum = 0;
        int sum = 0;
        boolean hasPositive = false;

        for (int i = 0; i < length; i++) {
            if (list.get(i) > 0) {
                hasPositive = true;
            }
            sum += list.get(i);
            if (sum < 0) {
                sum = 0;
            }
            maxSum = Math.max(sum, maxSum);
        }

        if (!hasPositive) {
            return list.stream().max(Comparator.comparingInt(k -> k)).get();
        }

        return maxSum;
    }

    private int maxSum = Integer.MIN_VALUE;

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftMax = Math.max(dfs(root.left), 0);
        int rightMax = Math.max(dfs(root.right), 0);

        int currentSum = root.val + leftMax + rightMax;
        maxSum = Math.max(maxSum, currentSum);

        return root.val + Math.max(leftMax, rightMax);
    }

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    private int longestPath = Integer.MIN_VALUE;

    public int longestPath(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftMax = longestPath(root.left);
        int rightMax = longestPath(root.right);

        int leftPath = 0, rightPath = 0;
        if (root.left != null && root.left.val == root.val) {
            leftPath = leftMax + 1;
        }
        if (root.right != null && root.right.val == root.val) {
            rightPath = rightMax + 1;
        }

        longestPath = Math.max(longestPath, leftPath + rightPath);

        return Math.max(leftPath, rightPath);
    }

    public int longestUnivaluePath(TreeNode root) {
        longestPath(root);

        return longestPath;
    }

    public void inorder(List<Integer> list, TreeNode root) {
        if (root == null) {
            return;
        }

        inorder(list, root.left);
        list.add(root.val);
        inorder(list, root.right);
    }

    public int rob(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(list, root);
        int size = list.size();

        int[] dp = new int[size];
        if (size == 0) return 0;
        if (size == 1) return list.get(0);

        dp[0] = list.get(0);


        return 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

    }
}
