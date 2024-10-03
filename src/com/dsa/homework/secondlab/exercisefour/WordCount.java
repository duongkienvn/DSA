package com.dsa.homework.secondlab.exercisefour;

public class WordCount {
    private String word;
    private int count;

    public WordCount(String word, int count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }

    public void incrementCount() {
        this.count++;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WordCount that = (WordCount) obj;
        return word.equalsIgnoreCase(that.word);
    }

    @Override
    public int hashCode() {
        return word.toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return word + ": " + count;
    }
}
