package com.dsa.singlylinkedlist;

import java.util.PriorityQueue;
import java.util.Queue;

public class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Node current = head;
        while (current != null) {
            Node copy = new Node(current.val);
            copy.next = current.next;
            current.next = copy;
            current = copy.next;
        }

        current = head;
        while (current != null) {
            Node copied = current.next;
            copied.random = (current.random != null) ? current.random.next : null;
            current = copied.next;
        }

        Node newHead = head.next;
        current = head;
        while (current != null) {
            Node copy = current.next;
            current.next = copy.next;
            current = current.next;
            if (copy.next != null) {
                copy.next = copy.next.next;
            }
        }

        return newHead;
    }

    public static void main(String[] args) {
    }
}
