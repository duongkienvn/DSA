package com.dsa.homework.revise.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

public class UnsortedArrayPriorityQueue<K extends Comparable<K>, E> implements PriorityQueueInterface<K, E> {
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
    private int n = 0;
    private int defaultSize = 1000;

    public UnsortedArrayPriorityQueue() {
        array = new ArrEntry[defaultSize];
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
        array[n++] = (ArrEntry<K, E>) entry;
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
        int minIndex = 0;
        for (int i = 1; i < n; i++) {
            if (array[i].getKey().compareTo(array[minIndex].getKey()) < 0) {
                minIndex = i;
            }
        }
        Entry<K, E> minValue = array[minIndex];
        array[minIndex] = array[--n];
        return minValue;
    }

    @Override
    public Entry<K, E> min() {
        if (isEmpty()) {
            return null;
        }
        int minIndex = 0;
        for (int i = 1; i < n; i++) {
            if (array[i].getKey().compareTo(array[minIndex].getKey()) < 0) {
                minIndex = i;
            }
        }

        return array[minIndex];
    }
}
