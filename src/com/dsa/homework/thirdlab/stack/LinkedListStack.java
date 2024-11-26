package com.dsa.homework.thirdlab.stack;

import java.util.Iterator;

public class LinkedListStack<E> implements StackInterface<E> {
    private class Node {
        E element;
        Node next;

        public Node(E element) {
            this.element = element;
            this.next = null;
        }
    }

    private Node stack = null;

    @Override
    public void push(E element) {
        Node newNode = new Node(element);
        newNode.next = stack;
        stack = newNode;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty!");
        }
        E value = stack.element;
        stack = stack.next;
        return value;
    }

    @Override
    public boolean isEmpty() {
        return stack == null;
    }

    @Override
    public E top() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty!");
        }
        return stack.element;
    }

    @Override
    public Iterator<E> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<E> {
        private Node currentNode = stack;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new IllegalStateException("No more elements in stack");
            }
            E value = currentNode.element;
            currentNode = currentNode.next;
            return value;
        }
    }
}
