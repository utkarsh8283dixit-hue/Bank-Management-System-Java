package com.bank;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n================================");
            System.out.println("     BANK MANAGEMENT SYSTEM");
            System.out.println("================================");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
            System.out.println("================================");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    CreateAccount.create(sc);
                    break;

                case 2:
                    Login.login(sc);
                    break;

                case 3:
                    AdminLogin.login(sc);
                    break;

                case 4:
                    System.out.println("Thank you for using Bank Management System!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}