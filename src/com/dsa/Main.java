package com.dsa;

public class Main {
    public static String toBinary(int n) {
        if (n == 0) {
            return "";
        }
        if (n == 1) {
            return "0";
        }
        String result = String.valueOf(n % 2);
        return toBinary(n / 2) + result;
    }

    public static int countPath(int m, int n, int t) {
        int j1 = t - 1;
        int j2 = t;
        int[][] matrix = new int[m][n];
        matrix[0][j1] = 1;
        matrix[0][j2] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = j2; j < n; j++) {
//                if (i > 0 && j == j2) {
//                    matrix[i][j] += matrix[i - 1][j];
//                } else if (i >= 1 && j > j2) {
//                    matrix[i][j] += matrix[i][j - 1];
//                    matrix[i][j] += matrix[i - 1][j];
//                } else if (i == 0 && j > j2) {
//                    matrix[i][j] += matrix[i][j - 1];
//                }
                if (i > 0) {
                    matrix[i][j] += matrix[i - 1][j];
                }
                if (j > 0) {
                    matrix[i][j] += matrix[i][j - 1];
                }
            }

            for (int j = j1; j >= 0; j--) {
                if (i > 0) {
                    matrix[i][j] += matrix[i - 1][j];
                }
                if (j > 0) {
                    matrix[i][j] += matrix[i][j - 1];
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

    public static int countPathII(int m, int n, int t) {
        return recurRobot(m, t) + recurRobot(m, n - t);
    }

    public static int recurRobot(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }
        return recurRobot(m - 1, n) + recurRobot(m, n - 1);
    }

    public static int countPathIII(int m, int n, int t) {
        return recurRobotII(0, 0, m, t) + recurRobotII(0, t, m, n);
    }

    public static int recurRobotII(int i, int j, int m, int n) {
        if (i == m - 1 || j == n - 1) {
            return 1;
        }
        return recurRobotII(i + 1, j, m, n) + recurRobotII(i, j + 1, m, n);
    }

    public static void main(String[] args) {
        int n =  6;

        System.out.println("So "+ n+" co dang nhi phan la: "+toBinary(n));
    }
}
