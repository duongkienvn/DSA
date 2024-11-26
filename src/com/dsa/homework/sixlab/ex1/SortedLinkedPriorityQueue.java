package com.dsa.homework.sixlab.ex1;

public class SortedLinkedPriorityQueue<K extends Comparable<K>, E> implements PriorityQueueInterface<K, E> {
    protected class NodeEntry<K, E> implements Entry<K, E> {
        private K key;
        private E element;
        private NodeEntry<K, E> next;

        public NodeEntry(K k, E e) {
            key = k;
            element = e;
            next = null;
        }

        public K getKey() { return key; }
        public E getValue() { return element; }
    }

    private NodeEntry<K, E> head;
    private int n = 0;

    public int size() { return n; }
    public boolean isEmpty() { return n == 0; }

    public void insert(Entry<K, E> entry) {
        NodeEntry<K, E> newNode = new NodeEntry<>(entry.getKey(), entry.getValue());
        if (isEmpty() || head.getKey().compareTo(newNode.getKey()) > 0) {
            newNode.next = head;
            head = newNode;
        } else {
            NodeEntry<K, E> current = head;
            while (current.next != null && current.next.getKey().compareTo(newNode.getKey()) <= 0) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        n++;
    }

    public void insert(K k, E e) {
        insert(new NodeEntry<>(k, e));
    }

    public Entry<K, E> min() {
        if (isEmpty()) return null;
        return head;
    }

    public Entry<K, E> removeMin() {
        if (isEmpty()) return null;
        NodeEntry<K, E> minNode = head;
        head = head.next;
        n--;
        return minNode;
    }
}

