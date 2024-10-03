package com.dsa.homework.secondlab.exercisefour;

import com.dsa.homework.secondlab.exercisethree.SimpleLinkedList;

import java.util.Scanner;

public class WordCounter {
    public static void main(String[] args) {
        SimpleLinkedList<WordCount> wordList = new SimpleLinkedList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập đoạn văn bản cần đếm số lần xuất hiện của từ:");

        String inputText = scanner.nextLine();

        String[] words = inputText.split("\\s+");

        for (String word : words) {
            word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();

            if (word.isEmpty()) {
                continue;
            }

            boolean found = false;
            for (int i = 0; i < wordList.size(); i++) {
                WordCount wc = wordList.get(i);
                if (word.equals(wc.getWord())) {
                    found = true;
                    wc.incrementCount();
                    break;
                }
            }

            if (!found) {
                wordList.add(new WordCount(word, 1));
            }
        }

        System.out.println("Kết quả đếm từ:");
        for (int i = 0; i < wordList.size(); i++) {
            System.out.println(wordList.get(i));
        }
    }
}
