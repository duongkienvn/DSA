package com.dsa.homework.fifthlab.tree;

import java.io.FileWriter;
import java.io.IOException;

public class BinaryTreePrinter {
    public static <T> void printTree(BinaryTreeInterface<T> tree, T root, FileWriter writer, String prefix, boolean isLeft) throws IOException {
        if (root != null) {
            writer.write(prefix + (isLeft ? "├── " : "└── ") + root.toString() + "\n");

            T left = tree.left(root);
            T right = tree.right(root);

            if (left != null || right != null) {
                printTree(tree, left, writer, prefix + (isLeft ? "│   " : "    "), true);
                printTree(tree, right, writer, prefix + (isLeft ? "│   " : "    "), false);
            }
        }
    }

    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("output.txt")) {
            // Tạo cây nhị phân bằng ArrayBinaryTree hoặc LinkedBinaryTree
            ArrayBinaryTree<String> tree = new ArrayBinaryTree<>();
            tree.setRoot("A");
            tree.setLeft(1, "B");
            tree.setRight(1, "C");
            tree.setLeft(2, "D");
            tree.setRight(2, "E");

            // In cây ra file
            printTree(tree, 1, writer, "", false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

