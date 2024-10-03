package com.dsa.homework.secondlab.exericsethirteen;

import java.util.ArrayList;
import java.util.List;

class Node {
    int coeff;
    int pow;
    Node next;

    Node(int a, int b) {
        coeff = a;
        pow = b;
        next = null;
    }

    public class Solution {
        public static Node addPolynomial(Node head1, Node head2) {
            Node dummy = new Node(0, 0);
            Node current = dummy;

            while (head1 != null || head2 != null) {
                int pow1 = head1 != null ? head1.pow : 0;
                int pow2 = head2 != null ? head2.pow : 0;
                int val1 = head1 != null ? head1.coeff : 0;
                int val2 = head2 != null ? head2.coeff : 0;

                if (pow1 == pow2) {
                    int val = val1 + val2;
                    int pow = pow1;
                    Node newNode = new Node(val, pow);
                    current.next = newNode;
                    head1 = head1.next;
                    head2 = head2.next;
                } else if (pow1 > pow2) {
                    current.next = head1;
                    head1 = head1.next;
                } else {
                    current.next = head2;
                    head2 = head2.next;
                }
                current = current.next;
            }

            return dummy.next;
        }
    }
}
