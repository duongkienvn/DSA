package com.dsa.homework.thirdlab.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayQueue<E> implements QueueInterface<E> {
    private E[] queue;
    private int n = 0;
    private int top = 0;
    private int count = 0;
    private final static int DEFAULT_SIZE = 100;

    public ArrayQueue(int capacity) {
        this.n = capacity;
        queue = (E[]) new Object[capacity];
    }

    public ArrayQueue() {
        n = DEFAULT_SIZE;
        queue = (E[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void enqueue(E element) {
        if (count == n) {
            throw new IllegalStateException("Queue is full");
        }
        int end = (top + count) % n;
        queue[end] = element;
        count++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        E element = queue[top];
        queue[top] = null;
        top = (top + 1) % n;
        count--;

        return element;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public Iterator iterator() {
        return new ArrayQueueIterator();
    }

    private class ArrayQueueIterator implements Iterator<E> {
        private int current = top;
        private int num = 0;

        @Override
        public boolean hasNext() {
            return num < count;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E data = queue[(current + num) % n];
            num++;
            return data;
        }
    }
}
