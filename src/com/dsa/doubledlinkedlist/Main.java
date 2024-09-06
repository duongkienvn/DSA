package com.dsa.doubledlinkedlist;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
//        DoublyLinkedList dll = new DoublyLinkedList();
//
//        dll.addFirst(10);
//        dll.addLast(20);
//        dll.addFirst(5);
//        dll.addLast(30);
//        dll.addAtIndex(4, 7);
//
//        dll.printForward();
//
//        dll.removeAtIndex(2);
//        dll.printForward();
        HashMap<Integer, String> hash_map = new HashMap<>();
        hash_map.put(1, "hga");
        hash_map.put(2, "zzz");
        hash_map.put(3, "sho");
        hash_map.computeIfAbsent(4, k -> "update");
        System.out.println(hash_map);

    }
}
