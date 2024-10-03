package com.dsa.homework.secondlab.exerciseeight;

public class Solution {
    class Node
    {
        int data;
        Node next;
        Node(int d) {data = d; next = null; }
    }

    public int getSize(Node head) {
        int length = 0;
        Node current = head;
        while (current != null) {
            current = current.next;
            length++;
        }

        return length;
    }

    int getKthFromLast(Node head, int k) {
        int size = getSize(head);
        if (k > size) {
            return -1;
        }

        int index = size - k;
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }
}
