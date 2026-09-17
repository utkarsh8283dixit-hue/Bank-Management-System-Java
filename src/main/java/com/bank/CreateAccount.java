package com.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class CreateAccount {

    public static void create(Scanner sc) {

        sc.nextLine(); // clear buffer

        System.out.println("\n===== CREATE ACCOUNT =====");

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();

        System.out.print("Create PIN: ");
        String pin = sc.nextLine();

        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();

        String sql = "INSERT INTO accounts (name, phone, pin, balance) VALUES (?, ?, ?, ?)";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, pin);
            ps.setDouble(4, balance);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                int accountNo = rs.getInt(1);

                System.out.println("\nAccount Created Successfully!");
                System.out.println("Your Account Number: " + accountNo);
            }

            con.close();

        } catch (Exception e) {
            System.out.println("Account Creation Failed!");
            e.printStackTrace();
        }
    }
}
