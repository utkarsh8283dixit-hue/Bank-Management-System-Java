package com.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class AdminMenu {

    public static void show(Scanner sc) {

        while (true) {

            System.out.println("\n===== ADMIN MENU =====");

            System.out.println("1. View All Accounts");
            System.out.println("2. Total Accounts");
            System.out.println("3. View All Transactions");
            System.out.println("4. Total Bank Balance");
            System.out.println("5. Search Account");
            System.out.println("6. Delete Account");
            System.out.println("7. Logout");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    viewAllAccounts();
                    break;

                case 2:
                    totalAccounts();
                    break;

                case 3:
                    AdminTransactions.show();
                    break;

                case 4:
                    totalBankBalance();
                    break;

                case 5:
                    searchAccount(sc);
                    break;

                case 6:
                    deleteAccount(sc);
                    break;

                case 7:
                    System.out.println("Admin logged out successfully!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static void viewAllAccounts() {

        String sql = "SELECT account_no, name, phone, balance FROM accounts";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n===== ALL ACCOUNTS =====");

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println(
                        "Account No: " + rs.getInt("account_no") +
                                " | Name: " + rs.getString("name") +
                                " | Phone: " + rs.getString("phone") +
                                " | Balance: ₹" + rs.getDouble("balance")
                );
            }

            if (!found) {
                System.out.println("No accounts found.");
            }

            con.close();

        } catch (Exception e) {

            System.out.println("Unable to fetch accounts!");
            e.printStackTrace();
        }
    }

    public static void totalAccounts() {

        String sql = "SELECT COUNT(*) AS total FROM accounts";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int total = rs.getInt("total");

                System.out.println("\nTotal Accounts: " + total);
            }

            con.close();

        } catch (Exception e) {

            System.out.println("Unable to count accounts!");
            e.printStackTrace();
        }
    }
    public static void totalBankBalance() {

        String sql = "SELECT SUM(balance) AS total_balance FROM accounts";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                double totalBalance = rs.getDouble("total_balance");

                System.out.println("\n===== TOTAL BANK BALANCE =====");
                System.out.println("Total Balance: ₹" + totalBalance);
            }

            con.close();

        } catch (Exception e) {

            System.out.println("Unable to calculate total bank balance!");
            e.printStackTrace();
        }
    }
    public static void searchAccount(Scanner sc) {

        System.out.println("\n===== SEARCH ACCOUNT =====");

        System.out.print("Enter Account Number: ");
        int accountNo = sc.nextInt();

        String sql = "SELECT account_no, name, phone, balance " +
                "FROM accounts WHERE account_no = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, accountNo);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("\n===== ACCOUNT FOUND =====");

                System.out.println("Account Number: "
                        + rs.getInt("account_no"));

                System.out.println("Name: "
                        + rs.getString("name"));

                System.out.println("Phone: "
                        + rs.getString("phone"));

                System.out.println("Balance: ₹"
                        + rs.getDouble("balance"));

                System.out.println("=========================");

            } else {

                System.out.println("Account not found!");

            }

            con.close();

        } catch (Exception e) {

            System.out.println("Unable to search account!");
            e.printStackTrace();
        }
    }
    public static void deleteAccount(Scanner sc) {

        System.out.println("\n===== ADMIN DELETE ACCOUNT =====");

        System.out.print("Enter Account Number: ");
        int accountNo = sc.nextInt();

        System.out.print("Are you sure you want to delete this account? (yes/no): ");
        String confirm = sc.next();

        if (!confirm.equalsIgnoreCase("yes")) {
            System.out.println("Account deletion cancelled.");
            return;
        }

        String transactionSql =
                "DELETE FROM transactions WHERE account_no = ?";

        String deleteSql =
                "DELETE FROM accounts WHERE account_no = ?";

        try {

            Connection con = DBConnection.getConnection();

            // Delete transaction history
            PreparedStatement transactionPs =
                    con.prepareStatement(transactionSql);

            transactionPs.setInt(1, accountNo);
            transactionPs.executeUpdate();

            // Delete account
            PreparedStatement deletePs =
                    con.prepareStatement(deleteSql);

            deletePs.setInt(1, accountNo);

            int rows = deletePs.executeUpdate();

            if (rows > 0) {
                System.out.println("Account deleted successfully!");
            } else {
                System.out.println("Account not found!");
            }

            con.close();

        } catch (Exception e) {

            System.out.println("Account deletion failed!");
            e.printStackTrace();
        }
    }
}
