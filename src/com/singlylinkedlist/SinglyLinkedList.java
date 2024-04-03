package com.singlylinkedlist;

public class SinglyLinkedList {

    public static void printLinkedList(Node head) {
        if (head == null) {
            System.out.println("List is empty!");
        } else {
            Node temp = head;
            while (temp != null) {
                System.out.print(temp.value);
                temp = temp.next;
                if (temp != null) {
                    System.out.print(" -> ");
                } else {
                    System.out.println();
                }
            }
        }
    }

    public static Node addToHead(Node headNode, int value) {
        Node newNode = new Node(value);
        if (headNode != null) {
            newNode.next = headNode;
        }

        return newNode;
    }

    public static Node addToTail(Node headNode, int value) {
        Node newNode = new Node(value);

        if (headNode == null) {
            return newNode;
        } else {
            // B1: xac dinh lastNode (lastNode = null)
            Node lastNode = headNode;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            lastNode.next = newNode;
        }

        return headNode;
    }

    public static Node addToIndex(Node headNode, int value, int index) {
        if (index == 0) {
            return addToHead(headNode, value);
        } else {
            Node newNode = new Node(value);
            int count = 0;
            Node currentNode = headNode;
            while (currentNode != null) {
                count++;
                if (count == index) {
                    newNode.next = currentNode.next;
                    currentNode.next = newNode;
                    break;
                }
                currentNode = currentNode.next;
            }
        }

        return headNode;
    }

    public static Node removeAtHead(Node headNode) {
        if (headNode != null) {
            return headNode.next;
        }
        return headNode;
    }

    public static Node removeAtTail(Node headNode) {
        if (headNode == null) {
            return null;
        } else {
            Node lastNode = headNode;
            Node prevNode = null;
            while (lastNode.next != null) {
                prevNode = lastNode;
                lastNode = lastNode.next;
            }
            if (prevNode != null) {
                prevNode.next = lastNode.next;
            }
        }
        return headNode;
    }

    public static Node removeAtIndex(Node headNode, int value, int index) {
        if (headNode == null || index < 0) {
            return null;
        }

        if (index == 0) {
            return removeAtHead(headNode);
        }

        Node currentNode = headNode;
        Node previousNode = null;
        int currentIndex = 0;
        while (currentNode != null && (currentNode.value == value || currentIndex != index)) {
            previousNode = currentNode;
            currentNode = currentNode.next;
            currentIndex++;
        }

        if (currentNode == null || currentIndex != index) {
            return headNode;
        }

        if (previousNode != null) {
            previousNode.next = currentNode.next;
        }

        return headNode;
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);

        n1.next = n2;
        n2.next = n3;

        printLinkedList(n1);

//        n1 = addToHead(n1, 0);
//        printLinkedList(n1);
//
//        n1 = addToTail(n1, 4);
//        printLinkedList(n1);

        n1 = addToIndex(n1, 5, 0);
        printLinkedList(n1);
        n1 = addToIndex(n1, 5, 4);
        printLinkedList(n1);
        n1 = addToIndex(n1, 10, 2);
        printLinkedList(n1);

//        n1 = removeAtHead(n1);
//        printLinkedList(n1);
//
//        n1 = removeAtTail(n1);
//        printLinkedList(n1);

        n1 = removeAtIndex(n1, 5, 0);
        printLinkedList(n1);
    }

    // khong de hai clas trong mot lop khi lam trong thuc te
    public static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }
}
