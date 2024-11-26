package com.dsa.queue;

import java.util.ArrayDeque;

public class MyCircularDeque {
    private ArrayDeque<Integer> deque;
    private int size;

    public MyCircularDeque(int k) {
        deque = new ArrayDeque<>();
        size = k;
    }

    public boolean insertFront(int value) {
        if (deque.size() == size) {
            return false;
        }
        deque.addFirst(value);
        return true;
    }

    public boolean insertLast(int value) {
        if (deque.size() == size) {
            return false;
        }
        deque.addLast(value);
        return true;
    }

    public boolean deleteFront() {
        if (deque.isEmpty()) {
            return false;
        }
        deque.pollFirst();
        return true;
    }

    public boolean deleteLast() {
        if (deque.isEmpty()) {
            return false;
        }
        deque.pollLast();
        return true;
    }

    public int getFront() {
        if (deque.isEmpty()) {
            return -1;
        }
        return deque.peekFirst();
    }

    public int getRear() {
        if (deque.isEmpty()) {
            return -1;
        }
        return deque.peekLast();
    }

    public boolean isEmpty() {
        return deque.isEmpty();
    }

    public boolean isFull() {
        return deque.size() == size;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */
