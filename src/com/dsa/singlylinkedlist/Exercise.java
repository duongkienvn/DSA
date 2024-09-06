package com.dsa.singlylinkedlist;

import java.util.*;

public class Exercise {
    private static class ListNode {
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

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.add(list);
            }
        }

        ListNode dummy = new ListNode();
        ListNode current = dummy;

        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();

            current.next = node;
            current = current.next;

            if (node.next != null) {
                minHeap.add(node.next);
            }
        }

        return dummy.next;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = head, previous = dummy, next = dummy;

        int count = 0;
        while (current != null) {
            count++;
            current = current.next;
        }

        while (count >= k) {
            current = previous.next;
            next = current.next;
            for (int i = 1; i < k; i++) {
                current.next = next.next;
                next.next = previous.next;
                previous.next = next;
                next = current.next;
            }
            count -= k;
            previous = current;
        }

        return dummy.next;
    }

    class LRUCache {
        class DLinkedNode {
            int key;
            int value;
            DLinkedNode prev;
            DLinkedNode next;
        }

        private void addNode(DLinkedNode node) {
            DLinkedNode nextNode = head.next;

            node.prev = head;
            node.next = nextNode;

            nextNode.prev = node;
            head.next = node;
        }

        private void removeNode(DLinkedNode node) {
            DLinkedNode prevNode = node.prev;
            DLinkedNode nextNode = node.next;

            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }

        private void moveToHead(DLinkedNode node) {
            removeNode(node);
            addNode(node);
        }

        private DLinkedNode popTail() {
            DLinkedNode res = tail.prev;
            removeNode(res);
            return res;
        }

        int size = 0;
        int capacity;
        private Map<Integer, DLinkedNode> cache = new HashMap<>();
        private DLinkedNode head, tail;

        public LRUCache(int capacity) {
            this.size = 0;
            this.capacity = capacity;

            head = new DLinkedNode();
            tail = new DLinkedNode();

            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                return -1;
            }

            moveToHead(node);

            return node.value;
        }

        public void put(int key, int value) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                DLinkedNode newNode = new DLinkedNode();
                newNode.key = key;
                newNode.value = value;

                cache.put(key, newNode);
                addNode(newNode);
                size++;
                if (size > capacity) {
                    DLinkedNode tail = popTail();
                    cache.remove(tail.key);
                    size--;
                }
            } else {
                node.value = value;
                moveToHead(node);
            }
        }
    }

    class LFUCache {
        class Node {
            int key, value, freq;
            Node prev;
            Node next;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
                this.freq = 1;
            }
        }

        class DoublyLinkedList {
            int size;
            Node head, tail;

            public DoublyLinkedList() {
                head = new Node(0, 0);
                tail = new Node(0, 0);
                head.next = tail;
                tail.prev = head;
                size = 0;
            }

            public void addNode(Node node) {
                Node nextNode = head.next;
                node.next = nextNode;
                node.prev = head;
                head.next = node;
                nextNode.prev = node;
                size++;
            }

            public void removeNode(Node node) {
                Node prevNode = node.prev;
                Node nextNode = node.next;

                prevNode.next = nextNode;
                nextNode.prev = prevNode;
                size--;
            }

            public Node removeLast() {
                if (size > 0) {
                    Node lastNode = tail.prev;
                    removeNode(lastNode);
                    return lastNode;
                }
                return null;
            }
        }

        private int capacity, minFreq;
        private Map<Integer, Node> keyToNode;
        private Map<Integer, DoublyLinkedList> freqToDLL;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            this.minFreq = 0;
            keyToNode = new HashMap<>();
            freqToDLL = new HashMap<>();
        }

        public int get(int key) {
            if (!keyToNode.containsKey(key)) {
                return -1;
            }

            Node node = keyToNode.get(key);
            updateNode(node);

            return node.value;
        }

        public void put(int key, int value) {
            if (keyToNode.containsKey(key)) {
                Node node = keyToNode.get(key);
                node.value = value;
                updateNode(node);
            } else {
                if (keyToNode.size() >= capacity) {
                    DoublyLinkedList minFreqList = freqToDLL.get(minFreq);
                    Node nodeToRemove = minFreqList.removeLast();
                    keyToNode.remove(nodeToRemove.key);
                }

                Node newNode = new Node(key, value);
                keyToNode.put(key, newNode);
                minFreq = 1;

                freqToDLL.computeIfAbsent(1, k -> new DoublyLinkedList()).addNode(newNode);
            }
        }

        private void updateNode(Node node) {
            int freq = node.freq;
            DoublyLinkedList list = freqToDLL.get(freq);
            list.removeNode(node);

            if (freq == minFreq && list.size == 0) {
                minFreq++;
            }

            node.freq++;
            freqToDLL.computeIfAbsent(node.freq, k -> new DoublyLinkedList()).addNode(node);
        }
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        return null;
    }

    private int getListSize(ListNode head) {
        int size = 0;
        ListNode current = head;
        while (current != null) {
            size++;
            current = current.next;
        }

        return size;
    }

    private TreeNode listToBST(int left, int right) {
        if (left > right) {
            return null;
        }


        return null;
    }

    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode();
        ListNode first = dummy;
        ListNode temp = new ListNode();
        ListNode second = temp;
        ListNode current = head;

        while (current != null) {
            if (current.val < x) {
                first.next = new ListNode(current.val);
                first = first.next;
            } else {
                second.next = new ListNode(current.val);
                second = second.next;
            }
            current = current.next;
        }
        first.next = temp.next;

        return dummy.next;
    }

    class Solution {
        private ListNode head;
        private Random random;

        public Solution(ListNode head) {
            this.head = head;
            random = new Random();
        }

        public int getRandom() {
            ListNode current = head;
            int size = getListSize(head);

            int index = random.nextInt(size);
            int cnt = 0;

            while (current != null) {
                if (cnt == index) {
                    return current.val;
                }
                current = current.next;
                cnt++;
            }

            return -1;
        }
    }

    public ListNode addTwoNumbersII(ListNode l1, ListNode l2) {
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();

        while (l1 != null) {
            stack1.push(l1);
            l1 = l1.next;
        }

        while (l2 != null) {
            stack2.push(l2);
            l2 = l2.next;
        }

        ListNode result = null;
        int carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            int sum = carry;
            int num1 = !stack1.isEmpty() ? stack1.pop().val : 0;
            int num2 = !stack2.isEmpty() ? stack2.pop().val : 0;
            sum += num1 + num2;

            carry = sum / 10;
            ListNode newNode = new ListNode(sum % 10);
            newNode.next = result;
            result = newNode;
        }

        return result;
    }

    class Twitter {
        private static int timestamp = 0;
        private Map<Integer, Set<Integer>> followerMap;
        private Map<Integer, List<Tweet>> tweetsMap;

        class Tweet {
            int tweetId;
            int time;

            public Tweet(int tweetId, int time) {
                this.tweetId = tweetId;
                this.time = time;
            }
        }

        public Twitter() {
            followerMap = new HashMap<>();
            tweetsMap = new HashMap<>();
        }

        public void postTweet(int userId, int tweetId) {
            if (!tweetsMap.containsKey(userId)) {
                tweetsMap.put(userId, new ArrayList<>());
            }
            tweetsMap.get(userId).add(new Tweet(tweetId, timestamp++));
        }

        public List<Integer> getNewsFeed(int userId) {
            Queue<Tweet> maxHeap = new PriorityQueue<>((a, b) -> b.time - a.time);

            if (tweetsMap.containsKey(userId)) {
                maxHeap.addAll(tweetsMap.get(userId));
            }

            Set<Integer> followeesId = followerMap.getOrDefault(userId, new HashSet<>());
            for (int followeeId : followeesId) {
                if (tweetsMap.containsKey(followeeId)) {
                    maxHeap.addAll(tweetsMap.get(followeeId));
                }
            }

            List<Integer> result = new ArrayList<>();
            int count = 0;
            while (!maxHeap.isEmpty() && count < 10) {
                result.add(maxHeap.poll().tweetId);
                count++;
            }

            return result;
        }

        public void follow(int followerId, int followeeId) {
            if (followerId == followeeId) {
                return;
            }
//            if (!followerMap.containsKey(followerId)) {
//                followerMap.put(followerId, new HashSet<>());
//            }
//            followerMap.get(followerId).add(followeeId);

            followerMap.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            if (followerMap.containsKey(followerId)) {
                followerMap.get(followerId).remove(followeeId);
            }
        }
    }

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (fast == slow) {
                break;
            }
        }

        if (fast == null || fast.next == null) {
            return null;
        }

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    public ListNode modifiedList(int[] nums, ListNode head) {
        int length = nums.length;
        ListNode dummy = new ListNode();
        ListNode current = dummy;

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        while (head != null) {
            while (set.contains(head.val)) {
                head = head.next;
                if (head == null) {
                    break;
                }
            }

            current.next = head;
            current = current.next;
            if (head != null) {
                head = head.next;
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {
//        ListNode l1 = new ListNode(1);
//        ListNode l2 = new ListNode(2);
//        ListNode l3 = new ListNode(3);
//        ListNode l4 = new ListNode(4);
//
//        l1.next = l2;
//        l2.next = l3;
//        l3.next = l4;
//        printListNode(l1);
//
//        Exercise exercise = new Exercise();
//        exercise.reorderList(l1);
//        printListNode(l1);


        Queue<Integer> queue = new LinkedList<>();
        queue.add(4);
        queue.add(5);
        queue.add(9);
        queue.remove(5);
        System.out.println(queue);
    }
}
