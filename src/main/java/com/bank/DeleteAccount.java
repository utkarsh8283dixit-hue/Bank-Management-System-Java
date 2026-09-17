package com.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class DeleteAccount {

    public static boolean delete(Scanner sc, int accountNo) {

        System.out.println("\n===== DELETE ACCOUNT =====");

        System.out.print("Enter your PIN: ");
        String pin = sc.next();

        System.out.print("Are you sure you want to delete your account? (yes/no): ");
        String confirm = sc.next();

        if (!confirm.equalsIgnoreCase("yes")) {
            System.out.println("Account deletion cancelled.");
            return false;
        }

        String checkSql =
                "SELECT account_no FROM accounts WHERE account_no = ? AND pin = ?";

        String transactionSql =
                "DELETE FROM transactions WHERE account_no = ?";

        String deleteSql =
                "DELETE FROM accounts WHERE account_no = ? AND pin = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement checkPs =
                    con.prepareStatement(checkSql);

            checkPs.setInt(1, accountNo);
            checkPs.setString(2, pin);

            var rs = checkPs.executeQuery();

            if (!rs.next()) {
                System.out.println("Incorrect PIN!");
                con.close();
                return false;
            }

            // Delete transaction history
            PreparedStatement transactionPs =
                    con.prepareStatement(transactionSql);

            transactionPs.setInt(1, accountNo);
            transactionPs.executeUpdate();

            // Delete account
            PreparedStatement deletePs =
                    con.prepareStatement(deleteSql);

            deletePs.setInt(1, accountNo);
            deletePs.setString(2, pin);

            int rows = deletePs.executeUpdate();

            if (rows > 0) {

                System.out.println("Account deleted successfully!");

                con.close();

                return true;

            } else {

                System.out.println("Account deletion failed!");

                con.close();

                return false;
            }

        } catch (Exception e) {

            System.out.println("Account deletion failed!");
            e.printStackTrace();

            return false;
        }
    }
}