package com.dsa;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Tt {
    public int manhattan(int[][] m) {
        int rows = m.length;
        int cols = m[0].length;
        int result = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (m[i][j] == 0) {
                    continue;
                }
                int i1 = (m[i][j] - 1) / rows;
                int j1 = (m[i][j] - 1) % cols;
                result += Math.abs(i - i1) + Math.abs(j - j1);
            }
        }

        return result;
    }

    public int[][] copyMatrix(int[][] matrix) {
        int[][] result = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, result[i], 0, matrix[i].length);
        }

        return result;
    }

    public Iterable<int[][]> neighbors(int[][] m) {
        List<int[][]> result = new ArrayList<>();
        int zeroRow = -1, zeroCol = -1;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] == 0) {
                    zeroRow = i;
                    zeroCol = j;
                    break;
                }
            }
            if (zeroRow != -1) {
                break;
            }
        }

        int directions[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] direc : directions) {
            int newRow = zeroRow + direc[0];
            int newCol = zeroCol + direc[1];
            if (newRow >= 0 && newRow < m.length && newCol >= 0 && newCol < m[0].length) {
                int[][] newMatrix = copyMatrix(m);

                newMatrix[zeroRow][zeroCol] = newMatrix[newRow][newCol];
                newMatrix[newRow][newCol] = 0;

                result.add(newMatrix);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.addFirst(1);
        arrayDeque.addFirst(4);
        System.out.println(arrayDeque.removeFirst());
        arrayDeque.pollFirst();

    }
}
