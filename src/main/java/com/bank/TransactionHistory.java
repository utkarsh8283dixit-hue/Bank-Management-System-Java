package com.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class TransactionHistory {

    public static void show(int accountNo) {

        String sql = "SELECT transaction_id, type, amount, transaction_date " +
                "FROM transactions WHERE account_no = ? " +
                "ORDER BY transaction_date DESC";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, accountNo);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n===== TRANSACTION HISTORY =====");

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println(
                        "ID: " + rs.getInt("transaction_id") +
                                " | Type: " + rs.getString("type") +
                                " | Amount: ₹" + rs.getDouble("amount") +
                                " | Date: " + rs.getTimestamp("transaction_date")
                );
            }

            if (!found) {
                System.out.println("No transactions found.");
            }

            con.close();

        } catch (Exception e) {
            System.out.println("Unable to fetch transaction history!");
            e.printStackTrace();
        }
    }
}
