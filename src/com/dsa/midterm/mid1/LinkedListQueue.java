package com.dsa.midterm.mid1;

@SuppressWarnings("unchecked")
public class LinkedListQueue<T> implements QueueInterface<T> {

    class Node {
        T element;
        Node next;
    }

    private Node head, tail;
    int n;

    public LinkedListQueue() {
        head = tail = null;
        n = 0;
    }

    @Override
    public void enqueue(T element) {
        // TODO Auto-generated method stub
        Node newNode = new Node();
        newNode.element = element;
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        n++;
    }

    @Override
    public T dequeue() {
        // TODO Auto-generated method stub
        if (isEmpty()) {
            return null;
        }
        Node current = head;
        head = head.next;
        n--;
        return current.element;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return n == 0;
    }
}

