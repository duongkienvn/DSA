package com.dynamicprogramming;

import java.util.Arrays;

public class DynamicProgramming {
    public static int NumberOfLIS(int nums[], int n) {
        int dp[] = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i : dp) {
            if (max < i) {
                max = i;
            }
        }

        return max;
    }

    public static int findNumberOfLIS(int[] nums) {
        int length = nums.length;
        int dp[] = new int[length];
        Arrays.fill(dp, 1);

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int count = 0, max = Integer.MIN_VALUE;
        for (int i : dp) {
            if (max < i) {
                max = i;
            }
        }

        for (int i : dp) {
            if (max == i) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int arr[] = {1, 3, 4, 5, 1, 5, 4, 9};
        System.out.println(NumberOfLIS(arr, arr.length));
    }
}
