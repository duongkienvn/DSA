package com.dsa.singlylinkedlist;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Exercise {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode reverseList(ListNode head) {
        ListNode currentNode = head;
        while (currentNode != null && currentNode.next != null) {
            ListNode nextNode = currentNode.next;
            currentNode.next = nextNode.next;
            nextNode.next = head;
            head = nextNode;
        }

        return head;
    }

    public static ListNode reverseListRecursion(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode nextNode = head.next;
        if (nextNode == null) {
            return head;
        }

        ListNode newHead = reverseListRecursion(nextNode);
        nextNode.next = head;
        head.next = null;

        return newHead;
    }

    public static void printListNode(ListNode head) {
        if (head == null) {
            System.out.println("List is empty!");
        } else {
            ListNode temp = head;
            while (temp != null) {
                System.out.print(temp.val);
                temp = temp.next;
                if (temp != null) {
                    System.out.print(" -> ");
                } else {
                    System.out.println();
                }
            }
        }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode current = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }

            current = current.next;
        }

        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }

        return dummy.next;
    }

    public static ListNode deleteDuplicates(ListNode head) {
        ListNode currentNode = head;

        while (currentNode != null && currentNode.next != null) {
            if (currentNode.val == currentNode.next.val) {
                currentNode.next = currentNode.next.next;
            } else {
                currentNode = currentNode.next;
            }
        }

        return head;
    }


    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode secondHalf = reverseList(slow.next);

        ListNode p1 = head;
        ListNode p2 = secondHalf;

        while (p2 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        return true;
    }

    public static ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }

        while (head != null && head.val == val) {
            head = head.next;
        }

        ListNode currentNode = head;
        while (currentNode != null && currentNode.next != null) {
            if (currentNode.next.val == val) {
                currentNode.next = currentNode.next.next;
            } else {
                currentNode = currentNode.next;
            }
        }

        return head;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode();
        ListNode current = dummyHead;
        int carry = 0;

        while (l1 != null || l2 != null) {
            int x = l1 != null ? l1.val : 0;
            int y = l2 != null ? l2.val : 0;
            int sum = x + y + carry;

            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (carry > 0) {
            current.next = new ListNode(carry);
        }

        return dummyHead.next;
    }

    public static ListNode deleteDuplicatesII(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode previous = dummy;
        ListNode current = head;

        while (current != null) {
            boolean duplicate = false;
            while (current.next != null && current.val == current.next.val) {
                current = current.next;
                duplicate = true;
            }

            if (duplicate) {
                previous.next = current.next;
            } else {
                previous = previous.next;
            }

            current = current.next;
        }

        return dummy.next;

    }

    public static ListNode removeNodes(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode current = head;
        Stack<ListNode> stack = new Stack<>();

        while (current != null) {
            while (!stack.isEmpty() && stack.peek().val < current.val) {
                stack.pop();
            }
            stack.push(current);
            current = current.next;
        }

        ListNode dummy = new ListNode(0);
        ListNode result = dummy;

        for (ListNode listNode : stack) {
            result.next = listNode;
            result = result.next;
        }

        return dummy.next;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode currentA = headA, currentB = headB;
        int lenA = 0, lenB = 0;
        ListNode lastA = null, lastB = null;

        while (currentA != null) {
            lenA++;
            lastA = currentA;
            currentA = currentA.next;
        }

        while (currentB != null) {
            lenB++;
            lastB = currentB;
            currentB = currentB.next;
        }

        if (lastA != lastB) {
            return null;
        }

        currentA = headA;
        currentB = headB;

        int diff = Math.abs(lenA - lenB);
        if (lenA > lenB) {
            while (diff-- > 0) {
                currentA = currentA.next;
            }
        } else {
            while (diff-- > 0) {
                currentB = currentB.next;
            }
        }

        while (currentA != currentB) {
            currentA = currentA.next;
            currentB = currentB.next;
        }

        return currentA;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode current = head;
        int len = 0;

        while (current != null) {
            len++;
            current = current.next;
        }

        int index = len - n + 1;
        current = head;
        if (len == 1) {
            return null;
        }

        if (index == 1) {
            head = current.next;
            return head;
        }

        int count = 1;
        while (current != null) {
            if (count == index - 1) {
                current.next = current.next.next;
                break;
            } else {
                count++;
                current = current.next;
            }
        }

        return head;
    }

    public ListNode removeNthFromEndGPT(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;

        for (int i = 0; i <= n; i++) {
            second = second.next;
        }

        while (second != null) {
            first = first.next;
            second = second.next;
        }

        first.next = first.next.next;

        return dummy.next;
    }

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode previous = dummy;

        while (previous.next != null && previous.next.next != null) {
            ListNode first = previous.next;
            ListNode second = previous.next.next;

            previous.next = second;
            first.next = second.next;
            second.next = first;

            previous = first;
        }

        return dummy.next;
    }

    public ListNode getNodeByIndex(ListNode head, int index) {
        ListNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current;
    }

    public ListNode swapNodes(ListNode head, int k) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;

        for (int i = 0; i < k; i++) {
            first = first.next;
        }

        ListNode temp = dummy;
        for (int i = 0; i < k; i++) {
            temp = temp.next;
        }
        while (temp != null) {
            temp = temp.next;
            second = second.next;
        }

        int tempVal = first.val;
        first.val = second.val;
        second.val = tempVal;

        return dummy.next;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) {
            return head;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode prev = dummy;

        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }

        ListNode cur = prev.next;
        for (int i = 0; i < right - left; i++) {
            ListNode nextNode = cur.next;
            cur.next = nextNode.next;
            nextNode.next = prev.next;
            prev.next = nextNode;
        }

        return dummy.next;
    }

    public ListNode rotateRight(ListNode head, int k) {
        return head;
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;
        ListNode previous = null;

        while (fast != null && fast.next != null) {
            previous = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        previous.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(slow);

        return merge(left, right);
    }

    public ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode();
        ListNode current = dummy;

        while (left != null && right != null) {
            if (left.val < right.val) {
                current.next = left;
                left = left.next;
            } else {
                current.next = right;
                right = right.next;
            }

            current = current.next;
        }

        if (left != null) {
            current.next = left;
        }

        if (right != null) {
            current.next = right;
        }

        return dummy.next;
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        ListNode current = head;

        while (current != null) {
            ListNode nextNode = current.next;
            ListNode sorted = dummy;

            while (sorted.next != null && sorted.next.val < current.val) {
                sorted = sorted.next;
            }

            current.next = sorted.next;
            sorted.next = current;
            current = nextNode;
        }

        return dummy.next;
    }

    public ListNode doubleIt(ListNode head) {
        ListNode reverse = null;
        ListNode current = head;

        while (current != null) {
            ListNode nextNode = current.next;
            current.next = reverse;
            reverse = current;
            current = nextNode;
        }
        head = reverse;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode temp = dummy;
        int carry = 0;
        while (head != null) {
            int doubledValue = carry + head.val * 2;
            head.val = doubledValue % 10;
            carry = doubledValue / 10;
            temp.next = head;
            head = head.next;
        }

        if (carry > 0) {
            temp.next = new ListNode(carry);
        }

        reverse = reverseList(dummy.next);

        return reverse;
    }

    public ListNode doubleItC1(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
        }
        head = prev;

        // Double the number and handle carry
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode temp = dummy;
        int carry = 0;
        while (head != null) {
            int sum = carry + head.val * 2;
            head.val = sum % 10;
            carry = sum / 10;
            temp = head;
            head = head.next;
        }

        // If there is a carry left, add a new node
        if (carry > 0) {
            temp.next = new ListNode(carry);
        }

        // Reverse the linked list again to get the final result
        prev = null;
        current = dummy.next;
        while (current != null) {
            ListNode nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
        }

        return prev;
    }

    public int[] nextLargerNodes(ListNode head) {
        List<Integer> values = new ArrayList<>();
        ListNode current = head;

        while (current != null) {
            values.add(current.val);
            current = current.next;
        }

        int length = values.size();
        Stack<Integer> stack = new Stack<>();
        int result[] = new int[length];

        for (int i = 0; i < length; i++) {
            while (!stack.isEmpty() && values.get(i) > values.get(stack.peek())) {
                result[stack.pop()] = values.get(i);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            result[stack.pop()] = 0;
        }

        return result;
    }

    public ListNode oddEvenList(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode odd = dummy;
        ListNode even = new ListNode();
        ListNode evenHead = even;
        ListNode current = head;
        int index = 1;

        while (current != null) {
            if (index % 2 == 1) {
                odd.next = current;
                odd = odd.next;
            } else {
                even.next = current;
                even = even.next;
            }

            current = current.next;
            index++;
        }

        even.next = null;
        odd.next = evenHead.next;
        return dummy.next;
    }

    public ListNode oddEvenListGpt(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;

        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }

        odd.next = evenHead;

        return head;
    }

    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode result[] = new ListNode[k];
        int len = 0;
        ListNode current = head;
        while (current != null) {
            len++;
            current = current.next;
        }

        current = head;

        while (current != null) {
            ListNode part = new ListNode();
            
        }
        return result;
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        slow.next = null;

        Stack<ListNode> stack = new Stack<>();
        while (mid != null) {
            stack.push(mid);
            mid = mid.next;
        }

        ListNode current = head;
        while (!stack.isEmpty()) {
            ListNode nextNode = current.next;
            current.next = stack.pop();
            current.next.next = nextNode;
            current = nextNode;
        }


    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        printListNode(l1);

        Exercise exercise = new Exercise();
        exercise.reorderList(l1);
        printListNode(l1);

    }
}
