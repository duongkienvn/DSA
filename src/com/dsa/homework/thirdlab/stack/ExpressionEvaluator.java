package com.dsa.homework.thirdlab.stack;

import java.util.Scanner;

public class ExpressionEvaluator {
    public static boolean isValidExpression(String expression) {
        LinkedListStack<Character> stack = new LinkedListStack<>();
        for (char c : expression.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
                stack.pop();
            }
        }

        return stack.isEmpty();
    }

    public static int evaluate(String expression) {
        LinkedListStack<String> numbers = new LinkedListStack<>();
        LinkedListStack<String> operators = new LinkedListStack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                StringBuilder number = new StringBuilder(String.valueOf(c));
                while (Character.isDigit(i + 1)) {
                    number.append(expression.charAt(i + 1));
                    i++;
                }
                numbers.push(number.toString());
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                operators.push(String.valueOf(c));
            } else if (c == ')') {
                int num2 = Integer.parseInt(numbers.pop());
                int num1 = Integer.parseInt(numbers.pop());
                String operator = operators.pop();
                int result = 0;
                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        result = num1 / num2;
                        break;
                }
                numbers.push(String.valueOf(result));
            }
        }

        return Integer.parseInt(numbers.pop());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        while (t-- > 0) {
            String s = sc.nextLine();
            System.out.println(evaluate(s));
        }
    }
}
