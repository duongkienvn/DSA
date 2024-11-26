package com.dsa.homework.sixlab.ex1;

import java.util.ArrayList;

public class UnsortedArrayPriorityQueue<K extends Comparable<K>, E> implements PriorityQueueInterface<K, E> {
    protected class ArrEntry<K, E> implements Entry<K, E> {
        K key;
        E element;

        public ArrEntry(K k, E e) {
            key = k;
            element = e;
        }

        public K getKey() { return key; }
        public E getValue() { return element; }
    }

    private ArrayList<ArrEntry<K, E>> array = new ArrayList<>();
    private int n = 0;

    public int size() { return n; }
    public boolean isEmpty() { return n == 0; }

    public void insert(Entry<K, E> entry) {
        array.add((ArrEntry<K, E>) entry);
        n++;
    }

    public void insert(K k, E e) {
        array.add(new ArrEntry<>(k, e));
        n++;
    }

    public Entry<K, E> min() {
        if (isEmpty()) return null;
        ArrEntry<K, E> minEntry = array.get(0);
        for (ArrEntry<K, E> entry : array) {
            if (entry.getKey().compareTo(minEntry.getKey()) < 0) {
                minEntry = entry;
            }
        }
        return minEntry;
    }

    public Entry<K, E> removeMin() {
        if (isEmpty()) return null;
        Entry<K, E> minEntry = min();
        array.remove(minEntry);
        n--;
        return minEntry;
    }
}

