package com.dsa.homework.fourthlab;

public class RLE {

    /**
     * Computes the length of the compression array.
     * @param t a binary array
     * @return an integer.
     */
    public static int length(int[] t) {
        if (t.length == 0) {
            return 0;
        }

        int length = 0;
        for (int i = 1; i < t.length; i++) {
            if (t[i] != t[i - 1]) {
                length += 2;
            }
        }
        length += 2;
        return length;
    }

    /**
     * Compresses an array in RLE format and return the result.
     * @param t
     * @return compressed array.
     */
    public static int[] compress(int[] t) {
        // TODO: Your code here
        int length = length(t);
        if (length == 0) {
            return new int[0];
        }
        int[] result = new int[length];
        int count = 1;
        int current = t[0];
        int idx = 0;

        for (int i = 1; i < t.length; i++) {
            if (t[i] == current) {
                count++;
            } else {
                result[idx++] = current;
                result[idx++] = count;
                current = t[i];
                count = 1;
            }
        }

        result[idx++] = current;
        result[idx++] = count;
        return result;
    }

    /**
     * Computes the length of the decompressed array.
     * @param t
     * @return an integer.
     */
    public static int lengthInverse(int[] t) {
        int length = 0;
        if (t.length == 0) {
            return 0;
        }

        for (int i = 1; i < t.length; i += 2) {
            length += t[i];
        }
        // TODO: Your code here
        return length;
    }

    /**
     * Decompresses the array.
     * @param t
     * @return an array
     */
    public static int[] decompress(int[] t) {
        int length = lengthInverse(t);
        if (length == 0) {
            return new int[0];
        }
        int[] result = new int[length];
        int idx = 0;

        for (int i = 0; i < t.length; i += 2) {
            int value = t[i];
            int count = t[i + 1];
            for (int j = 0; j < count; j++) {
                result[idx++] = value;
            }
        }

        return result;
    }
}
