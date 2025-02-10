package com.mycompany.banksystem;

import Connectivity.DB;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * BankTransactionService provides services for handling bank transactions, such as
 * transferring funds between accounts and logging transactions.
 * It includes functionality for both GUI and console modes.
 * 
 * @version 1.0
 * @since 2024
 */
public class BankTransactionService {

    Connection con = DB.Con();  // Set up DB connection

    /**
     * Transfers funds between two accounts, updating balances and logging the transaction.
     * Supports both GUI and console modes.
     * 
     * @param senderAccountNumber Account number of the sender
     * @param recipientAccountNumber Account number of the recipient
     * @param amount Amount to transfer
     * @param fromGUI Whether the transfer is initiated from GUI (true) or console (false)
     */
    public void transferFunds(int senderAccountNumber, int recipientAccountNumber, double amount, boolean fromGUI) {
        String checkBalanceQuery = "SELECT balance FROM Accounts WHERE accountNumber = ?";
        String updateSenderQuery = "UPDATE Accounts SET balance = balance - ? WHERE accountNumber = ?";
        String updateRecipientQuery = "UPDATE Accounts SET balance = balance + ? WHERE accountNumber = ?";
        String getUserQuery = "SELECT username FROM Users WHERE userId = (SELECT userId FROM Accounts WHERE accountNumber = ?)";
        String transactionLog = "INSERT INTO Transactions (accountNumber, transactionType, amount) VALUES (?, ?, ?)";

        try {
            // Step 1: Retrieve sender and recipient names
            PreparedStatement senderStmt = con.prepareStatement(getUserQuery);
            senderStmt.setInt(1, senderAccountNumber);
            ResultSet senderRs = senderStmt.executeQuery();

            PreparedStatement recipientStmt = con.prepareStatement(getUserQuery);
            recipientStmt.setInt(1, recipientAccountNumber);
            ResultSet recipientRs = recipientStmt.executeQuery();

            if (senderRs.next() && recipientRs.next()) {
                String senderName = senderRs.getString("username");
                String recipientName = recipientRs.getString("username");

                // Step 2: Check sender's balance and perform transfer
                PreparedStatement balanceStmt = con.prepareStatement(checkBalanceQuery);
                balanceStmt.setInt(1, senderAccountNumber);
                ResultSet rs = balanceStmt.executeQuery();

                if (rs.next()) {
                    double senderBalance = rs.getDouble("balance");

                    if (senderBalance >= amount) {
                        // Step 3: Update sender and recipient accounts
                        updateAccountBalance(con, updateSenderQuery, amount, senderAccountNumber);
                        updateAccountBalance(con, updateRecipientQuery, amount, recipientAccountNumber);

                        // Log the transaction
                        logTransaction(con, transactionLog, senderAccountNumber, "Transfer Sent", amount);
                        logTransaction(con, transactionLog, recipientAccountNumber, "Transfer Received", amount);

                        // Step 4: Generate the receipt
                        String receipt = generateReceipt(senderName, senderAccountNumber, recipientName, recipientAccountNumber, amount);

                        // Step 5: Handle saving the receipt (GUI vs Console)
                        if (fromGUI) {
                            JOptionPane.showMessageDialog(null, receipt, "Transfer Receipt", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            Scanner scanner = new Scanner(System.in);
                            System.out.print("Would you like to save the receipt to a file? (yes/no): ");
                            String saveOption = scanner.nextLine();
                            if (saveOption.equalsIgnoreCase("yes")) {
                                saveReceiptToFile(receipt);
                            }
                        }

                    } else {
                        if (fromGUI) {
                            JOptionPane.showMessageDialog(null, "Insufficient balance. Transfer denied.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            System.out.println("Insufficient balance. Transfer denied.");
                        }
                    }

                } else {
                    showError("Sender account not found.", fromGUI);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates a formatted receipt for a transfer transaction.
     * 
     * @param senderName Name of the sender
     * @param senderAccount Sender's account number
     * @param recipientName Name of the recipient
     * @param recipientAccount Recipient's account number
     * @param amount Amount transferred
     * @return A formatted string representing the receipt
     */
    public static String generateReceipt(String senderName, int senderAccount, String recipientName, int recipientAccount, double amount) {
        StringBuilder receipt = new StringBuilder();
        receipt.append("==================== Transfer Receipt ====================\n");
        receipt.append("Sender Name: ").append(senderName).append("\n");
        receipt.append("Sender Account: ").append(senderAccount).append("\n");
        receipt.append("Recipient Name: ").append(recipientName).append("\n");
        receipt.append("Recipient Account: ").append(recipientAccount).append("\n");
        receipt.append("Amount Transferred: R").append(amount).append("\n");
        receipt.append("Transfer Date: ").append(new java.util.Date()).append("\n");
        receipt.append("===========================================================\n");
        return receipt.toString();
    }

    /**
     * Saves the receipt to a text file.
     * 
     * @param receipt The receipt content to save
     */
    private static void saveReceiptToFile(String receipt) {
        try (FileWriter fileWriter = new FileWriter("transfer_receipt.txt")) {
            fileWriter.write(receipt);
            System.out.println("Receipt saved to transfer_receipt.txt.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the receipt.");
            e.printStackTrace();
        }
    }

    /**
     * Updates the balance of an account based on a query.
     * 
     * @param con Database connection
     * @param query SQL update query
     * @param amount Amount to adjust in balance
     * @param accountNumber Account number to update
     * @throws SQLException if an error occurs in database interaction
     */
    private static void updateAccountBalance(Connection con, String query, double amount, int accountNumber) throws SQLException {
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setDouble(1, amount);
            stmt.setInt(2, accountNumber);
            stmt.executeUpdate();
        }
    }

    /**
     * Logs a transaction in the database for record-keeping.
     * 
     * @param con Database connection
     * @param query SQL insert query for logging
     * @param accountNumber Account number for the transaction
     * @param type Transaction type (e.g., "Transfer Sent")
     * @param amount Transaction amount
     * @throws SQLException if an error occurs in database interaction
     */
    private static void logTransaction(Connection con, String query, int accountNumber, String type, double amount) throws SQLException {
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, accountNumber);
            stmt.setString(2, type);
            stmt.setDouble(3, amount);
            stmt.executeUpdate();
        }
    }

    /**
     * Displays an error message in the appropriate mode (GUI or console).
     * 
     * @param message The error message to display
     * @param fromGUI Whether the error message is for GUI mode (true) or console (false)
     */
    private static void showError(String message, boolean fromGUI) {
        if (fromGUI) {
            JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            System.out.println(message);
        }
    }
}
