package com.dsa.homework.secondlab.exercisethree;

public class SimpleLinkedList<T> {
    class Node {
        T data;
        Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node top = null;
    private Node bot = null;
    private int n = 0;

    // Thêm phần tử ở đầu danh sách
    public void add(T data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            top = bot = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
        n++;
    }

    public void addBot(T data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            top = bot = newNode;
        } else {
            bot.next = newNode;
            bot = newNode;
        }
        n++;
    }

    public T get(int i) {
        if (i < 0 || i >= n) {
            throw new IndexOutOfBoundsException();
        }
        Node current = top;
        for (int j = 0; j < i; j++) {
            current = current.next;
        }
        return current.data;
    }

    public void set(int i, T data) {
        if (i < 0 || i >= n) {
            throw new IndexOutOfBoundsException();
        }
        Node current = top;
        for (int j = 0; j < i; j++) {
            current = current.next;
        }
        current.data = data;
    }

    public boolean isContain(T data) {
        Node current = top;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public T removeTop() {
        if (isEmpty()) {
            return null;
        }
        T value = top.data;
        top = top.next;
        n--;
        if (isEmpty()) {
            bot = null;
        }

        return value;
    }

    public T removeBot() {
        if (isEmpty()) {
            return null;
        }

        if (n == 1) {
            removeTop();
        }

        T value = bot.data;
        Node current = top;
        while (current.next != bot) {
            current = current.next;
        }
        bot = current;
        bot.next = null;
        n--;

        return value;
    }

    // Xóa phần tử đầu tiên có giá trị bằng data
    public void remove(T data) {
        if (isEmpty()) {
            return;
        }
        if (top.data.equals(data)) {
            removeTop();
            return;
        }

        Node current = top;
        while (current.next != null && !current.next.data.equals(data)) {
            current = current.next;
        }
        if (current.next.next == null) {
            bot = current;
            removeBot();
            return;
        } else {
            current.next = current.next.next;
            n--;
        }
    }
}
