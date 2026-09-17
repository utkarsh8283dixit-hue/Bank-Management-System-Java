package com.bank;
import java.util.Scanner;

public class Calculater {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Pehla number dalein: ");
        double num1 = sc.nextDouble();

        System.out.print("Operator chunein (+, -, *, /): ");
        char operator = sc.next().charAt(0);

        System.out.print("Dusra number dalein: ");
        double num2 = sc.nextDouble();

        double result;

        switch (operator) {
            case '+':
                result = num1 + num2;
                System.out.println("Result: " + result);
                break;
            case '-':
                result = num1 - num2;
                System.out.println("Result: " + result);
                break;
            case '*':
                result = num1 * num2;
                System.out.println("Result: " + result);
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                    System.out.println("Result: " + result);
                } else {
                    System.out.println("Error: Zero se divide nahi kar sakte!");
                }
                break;
            default:
                System.out.println("Galat operator chuna hai.");
        }
        sc.close();
    }
}
