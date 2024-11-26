package com.dsa.homework.thirdlab.stack;

import java.util.Iterator;

public class ArrayStack<E> implements StackInterface<E> {
    private E[] stack;
    private int top;
    private static final int INITIAL_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public ArrayStack() {
        stack = (E[]) new Object[INITIAL_CAPACITY];
        top = -1;
    }

    @Override
    public void push(E element) {
        if (top + 1 == stack.length) {
            enlarge();
        }
        stack[++top] = element;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty!");
        }
        E value = stack[top];
        stack[top--] = null;
        return value;
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public E top() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty!");
        }
        return stack[top];
    }

    public void enlarge() {
        @SuppressWarnings("unchecked")
        E[] newArr = (E[]) new Object[stack.length * 2];
        System.arraycopy(stack, 0, newArr, 0, stack.length);
        stack = newArr;
    }

    @Override
    public Iterator iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<E> {
        private int currentIndex = top;

        @Override
        public boolean hasNext() {
            return currentIndex >= 0;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException();
            }
            return stack[currentIndex--];
        }
    }
}
