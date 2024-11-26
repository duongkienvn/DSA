package com.dsa.homework.revise.heap;

public class MinHeapPriorityQueue <K extends Comparable<K>, E> extends SortedArrayPriorityQueue<K, E> {
    private ArrEntry<K, E>[] heapPQ;
    private int size;

    public MinHeapPriorityQueue(int capacity) {
        heapPQ = new ArrEntry[capacity];
        size = 0;
    }

    @Override
    public void insert(Entry<K, E> entry) {
        if (size == heapPQ.length) {
            throw new IllegalStateException("Heap is full");
        }
        heapPQ[size] = (ArrEntry<K, E>) entry;
        upHeap(size);
        size++;
    }

    @Override
    public void insert(K k, E e) {
        insert(new ArrEntry<>(k, e));
    }

    private void upHeap(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heapPQ[index].getKey().compareTo(heapPQ[parent].getKey()) >= 0) {
                break;
            }
            swap(index, parent);
            index = parent;
        }
    }

    private void downHeap(int index) {
        while (2 * index + 1 < size) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int smallChild = leftChild;

            if (rightChild < size && heapPQ[rightChild].getKey().compareTo(heapPQ[smallChild].getKey()) < 0) {
                smallChild = rightChild;
            }
            if (heapPQ[smallChild].getKey().compareTo(heapPQ[index].getKey()) >= 0) {
                break;
            }
            swap(smallChild, index);
            index = smallChild;
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Entry<K, E> removeMin() {
        if (size == 0) {
            return null;
        }
        ArrEntry<K, E> min = heapPQ[0];
        heapPQ[0] = heapPQ[--size];
        downHeap(0);
        return min;
    }

    private void swap(int i, int j) {
        ArrEntry<K, E> temp = heapPQ[i];
        heapPQ[i] = heapPQ[j];
        heapPQ[j] = temp;
    }

    @Override
    public Entry<K, E> min() {
        if (isEmpty()) {
            return null;
        }
        return heapPQ[0];
    }
}
