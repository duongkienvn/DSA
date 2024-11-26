package com.dsa.homework.fifthlab.tree;

import java.util.Arrays;
import java.util.Stack;

class BinaryTreeNode {
    String value;
    BinaryTreeNode left, right;

    public BinaryTreeNode(String value) {
        this.value = value;
        left = right = null;
    }
}

public class ExpressionTree<E> extends LinkedBinaryTree {

    public int precedence(char c) {
        switch (c) {
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return -1;
        }
    }

    public String[] infixToPostfix(String[] tokens) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        String exp = Arrays.stream(tokens).reduce("", (a, b) -> a + b);

        for (char c : exp.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c)) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString().split("");
    }

    public boolean isOperand(String token) {
        return Character.isDigit(token.charAt(0));
    }

    public BinaryTreeNode constructExpressionTree(String[] postfix) {
        Stack<BinaryTreeNode> stack = new Stack<>();

        for (String token : postfix) {
            BinaryTreeNode node = new BinaryTreeNode(token);

            if (isOperand(token)) {
                stack.push(node);
            } else {
                node.right = stack.pop();
                node.left = stack.pop();
                stack.push(node);
            }
        }

        return stack.peek();
    }

    public void preorderPrint(Node<E> p) {
        if (p == null) {
            return;
        }
        System.out.print(p.getElement() + " ");
        preorderPrint(p.getLeft());
        preorderPrint(p.getRight());
    }

    public void inorderPrint(Node<E> p) {
        if (p == null) {
            return;
        }
        if (p.getLeft() != null) {
            System.out.print("( ");
        }
        inorderPrint(p.getLeft());
        System.out.print(p.getElement() + " ");
        inorderPrint(p.getRight());
        if (p.getRight() != null) {
            System.out.print(") ");
        }
    }

    public void postorderPrint(Node<E> p) {
        if (p == null) {
            return;
        }
        postorderPrint(p.getLeft());
        postorderPrint(p.getRight());
        System.out.print(p.getElement() + " ");
    }

    public double evaluate(Node<String> p) {
        if (p.getLeft() == null && p.getRight() == null) {
            return Double.parseDouble(p.getElement());
        }
        double leftVal = evaluate(p.getLeft());
        double rightVal = evaluate(p.getRight());
        String operator = p.getElement();
        switch (operator) {
            case "+":
                return leftVal + rightVal;
            case "-":
                return leftVal - rightVal;
            case "*":
                return leftVal * rightVal;
            case "/":
                return leftVal / rightVal;
            default:
                throw new IllegalArgumentException();
        }
    }
}
