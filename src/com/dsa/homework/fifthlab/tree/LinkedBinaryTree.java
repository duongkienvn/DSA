package com.dsa.homework.fifthlab.tree;

public class LinkedBinaryTree<E> implements BinaryTreeInterface<LinkedBinaryTree.Node<E>> {
    protected static class Node<E> {
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        public Node(E element, Node<E> parent, Node<E> left, Node<E> right) {
            this.element = element;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }
    }

    private Node<E> root;
    private int size;

    public LinkedBinaryTree() {
        root = null;
        size = 0;
    }

    public Node<E> addRoot(E element) {
        if (isEmpty()) {
            root = new Node<>(element, null, null, null);
            size++;
            return root;
        }
        throw new IllegalStateException("Tree is not empty!");
    }

    public Node<E> addLeft(Node<E> p, E element) {
        if (p.left != null) {
            throw new IllegalArgumentException();
        }
        Node<E> leftChild = new Node<>(element, p, null, null);
        p.left = leftChild;
        size++;
        return leftChild;
    }

    public Node<E> addRight(Node<E> p, E element) {
        if (p.right != null) {
            throw new IllegalArgumentException();
        }
        Node<E> rightChild = new Node<>(element, p, null, null);
        p.right = rightChild;
        size++;
        return rightChild;
    }

    @Override
    public Node<E> root() {
        return root;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int numChildren(Node<E> p) {
        int count = 0;
        if (left(p) != null) {
            count++;
        }
        if (right(p) != null) {
            count++;
        }
        return count;
    }

    @Override
    public Node<E> parent(Node<E> p) {
        return p.parent;
    }

    @Override
    public Node<E> left(Node<E> p) {
        return p.left;
    }

    @Override
    public Node<E> right(Node<E> p) {
        return p.right;
    }

    @Override
    public Node<E> sibling(Node<E> p) {
        Node<E> parent = p.parent;
        if (parent == null) {
            return null;
        }
        if (p == parent.left) {
            return parent.right;
        }
        return parent.left;
    }
}
