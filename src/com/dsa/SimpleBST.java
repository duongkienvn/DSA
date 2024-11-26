package com.dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("unchecked")
public class SimpleBST<Key extends Comparable<Key>> implements SimpleBTreeInterface<Key> {

    class Node {
        Key data;
        Node left, right;

        public Node(Key key) {
            this.data = key;
        }
    }

    private Node root = null;
    int n = 0;

    @Override
    public void insert(Key k) {
        if (root == null) {
            root = new Node(k);
            n++;
            return;
        }

        Node node = root;
        while (true) {
            int cmp = k.compareTo(node.data);

            if (cmp < 0) {
                if (node.left == null) {
                    node.left = new Node(k);
                    n++;
                    break;
                }
                node = node.left;
            } else if (cmp >= 0) {
                if (node.right == null) {
                    node.right = new Node(k);
                    n++;
                    break;
                }
                node = node.right;
            }
        }
    }


    @Override
    public Key search(Key k) {
        // TODO Auto-generated method stub
        Node node = root;
        while (true) {
            int cmp = k.compareTo(node.data);

            if (node != null && cmp < 0) {
                node = node.left;
            } else if (node != null && cmp > 0) {
                node = node.right;
            }
            if (node == null) {
                return null;
            }
            if (cmp == 0) {
                return node.data;
            }
        }
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return n;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return n == 0;
    }

    @Override
    public Iterator<Key> iterator() {
        // TODO Auto-generated method stub
        List<Key> elements = new ArrayList<>();
        inOrder(root, elements);
        return elements.iterator();
    }

    public void inOrder(Node node, List<Key> elements) {
        if (node == null) {
            return;
        }
        inOrder(node.left, elements);
        elements.add(node.data);
        inOrder(node.right, elements);
    }

    //duyệt cây theo thứ tự trước (tiền thứ tự)
    public void preOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.println(root.data);
        preOrder(root.left);
        preOrder(root.right);
    }

    public void preTraverse() {
        preOrder(root);
    }

    // duyệt cây theo thứ tự sau (hậu thứ tự)
    public void postOrder(Node root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.data);
    }

    public void postTraverse() {
        postOrder(root);
    }


    // duyệt cây theo thứ tự giữa (trung thứ tự)
    public void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.println(root.data);
        inOrder(root.right);
    }

    public void inTraverse() {
        inOrder(root);
    }


    public static void main(String[] args) {
        SimpleBST<Integer> bst = new SimpleBST<>();

        int[] data = {5, 6, 7, 1, 2, 3, 8, 6, 9, 0};
        for (int i = 0; i < data.length; i++)
            bst.insert(data[i]);

        System.out.println("All element in tree:");
        int[] t = new int[data.length];
        int id = 0;
        for (int d : bst) {
            t[id] = d;
            id++;
        }

        Arrays.sort(t);
        for (int d : t) {
            System.out.print(d + " ");
        }

        System.out.println("");
        System.out.println("Size of tree = " + bst.size());

        System.out.println("Search key = 4> " + bst.search(4));
        System.out.println("Search key = 6> " + bst.search(6));

        System.out.println("Pre-order tree traversal");
        bst.preTraverse();
        System.out.println("Post-order tree traversal");
        bst.postTraverse();
        System.out.println("In-order tree traversal");
        bst.inTraverse();

    }


}
