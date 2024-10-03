package com.dsa;

public class Main {
    public static int countPath(int m, int n, int t) {
        int j1 = t - 1;
        int j2 = t;
        int[][] matrix = new int[m][n];
        matrix[0][j1] = 1;
        matrix[0][j2] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = j2; j < n; j++) {
                if (i > 0 && j == j2) {
                    matrix[i][j] += matrix[i - 1][j];
                } else if (i >= 1 && j > j2) {
                    matrix[i][j] += matrix[i][j - 1];
                    matrix[i][j] += matrix[i - 1][j];
                } else if (i == 0 && j > j2) {
                    matrix[i][j] += matrix[i][j - 1];
                }
            }

            for (int j = j1; j >= 0; j--) {
                if (i > 0 && j == j1) {
                    matrix[i][j] += matrix[i - 1][j];
                } else if (i >= 1 && j < j1) {
                    matrix[i][j] += matrix[i][j + 1];
                    matrix[i][j] += matrix[i - 1][j];
                } else if (i == 0 && j < j1) {
                    matrix[i][j] += matrix[i][j + 1];
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        return matrix[m - 1][0] + matrix[m - 1][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(countPath(6, 6, 3));
    }
}
