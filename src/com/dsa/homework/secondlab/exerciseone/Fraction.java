package com.dsa.homework.secondlab.exerciseone;

import java.util.Arrays;
import java.util.Scanner;

public class Fraction {
    private float numerator;
    private float denominator;

    public Fraction(float numerator, float denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction add(Fraction c) {
        float newNumerator = numerator * c.denominator + c.numerator * denominator;
        float newDenominator = this.denominator * c.denominator;

        return normalize(new Fraction(newNumerator, newDenominator));
    }

    public Fraction minus(Fraction c) {
        float newNumerator = numerator * c.denominator - c.numerator * denominator;
        float newDenominator = this.denominator * c.denominator;

        return normalize(new Fraction(newNumerator, newDenominator));
    }

    public Fraction multi(Fraction c) {
        float newNumerator = this.numerator * c.numerator;
        float newDenominator = this.denominator * c.denominator;
        return normalize(new Fraction(newNumerator, newDenominator));
    }

    public Fraction divi(Fraction c) {
        if (c.denominator == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }

        float newNumerator = this.numerator * c.denominator;
        float newDenominator = this.denominator * c.numerator;
        return normalize(new Fraction(newNumerator, newDenominator));
    }

    public Fraction normalize(Fraction c) {
        float gcd = gcd(Math.abs(c.numerator), Math.abs(c.denominator));
        c.numerator /= gcd;
        c.denominator /= gcd;

        if (c.denominator < 0) {
            c.numerator = -c.numerator;
            c.denominator = -c.denominator;
        }

        return c;
    }

    private float gcd(float a, float b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public float getNumerator() {
        return numerator;
    }

    public float getDenominator() {
        return denominator;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số lượng phân số n: ");
        int n = scanner.nextInt();
        Fraction[] fractions = new Fraction[n];

        for (int i = 0; i < n; i++) {
            float numerator = scanner.nextFloat();
            float denominator = scanner.nextFloat();
            fractions[i] = new Fraction(numerator, denominator);
        }
        System.out.println(Arrays.toString(fractions));

        System.out.print("Nhập vị trí phân số cần in ra: ");
        int v = scanner.nextInt();
        if (v >= 0 && v < n) {
            System.out.println("Phân số ở vị trí thứ " + v + " là: " + fractions[v]);
        } else {
            System.out.println("Vị trí không hợp lệ.");
        }

        Fraction sum = new Fraction(0, 1);
        for (Fraction fraction : fractions) {
            sum = sum.add(fraction);
        }
        System.out.println("Tổng của n phân số là: " + sum);

        Fraction difference = fractions[0];
        for (int i = 1; i < fractions.length; i++) {
            difference = difference.minus(fractions[i]);
        }
        System.out.println("Hiệu của n phân số là: " + difference);

        Fraction product = new Fraction(1, 1);
        for (Fraction fraction : fractions) {
            product = product.multi(fraction);
        }
        System.out.println("Tích của n phân số là: " + product);

        Fraction quotient = fractions[0];
        for (int i = 1; i < n; i++) {
            quotient = quotient.divi(fractions[i]);
        }
        System.out.println("Thương của n phân số là: " + quotient);

        // (*) Yêu cầu bổ sung: Tính giá trị thực của phân số
        System.out.println("Giá trị thực của tổng phân số là: " + (sum.getNumerator() / sum.getDenominator()));
    }
}
