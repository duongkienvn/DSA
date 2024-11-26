package com.dsa.homework.fourthlab;

public class TestLZ77 {

    public static void main(String[] args) {
        testLongestOccurrence();
    }

    public static void testLongestOccurrence() {
        // Test case 1: No previous occurrence (currentPosition == 0)
        int[] t1 = {1, 2, 3, 4, 5};
        Occurrence result1 = LZ77.longestOccurrence(t1, 0, 5);
        System.out.println("Test case 1 - Expected: retour = 0, size = 0, Got: retour = " + result1.retour + ", size = " + result1.size);

        // Test case 2: A simple match
        int[] t2 = {1, 2, 3, 1, 2, 3};
        Occurrence result2 = LZ77.longestOccurrence(t2, 5, 6);
        System.out.println("Test case 2 - Expected: retour = 3, size = 2, Got: retour = " + result2.retour + ", size = " + result2.size);

        // Test case 3: Matching sequence
        int[] t3 = {1, 2, 1, 2, 1, 2};
        Occurrence result3 = LZ77.longestOccurrence(t3, 5, 6);
        System.out.println("Test case 3 - Expected: retour = 2, size = 2, Got: retour = " + result3.retour + ", size = " + result3.size);

        // Test case 4: Large window with no matches
        int[] t4 = {10, 20, 30, 40, 50};
        Occurrence result4 = LZ77.longestOccurrence(t4, 4, 5);
        System.out.println("Test case 4 - Expected: retour = 0, size = 0, Got: retour = " + result4.retour + ", size = " + result4.size);

        // Test case 5: Exact match found
        int[] t5 = {5, 1, 2, 5, 1, 2};
        Occurrence result5 = LZ77.longestOccurrence(t5, 5, 6);
        System.out.println("Test case 5 - Expected: retour = 3, size = 2, Got: retour = " + result5.retour + ", size = " + result5.size);
    }
}

