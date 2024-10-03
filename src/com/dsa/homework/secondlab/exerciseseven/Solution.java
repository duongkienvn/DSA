package com.dsa.homework.secondlab.exerciseseven;

public class Solution {
    class Node{
        int data;
        Node next;
        Node(int a){  data = a; next = null; }
    }

    public int getCount(Node head) {
        int length = 0;
        Node current = head;
        while (current != null) {
            current = current.next;
            length++;
        }

        return length;
    }
}
