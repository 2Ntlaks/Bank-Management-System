package com.mycompany.banksystem;

/**
 * Represents a user in the bank management system.
 * 
 * <p>This class includes essential user information such as ID, username, role, 
 * password, account number, account type, and balance.</p>
 * 
 * <p>The class also provides constructors to initialize users with varying levels of detail,
 * as well as methods to get and set each field.</p>
 * 
 * @author Ntlakanipho Mgaguli
 */
public class Users {

    private int userId;
    private String username;
    private String role;  // Specifies whether the user is an "admin" or "user"
    private String password;
    private int accountNumber;
    private String accountType;
    private double balance;

    /**
     * Constructs a new user with user ID, username, password, and role.
     * This constructor is primarily used when creating a user with login credentials.
     * 
     * @param userId   the unique ID of the user
     * @param username the username for this user
     * @param password the password for this user
     * @param role     the role of the user (e.g., "admin" or "user")
     */
    public Users(int userId, String username, String password, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Constructs a new user with user ID, username, and role.
     * This constructor is used when searching for a user without needing the password.
     * 
     * @param userId   the unique ID of the user
     * @param username the username for this user
     * @param role     the role of the user (e.g., "admin" or "user")
     */
    public Users(int userId, String username, String role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

    /**
     * Constructs a new user with all user and account details.
     * This constructor is used when retrieving both user and account information.
     * 
     * @param userId        the unique ID of the user
     * @param username      the username for this user
     * @param role          the role of the user (e.g., "admin" or "user")
     * @param accountNumber the user's account number
     * @param accountType   the type of account (e.g., "savings", "debit")
     * @param balance       the current balance of the account
     */
    public Users(int userId, String username, String role, int accountNumber, String accountType, double balance) {
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
    }

    /**
     * Default constructor for the Users class.
     */
    public Users() {
        // Default constructor
    }

    /**
     * Gets the user ID.
     * 
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     * 
     * @param userId the user ID to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the username.
     * 
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     * 
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     * 
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     * 
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the role of the user.
     * 
     * @return the role (e.g., "admin" or "user")
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     * 
     * @param role the role to set (e.g., "admin" or "user")
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets the account number associated with this user.
     * 
     * @return the account number
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the account number for this user.
     * 
     * @param accountNumber the account number to set
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Gets the account type (e.g., "savings", "debit").
     * 
     * @return the account type
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Sets the account type for this user.
     * 
     * @param accountType the account type to set
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * Gets the current balance of the account associated with this user.
     * 
     * @return the account balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the account balance for this user.
     * 
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
