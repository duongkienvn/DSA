package com.dsa.homework.sixlab.ex1;

public class UnsortedLinkedPriorityQueue<K extends Comparable<K>, E> implements PriorityQueueInterface<K, E> {
    protected class NodeEntry<K, E> implements Entry<K, E> {
        private K key;
        private E element;
        private NodeEntry<K, E> next;

        public NodeEntry(K k, E e) {
            key = k;
            element = e;
            next = null;
        }

        public K getKey() {
            return key;
        }

        public E getValue() {
            return element;
        }
    }

    private NodeEntry<K, E> head;
    private int n = 0;

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void insert(Entry<K, E> entry) {
        NodeEntry<K, E> newNode = new NodeEntry<>(entry.getKey(), entry.getValue());
        newNode.next = head;
        head = newNode;
        n++;
    }

    public void insert(K k, E e) {
        insert(new NodeEntry<>(k, e));
    }

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

    public Entry<K, E> removeMin() {
        if (isEmpty()) return null;
        NodeEntry<K, E> minNode = head;
        NodeEntry<K, E> current = head;
        NodeEntry<K, E> prev = null;
        NodeEntry<K, E> prevMin = null;

        while (current != null) {
            if (current.getKey().compareTo(minNode.getKey()) < 0) {
                minNode = current;
                prevMin = prev;
            }
            prev = current;
            current = current.next;
        }

        if (prevMin == null) {
            head = minNode.next;
        } else {
            prevMin.next = minNode.next;
        }
        n--;
        return minNode;
    }
}

