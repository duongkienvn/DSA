package com.dsa.homework.firstlab.exercisethree;

public class Card implements Comparable<Card> {
    private String rank;
    private String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return this.rank + " of " + this.suit;
    }

    public static Card[] createDeck() {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"Hearts", "Diamonds", "Club", "Spades"};

        Card[] deck = new Card[52];
        int index = 0;

        for (String suit : suits) {
            for (String rank : ranks) {
                deck[index++] = new Card(rank, suit);
            }
        }

        return deck;
    }

    @Override
    public int compareTo(Card o) {
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suits = {"Spades", "Club", "Diamonds", "Hearts"};

        int compareTo = Integer.compare(indexOf(this.rank, ranks), indexOf(o.rank, ranks));
        if (compareTo != 0) {
            return compareTo;
        }
        return Integer.compare(indexOf(this.suit, suits), indexOf(o.suit, suits));
    }

    public int indexOf(String value, String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (value.equals(array[i])) {
                return i;
            }
        }

        return -1;
    }
}
