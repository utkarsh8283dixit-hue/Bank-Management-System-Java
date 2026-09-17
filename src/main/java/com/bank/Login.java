package com.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Login {

    public static void login(Scanner sc) {

        System.out.println("\n===== LOGIN =====");

        System.out.print("Enter Account Number: ");
        int accountNo = sc.nextInt();

        System.out.print("Enter PIN: ");
        String pin = sc.next();

        String sql = "SELECT name FROM accounts WHERE account_no = ? AND pin = ?";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, accountNo);
            ps.setString(2, pin);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String name = rs.getString("name");

                System.out.println("\nLogin Successful!");
                System.out.println("Welcome, " + name + "!");

                BankMenu.show(sc, accountNo);

            } else {

                System.out.println("\nInvalid Account Number or PIN!");

            }

            con.close();

        } catch (Exception e) {

            System.out.println("Login Failed!");
            e.printStackTrace();

        }
    }
}