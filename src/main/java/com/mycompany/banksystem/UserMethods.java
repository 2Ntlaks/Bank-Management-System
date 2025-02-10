package com.mycompany.banksystem;

import Connectivity.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UserMethods 
{
    private static Connection con = DB.Con();  // Set up DB connection

    // Method for user to check their account balance
   public static double checkBalance(int accountNumber) {
    String query = "SELECT balance FROM Accounts WHERE accountNumber = ?";
    try (PreparedStatement stmt = con.prepareStatement(query)) {
        stmt.setInt(1, accountNumber);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getDouble("balance");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0.0;  // Return 0 if the balance cannot be found
}


    // Method to deposit funds into an account
   public static void depositFunds(int accountNumber, double amount) {
    String query = "UPDATE Accounts SET balance = balance + ? WHERE accountNumber = ?";
    String transactionLog = "INSERT INTO Transactions (accountNumber, transactionType, amount) VALUES (?, ?, ?)";

    try {
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setDouble(1, amount);
        stmt.setInt(2, accountNumber);
        stmt.executeUpdate();

        // Log the transaction
        PreparedStatement logStmt = con.prepareStatement(transactionLog);
        logStmt.setInt(1, accountNumber);
        logStmt.setString(2, "Deposit");
        logStmt.setDouble(3, amount);
        logStmt.executeUpdate();

        System.out.println("Deposit successful! Amount deposited: R" + amount);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    // Method to withdraw funds from an account
    public static void withdrawFunds(int accountNumber, double amount) {
    String query = "UPDATE Accounts SET balance = balance - ? WHERE accountNumber = ?";
    String transactionLog = "INSERT INTO Transactions (accountNumber, transactionType, amount) VALUES (?, ?, ?)";

    try {
        PreparedStatement balanceStmt = con.prepareStatement("SELECT balance FROM Accounts WHERE accountNumber = ?");
        balanceStmt.setInt(1, accountNumber);
        ResultSet rs = balanceStmt.executeQuery();

        if (rs.next()) {
            double currentBalance = rs.getDouble("balance");

            if (currentBalance >= amount) {
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setDouble(1, amount);
                stmt.setInt(2, accountNumber);
                stmt.executeUpdate();

                // Log the transaction
                PreparedStatement logStmt = con.prepareStatement(transactionLog);
                logStmt.setInt(1, accountNumber);
                logStmt.setString(2, "Withdrawal");
                logStmt.setDouble(3, amount);
                logStmt.executeUpdate();

                System.out.println("Withdrawal successful! Amount withdrawn: R" + amount);
            } else {
                System.out.println("Insufficient balance. Withdrawal denied.");
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

   public static int checkCredentials(String username, String password) {
    String userQuery = "SELECT userId FROM Users WHERE username = ? AND password = ?";

    try (PreparedStatement stmt = con.prepareStatement(userQuery)) {
        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            // Fetch the user's account number from the Accounts table using userId
            int userId = rs.getInt("userId");
            return getAccountNumber(userId);  // Retrieve account number using userId
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return -1;  // Return -1 if credentials are invalid
}
   // New method to fetch account number using userId
public static int getAccountNumber(int userId) {
    String accountQuery = "SELECT accountNumber FROM Accounts WHERE userId = ?";

    try (PreparedStatement stmt = con.prepareStatement(accountQuery)) {
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt("accountNumber");  // Return account number
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return -1;  // Return -1 if no account is found
}

//method to view Transaction history
public static void viewTransactionHistory(int accountNumber) {
    String query = "SELECT transactionType, amount, transactionDate FROM Transactions WHERE accountNumber = ? ORDER BY transactionDate DESC";

    try {
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, accountNumber);
        ResultSet rs = stmt.executeQuery();

        System.out.println("*******************************************************************");
        System.out.println("Transaction History for Account Number: " + accountNumber);
        System.out.println("*******************************************************************");
        
        while (rs.next()) 
        {
            String type = rs.getString("transactionType");
            double amount = rs.getDouble("amount");
            Timestamp date = rs.getTimestamp("transactionDate");
            System.out.println(type + " of R" + amount + " on " + date);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
//method to login user
public static void userLogin(String username, String password, String role) {
    String query = "SELECT * FROM Users WHERE username = ? AND role = ?";
    String updateLockStatus = "UPDATE Users SET isLocked = TRUE WHERE username = ?";
    String updateFailedAttempts = "UPDATE Users SET failedAttempts = ? WHERE username = ?";
    String resetFailedAttempts = "UPDATE Users SET failedAttempts = 0 WHERE username = ?";

    try {
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, role);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            boolean isLocked = rs.getBoolean("isLocked");
            int failedAttempts = rs.getInt("failedAttempts");

            if (isLocked) {
                JOptionPane.showMessageDialog(null, "Account is locked. Please contact admin.");
            } else {
                if (rs.getString("password").equals(password)) {
                    // Successful login
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    
                    // Reset failed attempts after successful login
                    PreparedStatement resetStmt = con.prepareStatement(resetFailedAttempts);
                    resetStmt.setString(1, username);
                    resetStmt.executeUpdate();
                    
                } else {
                    // Increase failed attempts
                    failedAttempts++;
                    if (failedAttempts >= 3) {
                        // Lock the account after 3 failed attempts
                        PreparedStatement lockStmt = con.prepareStatement(updateLockStatus);
                        lockStmt.setString(1, username);
                        lockStmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Account locked after multiple failed login attempts.");
                    } else {
                        // Update failed attempts in the database
                        PreparedStatement failedStmt = con.prepareStatement(updateFailedAttempts);
                        failedStmt.setInt(1, failedAttempts);
                        failedStmt.setString(2, username);
                        failedStmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Invalid credentials. Attempts left: " + (3 - failedAttempts));
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "User not found.");
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Database connection error.", "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}
//overriding the userLogin() for console application
public boolean userLogin(String username, String password) {
    String query = "SELECT * FROM Users WHERE username = ?";
    String updateLockStatus = "UPDATE Users SET isLocked = TRUE WHERE username = ?";
    String updateFailedAttempts = "UPDATE Users SET failedAttempts = ? WHERE username = ?";
    String resetFailedAttempts = "UPDATE Users SET failedAttempts = 0 WHERE username = ?";

    try {
        // Prepare the SQL statement to fetch user details
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();

        // Check if user exists
        if (rs.next()) {
            boolean isLocked = rs.getBoolean("isLocked");
            int failedAttempts = rs.getInt("failedAttempts");

            // Check if the account is locked
            if (isLocked) {
                System.out.println("Account is locked. Please contact admin.");
                return false;
            } else {
                // Check if the password is correct
                if (rs.getString("password").equals(password)) {
                    System.out.println("Login successful!");
                    
                    // Reset failed attempts after successful login
                    PreparedStatement resetStmt = con.prepareStatement(resetFailedAttempts);
                    resetStmt.setString(1, username);
                    resetStmt.executeUpdate();
                    return true;
                } else {
                    // Increment failed attempts
                    failedAttempts++;
                    if (failedAttempts >= 3) {
                        // Lock the account after 3 failed attempts
                        PreparedStatement lockStmt = con.prepareStatement(updateLockStatus);
                        lockStmt.setString(1, username);
                        lockStmt.executeUpdate();
                        System.out.println("Account has been locked due to multiple failed login attempts.");
                    } else {
                        // Update failed attempts in the database
                        PreparedStatement failedStmt = con.prepareStatement(updateFailedAttempts);
                        failedStmt.setInt(1, failedAttempts);
                        failedStmt.setString(2, username);
                        failedStmt.executeUpdate();
                        System.out.println("Incorrect password. Attempts left: " + (3 - failedAttempts));
                    }
                    return false;
                }
            }
        } else {
            System.out.println("User not found.");
            return false;
        }

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
//fecth account number by username
public int fetchAccountNumber(String username) {
    String query = "SELECT accountNumber FROM Accounts WHERE userId = (SELECT userId FROM Users WHERE username = ?)";
    int accountNumber = -1;

    try {
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            accountNumber = rs.getInt("accountNumber");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return accountNumber;
}
//get username by account number of a user from the database
public static String getUsernameByAccountNumber(int accountNumber) 
{
    String query = "SELECT username FROM Users WHERE userId = (SELECT userId FROM Accounts WHERE accountNumber = ?)";
    String username = null;

    try (PreparedStatement stmt = con.prepareStatement(query)) {
        stmt.setInt(1, accountNumber);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            username = rs.getString("username");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return username != null ? username : "Unknown User";
}
// Method to fetch transaction history for a specific user (by account number)
public static List<Transaction> getUserTransactionHistory(int accountNumber) {
    List<Transaction> transactions = new ArrayList<>();
    String query = "SELECT transactionId, transactionType, amount, transactionDate FROM Transactions WHERE accountNumber = ? ORDER BY transactionDate DESC";

    try (PreparedStatement stmt = con.prepareStatement(query)) {
        stmt.setInt(1, accountNumber);  // Set the specific account number to filter by user
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int transactionId = rs.getInt("transactionId");
            String transactionType = rs.getString("transactionType");
            double amount = rs.getDouble("amount");
            Timestamp timestamp = rs.getTimestamp("transactionDate");

            // Pass accountNumber as a parameter to match the constructor
            transactions.add(new Transaction(transactionId, accountNumber, transactionType, amount, timestamp));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return transactions;  // Return the list of transactions for the specific user
}



}
