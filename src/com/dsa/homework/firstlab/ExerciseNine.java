package com.dsa.homework.firstlab;

import java.util.Arrays;
import java.util.Scanner;

public class ExerciseNine {
    public static int binarySearch(int[] arr, int value) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == value) {
                return mid + 1;
            } else if (arr[mid] < value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int t = scanner.nextInt();
        int height = 0;

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
            if (i == t - 1) {
                height = arr[i];
            }
        }

        Arrays.sort(arr);
        System.out.println(binarySearch(arr, height));
    }
}
