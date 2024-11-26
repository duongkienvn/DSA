package com.dsa.homework.sixlab.ex2;

import com.dsa.homework.sixlab.ex1.Entry;
import com.dsa.homework.sixlab.ex1.SortedArrayPriorityQueue;

public class MinHeapPriorityQueue<K extends Comparable<K>, E> extends SortedArrayPriorityQueue<K, E> {
    private ArrEntry<K, E>[] heapPQ;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 1000;

    public MinHeapPriorityQueue() {
        heapPQ = new ArrEntry[DEFAULT_CAPACITY];
    }

    private void swap(int i, int j) {
        ArrEntry<K, E> temp = heapPQ[i];
        heapPQ[i] = heapPQ[j];
        heapPQ[j] = temp;
    }

    protected void upHeap() {
        int i = size - 1;
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (heapPQ[i].getKey().compareTo(heapPQ[parent].getKey()) >= 0) break;
            swap(i, parent);
            i = parent;
        }
    }

    protected void downHeap() {
        int i = 0;
        while (i < size / 2) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int smallerChild = (right < size && heapPQ[right].getKey().compareTo(heapPQ[left].getKey()) < 0) ? right : left;
            if (heapPQ[i].getKey().compareTo(heapPQ[smallerChild].getKey()) <= 0) break;
            swap(i, smallerChild);
            i = smallerChild;
        }
    }

    @Override
    public void insert(K k, E e) {
        if (size == heapPQ.length) throw new IllegalStateException("Heap is full");
        heapPQ[size] = new ArrEntry(k, e);
        size++;
        upHeap();
    }

    @Override
    public Entry<K, E> removeMin() {
        if (isEmpty()) return null;
        Entry<K, E> min = heapPQ[0];
        heapPQ[0] = heapPQ[--size];
        downHeap();
        return min;
    }
}

