package com.dsa.homework.revise.heap;

public class SortedLinkedPriorityQueue <K extends Comparable<K>, E> implements PriorityQueueInterface<K, E> {
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
        if (head == null || newNode.getKey().compareTo(head.getKey()) < 0) {
            newNode.next = head;
            head = newNode;
        } else {
            NodeEntry<K, E> current = head;
            while (current.next != null && current.next.getKey().compareTo(newNode.getKey()) < 0) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        n++;
    }

    @Override
    public void insert(K k, E e) {
        insert(new NodeEntry<>(k, e));
    }

    @Override
    public Entry<K, E> removeMin() {
        if (isEmpty()) {
            return null;
        }
        NodeEntry<K, E> min = head;
        head = head.next;
        n--;
        return min;
    }

    @Override
    public Entry<K, E> min() {
        return head;
    }
}
