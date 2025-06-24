package com.mycompany.banksystem;

import static Connectivity.DB.Con;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Provides methods for admin functionalities in the Bank Management System.
 * This class includes functionalities such as adding and managing users, 
 * viewing accounts, unlocking accounts, and retrieving transaction history.
 *
 * @version 1.0
 * @since 2024
 */
public class AdminMethods {
    public static Connection con = Con();  // Database connection
    Scanner scanner = new Scanner(System.in);
    BankSystem bank;

    /**
     * Initializes AdminMethods and sets up BankSystem instance.
     */
    public AdminMethods() {
        bank = new BankSystem();  // Initialize BankSystem object
    }

    /**
     * Prompts for admin login and validates credentials.
     */
    public void adminLogin() {
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        String role = checkCredentials(username, password);  // Check credentials and get role

        if (role != null && role.equals("admin")) {
            System.out.println("Welcome, Admin!");
            bank.adminMenu();  // Redirect to Admin dashboard
        } else {
            System.out.println("Invalid username or password for Admin. Try again.");
        }
    }

    /**
     * Retrieves and displays all users in the Users table.
     */
    public static void getAllUsers() {
        String query = "SELECT * FROM Users";
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("User: " + rs.getString("username") + ", Role: " + rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new user to the database with a unique account number.
     * 
     * @param username The username of the new user.
     * @param password The password of the new user (not hashed for simplicity).
     * @param role The role of the new user (default: "user").
     */
    public static int addUserToDatabase(String username, String password, String role) {
        int accountNumber = generateAccountNumber();
        String userQuery = "INSERT INTO Users (username, password, role) VALUES (?, ?, 'user')";

        try (PreparedStatement stmt = con.prepareStatement(userQuery)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
            System.out.println("User added successfully.");
            addAccount(accountNumber, username);
            return accountNumber;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Generates a unique account number based on the current system time.
     * 
     * @return The generated account number.
     */
    public static int generateAccountNumber() {
        return (int) (System.currentTimeMillis() / 1000L);  // Time-based unique number
    }

    /**
     * Adds an account for a new user with default settings.
     * 
     * @param accountNumber The generated account number.
     * @param username The username linked to the account.
     */
    public static void addAccount(int accountNumber, String username) {
        String accountType = "savings";
        double initialBalance = 0.0;
        String accountQuery = "INSERT INTO Accounts (accountNumber, userId, accountType, balance) " +
                              "SELECT ?, userId, ?, ? FROM Users WHERE username = ?";

        try (PreparedStatement stmt = con.prepareStatement(accountQuery)) {
            stmt.setInt(1, accountNumber);
            stmt.setString(2, accountType);
            stmt.setDouble(3, initialBalance);
            stmt.setString(4, username);
            stmt.executeUpdate();
            System.out.println("Account created successfully with account number: " + accountNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks user credentials in the database and returns the role if valid.
     * 
     * @param username The username for login.
     * @param password The password for login.
     * @return The role of the user, or null if credentials are invalid.
     */
    public static String checkCredentials(String username, String password) {
        String query = "SELECT role FROM Users WHERE username = ? AND password = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves and displays all accounts from the Accounts table.
     */
    public static void getAllAccounts() {
        String query = "SELECT * FROM Accounts";
        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Account Number: " + rs.getInt("accountNumber") + ", Balance: " + rs.getDouble("balance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Displays all linked accounts for a specific user by their user ID. This
     * method is intended to be used within search operations, providing
     * additional account information for the located user.
     *
     * @param userId The unique ID of the user whose linked accounts are
     * displayed.
     */
private static void displayLinkedAccounts(int userId) {
    String query = "SELECT accountNumber, accountType, balance FROM Accounts WHERE userId = ?";

    try (PreparedStatement stmt = con.prepareStatement(query)) {
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();

        System.out.println("\tLinked Accounts:");
        while (rs.next()) {
            int accountNumber = rs.getInt("accountNumber");
            String accountType = rs.getString("accountType");
            double balance = rs.getDouble("balance");

            System.out.println("\tAccount Number: " + accountNumber);
            System.out.println("\tAccount Type: " + accountType);
            System.out.println("\tBalance: R" + balance);
            System.out.println("\t---------------------------\n");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    /**
     * Searches for a user by their ID and displays their details using binary search.
     */
    public static void searchUserById() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter User ID to search for: ");
        int searchId = scanner.nextInt();

        ArrayList<Users> users = getUsersSortedById();
        int resultIndex = binarySearchById(users, searchId);

        if (resultIndex != -1) {
            Users foundUser = users.get(resultIndex);
            System.out.println("***** DETAILS OF THE USER FOUND ************\n");
            System.out.println("\tUser ID: " + foundUser.getUserId());
            System.out.println("\tUsername: " + foundUser.getUsername());
            System.out.println("\tRole: " + foundUser.getRole());
            displayLinkedAccounts(foundUser.getUserId());
        } else {
            System.out.println("\tUser not found.");
        }
    }
    /**
     * Performs a binary search on a sorted list of users by their ID. Utilized
     * in search operations to quickly locate a user based on the ID.
     *
     * @param users A sorted ArrayList of Users objects by userId.
     * @param searchId The user ID to search for in the list.
     * @return The index of the user in the list, or -1 if the user is not
     * found.
     */
public static int binarySearchById(ArrayList<Users> users, int searchId) {
    int left = 0;
    int right = users.size() - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (users.get(mid).getUserId() == searchId) {
            return mid;  // User found at index mid
        }

        if (users.get(mid).getUserId() < searchId) {
            left = mid + 1;  // Ignore left half
        } else {
            right = mid - 1;  // Ignore right half
        }
    }
    return -1;  // User not found
}

/**
 * Retrieves a list of all users with their associated account information.
 * This method performs a join between the Users and Accounts tables to provide
 * a complete view of each user's details and account information.
 * 
 * @return A list of `Users` objects, each containing user details along with
 *         associated account information (e.g., account number, type, balance).
 */
public static List<Users> getAllUsersWithAccounts() {
    List<Users> usersList = new ArrayList<>();

    String query = "SELECT U.userId, U.username, U.role, A.accountNumber, A.accountType, A.balance " +
                   "FROM Users U LEFT JOIN Accounts A ON U.userId = A.userId";

    try (PreparedStatement stmt = con.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {
        
        while (rs.next()) {
            int userId = rs.getInt("userId");
            String username = rs.getString("username");
            String role = rs.getString("role");
            int accountNumber = rs.getInt("accountNumber");
            String accountType = rs.getString("accountType");
            double balance = rs.getDouble("balance");

            // Create a Users object to store user and account details
            Users user = new Users(userId, username, role, accountNumber, accountType, balance);
            usersList.add(user);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return usersList;  // Return the list of users with account info
}

    /**
     * Retrieves all users sorted by user ID.
     * 
     * @return A sorted ArrayList of Users objects.
     */
    public static ArrayList<Users> getUsersSortedById() {
        ArrayList<Users> users = new ArrayList<>();
        String query = "SELECT * FROM Users ORDER BY userId ASC";

        try {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("userId");
                String username = rs.getString("username");
                String role = rs.getString("role");
                users.add(new Users(userId, username, role));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * Retrieves transaction history for all accounts.
     * 
     * @return A list of Transaction objects containing transaction details.
     */
    public static List<Transaction> getTransactionHistory() {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM Transactions";

        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                int transactionId = rs.getInt("transactionId");
                int accountNumber = rs.getInt("accountNumber");
                String transactionType = rs.getString("transactionType");
                double amount = rs.getDouble("amount");
                Timestamp timestamp = rs.getTimestamp("transactionDate");
                transactions.add(new Transaction(transactionId, accountNumber, transactionType, amount, timestamp));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
    
    /**
 * Unlocks a user account by updating the `isLocked` field to `FALSE` and resetting the failed attempts.
 * This method is primarily used by admins to reactivate a locked user account.
 */
public static void unlockAccount() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the User ID to unlock: ");
    int userId = scanner.nextInt();

    String query = "UPDATE Users SET isLocked = FALSE, failedAttempts = 0 WHERE userId = ?";

    try {
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, userId);
        int rowsUpdated = stmt.executeUpdate();

        if (rowsUpdated > 0) {
            System.out.println("Account unlocked successfully.");
        } else {
            System.out.println("User not found.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

/**
 * Overloaded method to unlock an account using the userId directly.
 * 
 * @param userId The ID of the user to unlock.
 * @return True if the account was successfully unlocked; false otherwise.
 */
public static boolean unlockAccount(int userId) {
    String query = "UPDATE Users SET isLocked = FALSE, failedAttempts = 0 WHERE userId = ?";

    try {
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, userId);
        int rowsUpdated = stmt.executeUpdate();

        if (rowsUpdated > 0) {
            System.out.println("Account unlocked successfully.");
            return true;  // Account was successfully unlocked
        } else {
            System.out.println("User not found.");
            return false;  // User not found
        }

    } catch (SQLException e) {
        e.printStackTrace();
        return false;  // Return false in case of an exception
    }
}
/**
 * Retrieves the account type associated with a specific user ID.
 * This method queries the Accounts table to find the account type for the given user.
 *
 * @param userId The unique identifier of the user.
 * @return The account type as a string (e.g., "savings", "checking").
 *         Returns an empty string if no account is found for the user.
 */
public static String getAccountTypeByUserId(int userId) {
    String query = "SELECT accountType FROM Accounts WHERE userId = ?";
    String accountType = "";

    try (PreparedStatement stmt = con.prepareStatement(query)) {
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            accountType = rs.getString("accountType");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return accountType;
}
/**
 * Searches for a user by their unique user ID.
 * This method uses a binary search on a sorted list of users to quickly locate a user by ID.
 * If found, it returns the user's details.
 * 
 * @param userId The unique identifier of the user to be searched.
 * @return A Users object containing details of the found user, or null if the user is not found.
 */
public Users searchUserById(int userId) {
    ArrayList<Users> users = getUsersSortedById();  // Retrieve all users sorted by ID

    // Call the binary search method to find the user by ID
    int resultIndex = binarySearchById(users, userId);

    if (resultIndex != -1) {
        // If the user is found, return the user object
        return users.get(resultIndex);
    } else {
        // Return null if the user is not found
        return null;
    }
}
/**
 * Retrieves the current balance of a specific user by their user ID.
 * This method queries the Accounts table to find the balance for the given user.
 *
 * @param userId The unique identifier of the user.
 * @return The balance as a double value. Returns 0.0 if no account is found for the user.
 */
public static double getBalanceByUserId(int userId) {
    String query = "SELECT balance FROM Accounts WHERE userId = ?";
    double balance = 0.0;

    try (PreparedStatement stmt = con.prepareStatement(query)) {
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            balance = rs.getDouble("balance");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return balance;
}

}
