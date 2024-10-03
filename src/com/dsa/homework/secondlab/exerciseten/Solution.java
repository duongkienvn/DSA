package com.dsa.homework.secondlab.exerciseten;

class Node {
    int data;
    Node next;
    Node(int value) {
        this.data = value;
        next = null;
    }
}

class Solution {
    // Function to reverse a linked list.
    Node reverseList(Node head) {
        if (head == null) {
            return null;
        }

        Node current = head;
        while (current != null && current.next != null) {
            Node nextNode = current.next;
            current.next = nextNode.next;
            nextNode.next = head;
            head = nextNode;
        }

        return head;
    }
}
