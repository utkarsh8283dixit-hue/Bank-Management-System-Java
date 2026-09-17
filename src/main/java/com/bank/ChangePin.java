package com.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class ChangePin {

    public static void change(Scanner sc, int accountNo) {

        System.out.println("\n===== CHANGE PIN =====");

        System.out.print("Enter Current PIN: ");
        String currentPin = sc.next();

        System.out.print("Enter New PIN: ");
        String newPin = sc.next();

        String sql = "UPDATE accounts SET pin = ? WHERE account_no = ? AND pin = ?";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, newPin);
            ps.setInt(2, accountNo);
            ps.setString(3, currentPin);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("PIN changed successfully!");
            } else {
                System.out.println("Current PIN is incorrect!");
            }

            con.close();

        } catch (Exception e) {
            System.out.println("PIN change failed!");
            e.printStackTrace();
        }
    }
}
