package com.dsa.homework.fourthlab;

public class TestRLE {

    public static void main(String[] args) {
        testRle1();
        testRle2();
        testRle3();
        testRle4();
    }

    public static void testRle1() {
        System.out.println("Running testRle1...");
        int[] input = {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0};
        System.out.println("Length of compressed array: " + RLE.length(input)); // Expected: 6

        int[] input2 = {1, 1, 1, 0, 0, 0, 1, 1, 1};
        System.out.println("Length of compressed array: " + RLE.length(input2)); // Expected: 6

        int[] input3 = {1};
        System.out.println("Length of compressed array: " + RLE.length(input3)); // Expected: 2

        int[] emptyInput = {};
        System.out.println("Length of compressed array: " + RLE.length(emptyInput)); // Expected: 0
    }

    public static void testRle2() {
        System.out.println("Running testRle2...");
        int[] input = {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0};
        int[] compressed = RLE.compress(input);
        System.out.print("Compressed array: ");
        printArray(compressed); // Expected: {0, 5, 1, 1, 0, 5}

        int[] input2 = {1, 1, 1, 0, 0, 0, 1, 1, 1};
        int[] compressed2 = RLE.compress(input2);
        System.out.print("Compressed array: ");
        printArray(compressed2); // Expected: {1, 3, 0, 3, 1, 3}

        int[] input3 = {1};
        int[] compressed3 = RLE.compress(input3);
        System.out.print("Compressed array: ");
        printArray(compressed3); // Expected: {1, 1}

        int[] emptyInput = {};
        int[] compressedEmpty = RLE.compress(emptyInput);
        System.out.print("Compressed array: ");
        printArray(compressedEmpty); // Expected: {}
    }

    public static void testRle3() {
        System.out.println("Running testRle3...");
        int[] compressedInput = {0, 5, 1, 1, 0, 5};
        System.out.println("Length of decompressed array: " + RLE.lengthInverse(compressedInput)); // Expected: 11

        int[] compressedInput2 = {1, 3, 0, 3, 1, 3};
        System.out.println("Length of decompressed array: " + RLE.lengthInverse(compressedInput2)); // Expected: 9

        int[] compressedInput3 = {1, 1};
        System.out.println("Length of decompressed array: " + RLE.lengthInverse(compressedInput3)); // Expected: 1

        int[] emptyInput = {};
        System.out.println("Length of decompressed array: " + RLE.lengthInverse(emptyInput)); // Expected: 0
    }

    public static void testRle4() {
        System.out.println("Running testRle4...");
        int[] compressedInput = {0, 5, 1, 1, 0, 5};
        int[] decompressed = RLE.decompress(compressedInput);
        System.out.print("Decompressed array: ");
        printArray(decompressed); // Expected: {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}

        int[] compressedInput2 = {1, 3, 0, 3, 1, 3};
        int[] decompressed2 = RLE.decompress(compressedInput2);
        System.out.print("Decompressed array: ");
        printArray(decompressed2); // Expected: {1, 1, 1, 0, 0, 0, 1, 1, 1}

        int[] compressedInput3 = {1, 1};
        int[] decompressed3 = RLE.decompress(compressedInput3);
        System.out.print("Decompressed array: ");
        printArray(decompressed3); // Expected: {1}

        int[] emptyInput = {};
        int[] decompressedEmpty = RLE.decompress(emptyInput);
        System.out.print("Decompressed array: ");
        printArray(decompressedEmpty); // Expected: {}
    }

    private static void printArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("[]");
            return;
        }
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
