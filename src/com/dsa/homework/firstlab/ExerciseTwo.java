package com.dsa.homework.firstlab;

import javax.swing.*;
import java.util.Arrays;

public class ExerciseTwo <T extends Comparable<T>> {
    private static int swapCount = 0;
    private static int comparisonCount = 0;

    public void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        swapCount++;
    }

    public void bubbleSort(T[] arr) {
        swapCount = 0;
        comparisonCount = 0;
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                comparisonCount++;
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                }
            }
            System.out.println("After iteration " + (i + 1) + ": " + Arrays.toString(arr));
        }
        System.out.println("Comparisons: " + comparisonCount + ", Swaps: " + swapCount);
    }

    public void selectionSort(T[] arr) {
        swapCount = 0;
        comparisonCount = 0;
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                comparisonCount++;
                if (arr[minIndex].compareTo(arr[j]) > 0) {
                    minIndex = j;
                }
            }
            swap(arr, minIndex, i);
            System.out.println("After iteration " + (i + 1) + ": " + Arrays.toString(arr));
        }
        System.out.println("Comparisons: " + comparisonCount + ", Swaps: " + swapCount);
    }

    public void insertionSort(T[] arr) {
        swapCount = 0;
        comparisonCount = 0;
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            T key = arr[i];
            int idx = i - 1;
            while (idx >= 0 && key.compareTo(arr[idx]) < 0) {
                comparisonCount++;
                arr[idx + 1] = arr[idx];
                idx--;
            }
            arr[idx + 1] = key;
            swapCount++;
            System.out.println("After iteration " + i + ": " + Arrays.toString(arr));
        }
        System.out.println("Comparisons: " + comparisonCount + ", Swaps: " + swapCount);
    }

    public void mergeSort(T[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
            System.out.println("After merge: " + Arrays.toString(arr));
        }
    }

    public void merge(T arr[], int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        T[] Left = Arrays.copyOfRange(arr, left, mid + 1);
        T[] Right = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0, j = 0, k = 0;
        while (i < n1 && j < n2) {
            comparisonCount++;
            if (Left[i].compareTo(Right[j]) <= 0) {
                arr[left++] = Left[i++];
            } else {
                arr[left++] = Right[j++];
            }
        }

        while (i < n1) {
            arr[left++] = Left[i++];
        }
        while (j < n2) {
            arr[left++] = Right[j++];
        }
    }

    public void quickSortLomuto(T arr[], int left, int right) {
        if (left < right) {
            int p = lomutoPartition(arr, left, right);
            quickSortLomuto(arr, left, p - 1);
            quickSortLomuto(arr, p + 1, right);
            System.out.println("After partition: " + Arrays.toString(arr));
        }
    }

    public void quickSortHoare(T[] arr, int left, int right) {
        if (left < right) {
            int p = hoarePartition(arr, left, right);
            quickSortHoare(arr, left, p);
            quickSortHoare(arr, p + 1, right);
            System.out.println("After partition: " + Arrays.toString(arr));
        }
    }

    public int lomutoPartition(T[] arr, int left, int right) {
        T pivot = arr[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            comparisonCount++;
            if (arr[j].compareTo(pivot) <= 0) {
                i++;
                swap(arr, i, j);
            }
        }
        i++;
        swap(arr, i, right);

        return i;
    }

    public int hoarePartition(T[] arr, int left, int right) {
        T pivot = arr[left];
        int i = left - 1;
        int j = right + 1;

        while (true) {
            do {
                i++;
            } while (arr[i].compareTo(pivot) < 0);
            do {
                j--;
            } while (arr[j].compareTo(pivot) > 0);

            if (i < j) {
                swap(arr, i, j);
            } else {
                return j;
            }
        }
    }

    public void measureTime(T[] arr, String algorithm) {
        T[] temp = arr.clone();
        long startTime = System.nanoTime();

        switch (algorithm) {
            case "BubbleSort":
                bubbleSort(temp);
                break;
            case "SelectionSort":
                selectionSort(temp);
                break;
            case "InsertionSort":
                insertionSort(temp);
                break;
            case "MergeSort":
                comparisonCount = 0;
                swapCount = 0;
                mergeSort(temp, 0, temp.length - 1);
                System.out.println("Comparisons: " + comparisonCount);
                break;
            case "QuickSortLomuto":
                comparisonCount = 0;
                swapCount = 0;
                quickSortLomuto(temp, 0, temp.length - 1);
                System.out.println("Comparisons: " + comparisonCount + ", Swaps: " + swapCount);
                break;
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000;
        System.out.println(algorithm + " took " + duration + " ms");
    }

    public static void main(String[] args) {
        ExerciseTwo<Integer> sortAlgorithms = new ExerciseTwo<>();

        Integer[] arr = {64, 25, 12, 22, 11};
        System.out.println("Original array: " + Arrays.toString(arr));

        System.out.println("\nTesting Bubble Sort...");
        sortAlgorithms.measureTime(arr, "BubbleSort");

        System.out.println("\nTesting Selection Sort...");
        sortAlgorithms.measureTime(arr, "SelectionSort");

        System.out.println("\nTesting Insertion Sort...");
        sortAlgorithms.measureTime(arr, "InsertionSort");

        System.out.println("\nTesting Merge Sort...");
        sortAlgorithms.measureTime(arr, "MergeSort");

        System.out.println("\nTesting Quick Sort...");
        sortAlgorithms.measureTime(arr, "QuickSortLomuto");
    }
}
