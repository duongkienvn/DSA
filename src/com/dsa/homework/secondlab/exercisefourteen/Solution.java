package com.dsa.homework.secondlab.exercisefourteen;

class Node {
    int data;
    Node next;
    Node prev;

    Node(int data) {
        this.data = data;
        next = prev = null;
    }


    class Solution {
        public Node getNodeByIndex(Node head, int index) {
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }

            return current;
        }

        public Node addNode(Node head, int p, int x) {
            if (head == null) {
                head = new Node(x);
                return head;
            }

            Node current = getNodeByIndex(head, p);
            Node newNode = new Node(x);
            Node nextNode = current.next;
            if (nextNode == null) {
                current.next = newNode;
                newNode.prev = current;
            } else {
                nextNode.prev = newNode;
                current.next = newNode;
                newNode.next = nextNode;
                newNode.prev = current;
            }

            return head;
        }

        public Node deleteNode(Node head, int x) {
            Node deleteNode = getNodeByIndex(head, x - 1);
            Node nextNode = deleteNode.next;
            Node prevNode = deleteNode.prev;

            if (nextNode == null) {
                prevNode.next = null;
            } else if (prevNode == null) {
                nextNode.prev = null;
                return nextNode;
            } else {
                prevNode.next = nextNode;
                nextNode.prev = prevNode;
            }

            return head;
        }

        public Node sortedInsert(Node head, int x) {
            if (head == null) {
                return null;
            }

            Node current = head;
            Node newNode = new Node(x);
            if (current.data >= x) {
                current.prev = newNode;
                newNode.next = current;
                return newNode;
            }

            Node prev = null;
            while (current.data < x) {
                prev = current;
                current = current.next;
                if (current == null) {
                    break;
                }
            }

            if (current == null) {
                prev.next = newNode;
                newNode.prev = prev;
            } else {
                prev.next = newNode;
                current.prev = newNode;
                newNode.next = current;
                newNode.prev = prev;
            }

            return head;
        }
    }
}
