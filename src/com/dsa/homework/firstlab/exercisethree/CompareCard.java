package com.dsa.homework.firstlab.exercisethree;

import java.util.Comparator;

public class CompareCard implements Comparator<Card> {
    @Override
    public int compare(Card o1, Card o2) {
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suits = {"Spades", "Club", "Diamonds", "Hearts"};

        int compare = Integer.compare(indexOf(o1.getRank(), ranks), indexOf(o2.getRank(), ranks));
        if (compare != 0) {
            return compare;
        }
        return Integer.compare(indexOf(o1.getSuit(), suits), indexOf(o2.getSuit(), suits));
    }

    public int indexOf(String rank, String[] ranks) {
        for (int i = 0; i < ranks.length; i++) {
            if (rank.equals(ranks[i])) {
                return i;
            }
        }

        return -1;
    }
}
