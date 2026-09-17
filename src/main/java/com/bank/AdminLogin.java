package com.bank;

import java.util.Scanner;

public class AdminLogin {

    public static void login(Scanner sc) {

        System.out.println("\n===== ADMIN LOGIN =====");

        System.out.print("Enter Admin Username: ");
        String username = sc.next();

        System.out.print("Enter Admin Password: ");
        String password = sc.next();

        if (username.equals("admin") && password.equals("admin123")) {

            System.out.println("\nAdmin Login Successful!");
            System.out.println("Welcome, Admin!");

            AdminMenu.show(sc);

        } else {

            System.out.println("\nInvalid Admin Username or Password!");

        }
    }
}
