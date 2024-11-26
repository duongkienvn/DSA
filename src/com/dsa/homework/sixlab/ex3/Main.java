package com.dsa.homework.sixlab.ex3;

import com.dsa.homework.sixlab.ex2.MinHeapPriorityQueue;

public class Main {
    public static <K extends Comparable<K>> void heapSort(K[] array) {
        MinHeapPriorityQueue<K, K> heap = new MinHeapPriorityQueue<>();
        for (K item : array) heap.insert(item, item);

        for (int i = 0; i < array.length; i++) {
            array[i] = heap.removeMin().getKey();
        }
    }

    // Bubble Sort
    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap array[j] and array[j+1]
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // Insertion Sort
    public static void insertionSort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;

            // Move elements that are greater than key to one position ahead
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    // Selection Sort
    public static void selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the first element
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    // Quick Sort
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);  // Sort elements before partition
            quickSort(array, pi + 1, high); // Sort elements after partition
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1); // Index of smaller element

        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                // Swap array[i] and array[j]
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // Swap array[i+1] and array[high] (or pivot)
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    // Merge Sort
    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;

            // Sort first and second halves
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);

            // Merge the sorted halves
            merge(array, left, middle, right);
        }
    }

    private static void merge(int[] array, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[middle + 1 + j];
        }

        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copy the remaining elements of leftArray
        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        // Copy the remaining elements of rightArray
        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }


    public static void compareSortingAlgorithms(int[] array) {
        int[] bubbleArray = array.clone();
        int[] insertionArray = array.clone();
        int[] selectionArray = array.clone();
        int[] heapArray = array.clone();
        int[] quickArray = array.clone();
        int[] mergeArray = array.clone();

        long startTime, endTime;

        startTime = System.nanoTime();
        bubbleSort(bubbleArray);
        endTime = System.nanoTime();
        System.out.println("BubbleSort Time: " + (endTime - startTime));

        startTime = System.nanoTime();
        insertionSort(insertionArray);
        endTime = System.nanoTime();
        System.out.println("InsertionSort Time: " + (endTime - startTime));

        startTime = System.nanoTime();
        selectionSort(selectionArray);
        endTime = System.nanoTime();
        System.out.println("SelectionSort Time: " + (endTime - startTime));

        startTime = System.nanoTime();
//        heapSort(heapArray);
        endTime = System.nanoTime();
        System.out.println("HeapSort Time: " + (endTime - startTime));

        startTime = System.nanoTime();
        quickSort(quickArray, 0, quickArray.length - 1);
        endTime = System.nanoTime();
        System.out.println("QuickSort Time: " + (endTime - startTime));

        startTime = System.nanoTime();
        mergeSort(mergeArray, 0, mergeArray.length - 1);
        endTime = System.nanoTime();
        System.out.println("MergeSort Time: " + (endTime - startTime));
    }

}
