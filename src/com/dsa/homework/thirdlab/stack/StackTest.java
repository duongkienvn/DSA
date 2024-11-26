package com.dsa.homework.thirdlab.stack;

import java.util.Iterator;
import java.util.Stack;

public class StackTest {
    public static void main(String[] args) {
        // Kiểm tra ArrayStack
        System.out.println("Testing ArrayStack:");
        ArrayStack<Integer> arrayStack = new ArrayStack<>();

        // Thêm phần tử vào stack
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);


        // Kiểm tra giá trị ở đỉnh stack
        System.out.println("Top element: " + arrayStack.top()); // 3

        // Kiểm tra pop và in ra các phần tử
        while (!arrayStack.isEmpty()) {
            System.out.println("Popped: " + arrayStack.pop());
        }

        // Kiểm tra stack có rỗng không
        System.out.println("Is stack empty? " + arrayStack.isEmpty()); // true


        // Kiểm tra LinkedListStack
        System.out.println("\nTesting LinkedListStack:");
        LinkedListStack<String> linkedListStack = new LinkedListStack<>();

        // Thêm phần tử vào stack
        linkedListStack.push("A");
        linkedListStack.push("B");
        linkedListStack.push("C");

        Iterator iterator = linkedListStack.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        Iterator iterator1 = stack.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator1.next());
        }

        // Kiểm tra giá trị ở đỉnh stack
        System.out.println("Top element: " + linkedListStack.top()); // C

        // Kiểm tra pop và in ra các phần tử
        while (!linkedListStack.isEmpty()) {
            System.out.println("Popped: " + linkedListStack.pop());
        }

        // Kiểm tra stack có rỗng không
        System.out.println("Is stack empty? " + linkedListStack.isEmpty()); // true
    }
}
