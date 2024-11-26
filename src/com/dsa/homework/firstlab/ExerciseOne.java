package com.dsa.homework.firstlab;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ExerciseOne {
    private static int swapCount = 0;
    private static int comparisonCount = 0;

    public static int[] generateRandomArray(int n) {
        Random random = new Random();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt((int) Math.pow(10, 5)) + 1;
        }

        return arr;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        swapCount++;
    }

    public static void bubbleSort(int[] arr) {
        swapCount = 0;
        comparisonCount = 0;
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                comparisonCount++;
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
            System.out.println("After iteration " + (i + 1) + ": " + Arrays.toString(arr));
        }
        System.out.println("Comparisons: " + comparisonCount + ", Swaps: " + swapCount);
    }

    public static void selectionSort(int[] arr) {
        swapCount = 0;
        comparisonCount = 0;
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                comparisonCount++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, minIndex, i);
            System.out.println("After iteration " + (i + 1) + ": " + Arrays.toString(arr));
        }
        System.out.println("Comparisons: " + comparisonCount + ", Swaps: " + swapCount);
    }

    public static void insertionSort(int[] arr) {
        swapCount = 0;
        comparisonCount = 0;
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int idx = i - 1;
            while (idx >= 0 && key < arr[idx]) {
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

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
            System.out.println("After merge: " + Arrays.toString(arr));
        }
    }

    public static void merge(int arr[], int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] Left = new int[n1];
        int[] Right = new int[n2];

        for (int i = 0; i < n1; i++) {
            Left[i] = arr[left + i];
        }
        for (int i = 0; i < n2; i++) {
            Right[i] = arr[mid + 1 + i];
        }

        int i = 0, j = 0;
        while (i < n1 && j < n2) {
            comparisonCount++;
            if (Left[i] <= Right[j]) {
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

    public static void quickSortLomuto(int arr[], int left, int right) {
        if (left < right) {
            int p = lomutoPartition(arr, left, right);
            quickSortLomuto(arr, left, p - 1);
            quickSortLomuto(arr, p + 1, right);
            System.out.println("After partition: " + Arrays.toString(arr));
        }
    }

    public static void quickSortHoare(int[] arr, int left, int right) {
        if (left < right) {
            int p = hoarePartition(arr, left, right);
            quickSortHoare(arr, left, p);
            quickSortHoare(arr, p + 1, right);
            System.out.println("After partition: " + Arrays.toString(arr));
        }
    }

    public static int lomutoPartition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            comparisonCount++;
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        i++;
        swap(arr, i, right);

        return i;
    }

    public static int hoarePartition(int[] arr, int left, int right) {
        int pivot = arr[left];
        int i = left - 1;
        int j = right + 1;

        while (true) {
            do {
                i++;
            } while (arr[i] < pivot);
            do {
                j--;
            } while (arr[j] > pivot);

            if (i < j) {
                swap(arr, i, j);
            } else {
                return j;
            }
        }
    }

    public static void measureTime(int[] arr, String algorithm) {
        int[] temp = arr.clone();
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
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = generateRandomArray(n);

        System.out.println("Testing Bubble Sort...");
        measureTime(arr, "BubbleSort");
        System.out.println("\nTesting Selection Sort...");
        measureTime(arr, "SelectionSort");
        System.out.println("\nTesting Insertion Sort...");
        measureTime(arr, "InsertionSort");
        System.out.println("\nTesting Merge Sort...");
        measureTime(arr, "MergeSort");
        System.out.println("\nTesting Quick Sort...");
        measureTime(arr, "QuickSortLomuto");
    }
}
