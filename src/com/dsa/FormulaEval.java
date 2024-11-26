package com.dsa;

public class FormulaEval {

    public double eval(String formula) {
        LinkedListStack<Double> stack = new LinkedListStack<>();
        String[] tokens = convertToRPN(formula).split(" ");

        for (String token : tokens) {
            if (isNumber(token)) {
                stack.push(Double.parseDouble(token));
            } else {
                double b = stack.pop();
                double a = stack.pop();
                switch (token) {
                    case "+" -> stack.push(a + b);
                    case "-" -> stack.push(a - b);
                    case "*" -> stack.push(a * b);
                    case "/" -> stack.push(a / b);
                }
            }
        }
        return stack.pop();
    }

    private String convertToRPN(String formula) {
        // Implement the Shunting Yard algorithm to convert `formula` to RPN.
        return ""; // Placeholder, implement RPN conversion logic here.
    }

    private boolean isNumber(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

