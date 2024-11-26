package com.dsa.homework.thirdlab.queue;

import javax.swing.plaf.IconUIResource;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<E> implements QueueInterface<E> {
    private class Node {
        E element;
        Node next;

        public Node(E element) {
            this.element = element;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size = 0;

    @Override
    public void enqueue(E element) {
        Node newNode = new Node(element);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        size++;
        tail = newNode;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        E element = head.element;
        head = head.next;
        size--;
        if (isEmpty()) {
            tail = null;
        }
        return element;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    private class LinkedQueueIterator implements Iterator<E> {
        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E element = current.element;
            current = current.next;
            return element;
        }
    }
}
