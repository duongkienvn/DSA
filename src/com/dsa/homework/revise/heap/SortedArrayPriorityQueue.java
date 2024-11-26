package com.dsa.homework.revise.heap;

import java.util.Arrays;
import java.util.List;

public class SortedArrayPriorityQueue <K extends Comparable<K>, E> implements PriorityQueueInterface<K, E> {
    protected class ArrEntry<K, E> implements Entry<K, E> {
        K key;
        E element;

        public ArrEntry(K k, E e) {
            key = k;
            element = e;
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

    private ArrEntry<K, E>[] array;
    int n = 0;
    private final int defaultSize = 1000;

    public SortedArrayPriorityQueue() {
        array = new ArrEntry[1000];
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
        if (n == array.length) {
            throw new IllegalStateException("Priority queue is full");
        }
        int i = n - 1;
        while (i >= 0 && entry.getKey().compareTo(array[i].getKey()) > 0) {
            array[i + 1] = array[i];
            i--;
        }

        array[i + 1] = (ArrEntry<K, E>) entry;
        n++;
    }

    @Override
    public void insert(K k, E e) {
        insert(new ArrEntry<>(k, e));
    }

    @Override
    public Entry<K, E> removeMin() {
        if (isEmpty()) {
            return null;
        }
        return array[--n];
    }

    @Override
    public Entry<K, E> min() {
        if (isEmpty()) {
            return null;
        }
        return array[n - 1];
    }
}
