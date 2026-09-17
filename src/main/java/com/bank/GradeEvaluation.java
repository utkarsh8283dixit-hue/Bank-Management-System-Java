package com.bank;
import java.util.Scanner;

public class GradeEvaluation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Apne marks dalein (0 - 100): ");
        int marks = sc.nextInt();

        if (marks < 0 || marks > 100) {
            System.out.println("Kripya 0 se 100 ke beech valid marks dalein.");
        } else if (marks >= 80) {
            System.out.println("Grade: A");
        } else if (marks >= 60) {
            System.out.println("Grade: B");
        } else if (marks >= 40) {
            System.out.println("Grade: C");
        } else {
            System.out.println("Grade: Fail");
        }

        sc.close();
    }
}