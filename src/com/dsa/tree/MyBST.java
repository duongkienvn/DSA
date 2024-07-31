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
}
