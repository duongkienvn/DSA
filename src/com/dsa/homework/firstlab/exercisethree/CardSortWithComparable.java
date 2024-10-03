package com.dsa.homework.firstlab.exercisethree;

import com.dsa.homework.firstlab.ExerciseTwo;

public class CardSortWithComparable {
    public static void main(String[] args) {
        Card[] deck = Card.createDeck();
        System.out.println("Before sorting:");
        for (Card card : deck) {
            System.out.println(card);
        }

        ExerciseTwo exerciseTwo = new ExerciseTwo();
        exerciseTwo.selectionSort(deck);

        System.out.println("\nAfter sorting:");
        for (Card card : deck) {
            System.out.println(card);
        }
    }
}
