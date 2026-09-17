package com.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class BankMenu {

    public static void show(Scanner sc, int accountNo) {

        while (true) {

            System.out.println("1. Account Details");
            System.out.println("2. Check Balance");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Transaction History");
            System.out.println("6. Change PIN");
            System.out.println("7. Delete Account");
            System.out.println("8. Logout");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    AccountsDetails.show(accountNo);
                    break;

                case 2:
                    checkBalance(accountNo);
                    break;

                case 3:
                    deposit(sc, accountNo);
                    break;

                case 4:
                    withdraw(sc, accountNo);
                    break;

                case 5:
                    TransactionHistory.show(accountNo);
                    break;

                case 6:
                    ChangePin.change(sc, accountNo);
                    break;

                case 7:
                    if (DeleteAccount.delete(sc, accountNo)) {
                        System.out.println("You have been logged out.");
                        return;
                    }
                    break;

                case 8:
                    System.out.println("Logged out successfully!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static void checkBalance(int accountNo) {

        String sql = "SELECT balance FROM accounts WHERE account_no = ?";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, accountNo);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("balance");

                System.out.println("Current Balance: ₹" + balance);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deposit(Scanner sc, int accountNo) {

        System.out.print("Enter amount to deposit: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }

        String sql = "UPDATE accounts SET balance = balance + ? WHERE account_no = ?";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDouble(1, amount);
            ps.setInt(2, accountNo);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                String transactionSql =
                        "INSERT INTO transactions (account_no, type, amount) VALUES (?, ?, ?)";

                PreparedStatement transactionPs =
                        con.prepareStatement(transactionSql);

                transactionPs.setInt(1, accountNo);
                transactionPs.setString(2, "DEPOSIT");
                transactionPs.setDouble(3, amount);

                transactionPs.executeUpdate();

                System.out.println("Money deposited successfully!");

            } else {

                System.out.println("Account not found!");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void withdraw(Scanner sc, int accountNo) {

        System.out.print("Enter amount to withdraw: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }

        String sql = "UPDATE accounts SET balance = balance - ? " +
                "WHERE account_no = ? AND balance >= ?";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDouble(1, amount);
            ps.setInt(2, accountNo);
            ps.setDouble(3, amount);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                String transactionSql =
                        "INSERT INTO transactions (account_no, type, amount) VALUES (?, ?, ?)";

                PreparedStatement transactionPs =
                        con.prepareStatement(transactionSql);

                transactionPs.setInt(1, accountNo);
                transactionPs.setString(2, "WITHDRAW");
                transactionPs.setDouble(3, amount);

                transactionPs.executeUpdate();

                System.out.println("Money withdrawn successfully!");

            } else {

                System.out.println("Insufficient balance!");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
