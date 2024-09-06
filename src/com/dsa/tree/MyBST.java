package com.dsa.tree;

public class MyBST {
    private TreeNode root;

    public MyBST() {

    }

    public TreeNode insertNode(TreeNode root, int val) {
        TreeNode newNode = new TreeNode(val);
        if (root == null) {
            root = new TreeNode(val);
            return root;
        } else {
            TreeNode temp = root;
            while (true) {
                if (val < temp.val) {
                    if (temp.left == null) {
                        temp.left = newNode;
                        break;
                    } else {
                        temp = temp.left;
                    }
                } else {
                    if (temp.right == null) {
                        temp.right = newNode;
                        break;
                    } else {
                        temp = temp.right;
                    }
                }
            }

            return root;
        }
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (val < root.val) {
            if (root.left == null) {
                root.left = new TreeNode(val);
            } else {
                root.left = insertIntoBST(root.left, val);
            }
        } else {
            if (root.right == null) {
                root.right = new TreeNode(val);
            } else {
                root.right = insertIntoBST(root.right, val);
            }
        }

        return root;
    }

    public TreeNode findLeftMostNode(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode leftMostNode = root;
        while (leftMostNode.left != null) {
            leftMostNode = leftMostNode.left;
        }

        return leftMostNode;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            }
            if (root.left != null && root.right == null) {
                return root.left;
            }
            if (root.left == null && root.right != null) {
                return root.right;
            }

            TreeNode leftMostNode = findLeftMostNode(root.right);
            root.val = leftMostNode.val;
            root.right = deleteNode(root.right, leftMostNode.val);
        }

        return root;
    }

    public TreeNode searchBST(TreeNode root, int val) {
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

    // Node - Left - Right
    public static void preOrder(TreeNode currentNode) {
        if (currentNode == null) {
            return;
        }
        System.out.print(currentNode.val + " ");
        preOrder(currentNode.left);
        preOrder(currentNode.right);
    }

    // Left - Node - Right
    public static void inOrder(TreeNode currentNode) {
        if (currentNode == null) {
            return;
        }

        preOrder(currentNode.left);
        System.out.println(currentNode.val);
        preOrder(currentNode.right);
    }

    // Left - Right - Node
    public static void postOrder(TreeNode currentNode) {
        if (currentNode == null) {
            return;
        }

        postOrder(currentNode.left);
        postOrder(currentNode.right);
        System.out.println(currentNode.val);
    }

    public static void main(String[] args) {
        TreeNode n0 = new TreeNode(0);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);

        n0.left = n1; n0.right = n2;
        n1.left = n3; n1.right = n4;
        n2.right = n5;
        preOrder(n0);
        System.out.println("\nDone");
    }
}
