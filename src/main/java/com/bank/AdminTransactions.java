package com.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class AdminTransactions {
    public static void show() {
        String sql = "SELECT transaction_id, account_no, type, amount, transaction_date " +
                "FROM transactions ORDER BY transaction_date DESC";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            System.out.println("\n===== ALL TRANSACTIONS =====");
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println(
                        "ID: " + rs.getInt("transaction_id") +
                                " | Account No: " + rs.getInt("account_no") +
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
            System.out.println("Unable to fetch transactions!");
            e.printStackTrace();
        }
    }
}
