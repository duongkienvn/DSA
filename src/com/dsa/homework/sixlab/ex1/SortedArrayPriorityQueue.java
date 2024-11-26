package com.dsa.homework.sixlab.ex1;

import java.util.ArrayList;

public class SortedArrayPriorityQueue<K extends Comparable<K>, E> implements PriorityQueueInterface<K, E> {
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

    public int size() { return array.size(); }
    public boolean isEmpty() { return array.isEmpty(); }

    public void insert(Entry<K, E> entry) {
        int i = 0;
        while (i < array.size() && array.get(i).getKey().compareTo(entry.getKey()) < 0) {
            i++;
        }
        array.add(i, (ArrEntry<K, E>) entry);
    }

    public void insert(K k, E e) {
        insert(new ArrEntry<>(k, e));
    }

    public Entry<K, E> min() {
        if (isEmpty()) return null;
        return array.get(0);
    }

    public Entry<K, E> removeMin() {
        if (isEmpty()) return null;
        return array.remove(0);
    }
}
