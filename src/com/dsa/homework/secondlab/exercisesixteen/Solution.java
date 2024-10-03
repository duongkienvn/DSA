package com.dsa.homework.secondlab.exercisesixteen;

public class Solution {
    class Node {
        int data;
        Node next;

        Node(int x) {
            data = x;
            next = null;
        }
    }

    void printList(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            System.out.print(slow.data + " ");
            slow = slow.next;
            fast = fast.next.next;

            if (fast == slow) {
                break;
            }
        }
    }

    boolean isCircular(Node head) {
        if (head == null) {
            return true;
        }

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (fast == slow) {
                return true;
            }
        }

        return false;
    }

    public Node sortedInsert(Node head, int data) {
        Node newNode = new Node(data);
        Node current = head;
        if (head.data > data) {
            newNode.next = head;
            head = newNode;
        } else {
            Node prev = null;
            Node slow = head;
            Node fast = head;

            while (current.data < data) {
                prev = current;
                current = current.next;
                slow = slow.next;
                fast = fast.next.next;

                if (slow == fast) {
                    break;
                }
            }

            prev.next = newNode;
            newNode.next = current;
        }

        return head;
    }
}
