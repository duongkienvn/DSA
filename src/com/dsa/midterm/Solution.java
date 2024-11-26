package com.dsa.midterm;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void generator(List<String> result, String[] alphabet, String[] arr, int i, int n) {
        if (i == n) {
            result.add(String.join("", arr));
            return;
        }

        for (String letter : alphabet) {
            arr[i] = letter;
            generator(result, alphabet, arr, i + 1, n);
        }
    }

    public static void main(String[] args) {
        // Testcase 1
        List<String> result1 = new ArrayList<>();
        String[] alphabet1 = {"a", "b"};
        String[] arr1 = new String[2]; // Sinh chuỗi độ dài 2
        generator(result1, alphabet1, arr1, 0, 2);
        System.out.println("Output của testcase 1: " + result1);

        // Testcase 2
        List<String> result2 = new ArrayList<>();
        String[] alphabet2 = {"0", "1"};
        String[] arr2 = new String[3]; // Sinh chuỗi độ dài 3
        generator(result2, alphabet2, arr2, 0, 3);
        System.out.println("Output của testcase 2: " + result2);
    }
}
