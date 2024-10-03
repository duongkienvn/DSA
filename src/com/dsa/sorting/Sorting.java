package com.dsa.sorting;

import java.util.Arrays;

public class Sorting {
    public void bubleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    public void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int idx = i - 1;
            while (idx >= 0 && key < arr[idx]) {
                arr[idx + 1] = arr[idx];
                idx--;
            }
            arr[idx + 1] = key;
        }
    }

    public static void main(String[] args) {
        Sorting sorting = new Sorting();
        int[] arr = {2, 1, 5, 9, 8, 10};
        sorting.insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
