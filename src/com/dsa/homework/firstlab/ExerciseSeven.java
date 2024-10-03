package com.dsa.homework.firstlab;

import java.util.*;

public class ExerciseSeven {
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void quickSortHoare(int[] arr, int left, int right) {
        if (left < right) {
            int p = hoarePartition(arr, left, right);
            quickSortHoare(arr, left, p);
            quickSortHoare(arr, p + 1, right);
        }
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x = scanner.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        int pairCount = 0;
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int complement = x - arr[i];

            if (frequency.containsKey(complement)) {
                pairCount += frequency.get(complement);
            }

            frequency.put(arr[i], frequency.getOrDefault(arr[i], 0) + 1);
        }
        System.out.println(pairCount);
    }
}
