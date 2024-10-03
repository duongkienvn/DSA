package com.dsa.homework.firstlab.exercisethree;

import java.util.Arrays;

public class CardSortWithComparator {
    public static void main(String[] args) {
        Card[] deck = Card.createDeck();
        System.out.println("Before sorting:");
        for (Card card : deck) {
            System.out.println(card);
        }

        CompareCard compareCard = new CompareCard();
        Arrays.sort(deck, compareCard);

        System.out.println("\nAfter sorting:");
        for (Card card : deck) {
            System.out.println(card);
        }
    }
}
