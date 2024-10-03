package com.dsa.homework.secondlab.exercisetwo;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        SimpleArrayList<Integer> list = new SimpleArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);

        System.out.println("Size: " + list.size());
        System.out.println("Element at index 1: " + list.get(1));

        list.set(1, 5);
        System.out.println("Updated element at index 1: " + list.get(1));

        list.remove(2);
        System.out.println("Size after removal: " + list.size());

        System.out.println("List contains 3: " + list.isContain(3));
        System.out.println("Is list empty: " + list.isEmpty());

        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
