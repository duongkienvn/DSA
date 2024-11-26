package com.dsa.homework.revise.heap;

public class UnsortedLinkedPriorityQueue <K extends Comparable<K>, E> implements PriorityQueueInterface<K, E> {
    protected class NodeEntry<K, E> implements Entry<K, E> {
        private K key;
        private E element;
        private NodeEntry<K, E> next;

        public NodeEntry(K k, E e) {
            key = k;
            element = e;
            next = null;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public E getValue() {
            return element;
        }
    }

    private NodeEntry<K, E> head;
    private int n = 0;

    public UnsortedLinkedPriorityQueue() {
        head = null;
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
    public void insert(Entry<K, E> entry) {
        NodeEntry<K, E> newNode = (NodeEntry<K, E>) entry;
        newNode.next = head;
        head = newNode;
        n++;
    }

    @Override
    public void insert(K k, E e) {
        insert(new NodeEntry<>(k, e));
    }

    @Override
    public Entry<K, E> removeMin() {
        NodeEntry<K, E> preMin = null;
        NodeEntry<K, E> current = head;
        NodeEntry<K, E> min = head;

        while (current != null) {
            if (min.getKey().compareTo(current.getKey()) > 0) {
                min = current;
            }
            current = current.next;
        }

        while (current != null) {
            if (min.getKey().compareTo(current.getKey()) == 0) {
                NodeEntry<K, E> nextNode = current.next;
                preMin.next = nextNode;
            }
            preMin = current;
            current = current.next;
        }

        return min;
    }

    @Override
    public Entry<K, E> min() {
        if (isEmpty()) return null;
        NodeEntry<K, E> minNode = head;
        NodeEntry<K, E> current = head;

        while (current != null) {
            if (current.getKey().compareTo(minNode.getKey()) < 0) {
                minNode = current;
            }
            current = current.next;
        }
        return minNode;
    }
}
