package com.dsa.homework.secondlab.exercisefifteen;

public class Solution {
    class Node {
        int data;
        Node next;
        Node prev;

        Node(int data) {
            this.data = data;
            next = prev = null;
        }
    }

    public Node rotateDLL(Node start, int p) {
        if (start == null || p == 0) {
            return start;
        }

        Node dummy = new Node(0);
        dummy.next = start;

        Node lastNode = start;
        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }

        while (p-- > 0) {
            Node firstNode = dummy.next;
            Node nextNode = firstNode.next;

            lastNode.next = firstNode;
            firstNode.prev = lastNode;
            lastNode = firstNode;
            firstNode.next = null;

            dummy.next = nextNode;
            nextNode.prev = null;
        }

        return dummy.next;
    }
}
