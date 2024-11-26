package com.dsa;

import java.util.Iterator;
import java.util.*;
public class LinkedListStack<T> implements StackInterface<T> {

    class Node {
        T element;
        Node next;
    }

//    Node stack = null;
    Stack<T> stack = new Stack<>();

    @Override
    public void push(T element) {
        // TODO Auto-generated method stub
        stack.push(element);

    }

    @Override
    public T pop() {
        // TODO Auto-generated method stub
        return stack.pop();
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return stack.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return new StackIterator();
    }

    class StackIterator implements Iterator<T> {

        private Node currentNode = null;

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return currentNode != null;
        }

        @Override
        public T next() {
            // TODO Auto-generated method stub
            T data = currentNode.element;
            currentNode = currentNode.next;
            return data;
        }
    }

}
