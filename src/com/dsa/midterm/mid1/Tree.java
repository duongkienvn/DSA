package com.dsa.midterm.mid1;

@SuppressWarnings("unchecked")
public class Tree<T> {

    class Node {
        T data;
        Node left;
        Node right;
    }

    void travel(Node tree) {
        LinkedListQueue<Node> queue = new LinkedListQueue<>();
        queue.enqueue(tree);

        while (!queue.isEmpty()) {
            Node current = queue.dequeue();
            System.out.print(current.data + " ");
            if (current.left != null) {
                queue.enqueue(current.left);
            }
            if (current.right != null) {
                queue.enqueue(current.right);
            }
        }
    }

}
