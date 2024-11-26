package com.dsa.homework.fifthlab.tree;

import java.util.Arrays;

public class TestExpressionTree {
    public static void preorderPrint(BinaryTreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value + " ");
        preorderPrint(node.left);
        preorderPrint(node.right);
    }

    public static void main(String[] args) {
        ExpressionTree<String> tree = new ExpressionTree<>();

        // Tạo cây nhị phân biểu thức ((6 / 3) + 2) * (7 - 4)
        LinkedBinaryTree.Node<String> root = tree.addRoot("*");
        LinkedBinaryTree.Node<String> left = tree.addLeft(root, "+");
        LinkedBinaryTree.Node<String> right = tree.addRight(root, "-");

        tree.addLeft(left, "/");
        tree.addRight(left, "2");
        tree.addLeft(tree.left(left), "6");
        tree.addRight(tree.left(left), "3");

        tree.addLeft(right, "7");
        tree.addRight(right, "4");

        System.out.println("Prefix (Tiền tố): ");
        tree.preorderPrint(root); // * + / 6 3 2 - 7 4
        System.out.println();

        System.out.println("Infix (Trung tố): ");
        tree.inorderPrint(root);  // ((6 / 3) + 2) * (7 - 4)
        System.out.println();

        System.out.println("Postfix (Hậu tố): ");
        tree.postorderPrint(root); // 6 3 / 2 + 7 4 - *
        System.out.println();

        System.out.println("Giá trị biểu thức: " + tree.evaluate(root)); // Kết quả: 12.0

        //
        String[] tokens = {"(", "6", "/", "3", "+", "2", ")", "*", "(", "7", "-", "4", ")"};
//        System.out.println(Arrays.toString(tokens));
        String[] postfix = tree.infixToPostfix(tokens);

        ExpressionTree tree2 = new ExpressionTree();
        BinaryTreeNode root2 = tree2.constructExpressionTree(postfix);
        preorderPrint(root2);

        // Evaluate expression tree


    }
}

