package com.dsa.homework.fifthlab.tree;

public class ArrayBinaryTree<E> implements BinaryTreeInterface<Integer> {
    private E[] array;
    private int n = 0;
    private int defaultsize = 100;

    public ArrayBinaryTree() {
        array = (E[]) new Object[defaultsize];
    }

    public void setRoot(E element) {
        if (n == 0) {
            array[1] = element;
            n++;
        }
    }

    public void setLeft(int p, E element) {
        int leftIndex = 2 * p;
        if (leftIndex < array.length) {
            array[leftIndex] = element;
            n++;
        }
    }

    public void setRight(int p, E element) {
        int rightIndex = 2 * p + 1;
        if (rightIndex < array.length) {
            array[rightIndex] = element;
            n++;
        }
    }

    @Override
    public Integer root() {
        return n == 0 ? null : 1;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int numChildren(Integer p) {
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
    public Integer parent(Integer p) {
        if (p > 1) {
            return p / 2;
        }
        return null;
    }

    @Override
    public Integer left(Integer p) {
        int leftIndex = p * 2;
        return leftIndex < array.length && array[leftIndex] != null ? leftIndex : null;
    }

    @Override
    public Integer right(Integer p) {
        int rightIndex = p * 2 + 1;
        return rightIndex < array.length && array[rightIndex] != null ? rightIndex : null;
    }

    @Override
    public Integer sibling(Integer p) {
        if (p % 2 == 0) {
            return right(p / 2);
        }
        return left(p / 2);
    }
}