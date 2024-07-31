package com.dsa.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        int dp[] = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        int dp[][] = new int[numRows][numRows];

        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (i == j || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }
                list.add(dp[i][j]);
            }
            result.add(list);
        }

        return result;
    }

    public List<List<Integer>> generateGpt(int numRows) {
        List<List<Integer>> triangles = new ArrayList<>();

        if (numRows <= 0) {
            return triangles;
        }

        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        triangles.add(firstRow);

        for (int i = 1; i < numRows; i++) {
            List<Integer> previousRow = triangles.get(i - 1);
            List<Integer> currentRow = new ArrayList<>();

            currentRow.add(1);

            for (int j = 1; j < i; j++) {
                int sum = previousRow.get(j - 1) + previousRow.get(j);
                currentRow.add(sum);
            }

            currentRow.add(1);

            triangles.add(currentRow);
        }

        return triangles;
    }

    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        int dp[][] = new int[rowIndex + 1][rowIndex + 1];

        for (int i = 0; i < rowIndex + 1; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || i == j) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }

                if (i == rowIndex) {
                    result.add(dp[i][j]);
                }
            }
        }

        return result;
    }

    public int minCostClimbingStairs(int[] cost) {
        int length = cost.length;

        // min cost to reach ith step, base cases
        int dp[] = new int[length + 1];
        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i < length + 1; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }

        return dp[length];
    }

    public int maxRepeating(String sequence, String word) {
        if (!sequence.contains(word)) {
            return 0;
        }
        int repeated = 0;
        int length = sequence.length();
        int index = sequence.indexOf(word.charAt(0));

        String temp = "";
        for (int i = index; i < length; i++) {
            temp += sequence.charAt(i);
            if (temp.equals(word)) {
                repeated++;
                System.out.println(temp);
                temp = "";
            }
        }

        return repeated;
    }

    public int minimumTotalGpt(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }

        int row = triangle.size();
        for (int i = row - 2; i >= 0; i--) {
            List<Integer> belowRow = triangle.get(i + 1);
            List<Integer> currentRow = triangle.get(i);

            for (int j = 0; j < currentRow.size(); j++) {
                int current = currentRow.get(j);
                int below = belowRow.get(j);
                int belowRight = belowRow.get(j + 1);

                currentRow.set(j, current + Math.min(belowRight, below));
            }
        }

        return triangle.get(0).get(0);
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        int minSum = Integer.MAX_VALUE;

        for (int i = 1; i < row; i++) {
            List<Integer> previousRow = triangle.get(i - 1);
            List<Integer> currentRow = triangle.get(i);
            int col = currentRow.size();

            for (int j = 0; j < col; j++) {
                if (j == 0) {
                    currentRow.set(j, previousRow.get(j) + currentRow.get(j));
                } else if (j == col - 1) {
                    currentRow.set(j, currentRow.get(j) + previousRow.get(j - 1));
                } else {
                    int sum = currentRow.get(j) + Math.min(previousRow.get(j), previousRow.get(j - 1));
                    currentRow.set(j, sum);
                }
            }
        }

        for (int i = 0; i < triangle.get(row - 1).size(); i++) {
            minSum = Math.min(triangle.get(row - 1).get(i), minSum);
        }

        return minSum;
    }

    public int minDistance(String word1, String word2) {
        int word1Len = word1.length();
        int word2Len = word2.length();
        int dp[][] = new int[word1Len + 1][word2Len + 1];

        

        return 1;
    }

    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> result = new ArrayList<>();

        return result;
    }

    public int integerBreak(int n) {
        if (n == 2) {
            return 1;
        }

        int dp[] = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int arr[] = {1, 3, 4, 5, 1, 5, 4, 9};
        System.out.println(NumberOfLIS(arr, arr.length));
    }
}
