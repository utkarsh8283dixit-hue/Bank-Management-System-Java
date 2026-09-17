package com.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountsDetails {

    public static void show(int accountNo) {

        String sql = "SELECT account_no, name, phone, balance " +
                "FROM accounts WHERE account_no = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, accountNo);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("\n===== ACCOUNT DETAILS =====");

                System.out.println("Account Number: "
                        + rs.getInt("account_no"));

                System.out.println("Name: "
                        + rs.getString("name"));

                System.out.println("Phone: "
                        + rs.getString("phone"));

                System.out.println("Balance: ₹"
                        + rs.getDouble("balance"));

                System.out.println("===========================");

            } else {

                System.out.println("Account details not found!");

            }

            con.close();

        } catch (Exception e) {

            System.out.println("Unable to fetch account details!");
            e.printStackTrace();

        }
    }
}
