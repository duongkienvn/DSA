package com.dsa.heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MyHeap {
    private static final int MAX_SIZE = 100;
    private int[] arr = new int[MAX_SIZE + 1];
    private int size;

    MyHeap() {
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int peek() {
        if (isEmpty()) {
            return -1;
        }
        return arr[1];
    }

    private void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void add(int value) {
        size++;
        arr[size] = value;

        // heapify up
        int curIndex = size;
        int parentIndex = curIndex / 2;
        while (parentIndex != 0 && arr[curIndex] < arr[parentIndex]) {
            swap(parentIndex, curIndex);
            curIndex = parentIndex;
            parentIndex = curIndex / 2;
        }
    }

    private void heapifyDown(int curIndex) {
        while (curIndex * 2 < size) {
            int leftChildIndex = curIndex * 2;
            int smallerChildIndex = leftChildIndex;
            int rightChildIndex = leftChildIndex + 1;
            if (arr[leftChildIndex] > arr[rightChildIndex]) {
                smallerChildIndex = rightChildIndex;
            }
            if (arr[smallerChildIndex] < arr[curIndex]) {
                swap(smallerChildIndex, curIndex);
                curIndex = smallerChildIndex;
            } else {
                break;
            }
        }
    }

    public int poll() {
        if (isEmpty()) {
            return -1;
        }

        // heapify down
        int root = arr[1];
        arr[1] = arr[size];
        size--;
        heapifyDown(1);

        return root;
    }

    public void remove(int value) {
        int curIndex = -1;
        for (int i = 1; i < size; i++) {
            if (arr[i] == value) {
                curIndex = i;
            }
        }

        if (curIndex == -1) {
            return;
        }

        arr[curIndex] = arr[size];
        size--;
        heapifyDown(curIndex);
    }

    public static void main(String[] args) {
        Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.add(10);
        maxHeap.add(50);
        maxHeap.add(4);
        maxHeap.add(6);
        maxHeap.add(100);

        while (!maxHeap.isEmpty()) {
            System.out.println(maxHeap.poll());
        }
    }
}
