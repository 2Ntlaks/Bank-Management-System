package com.mycompany.banksystem;

/**
 * Represents an account in the bank management system.
 * Each account is associated with a user and includes details such as account number,
 * account type (e.g., savings, debit), and balance.
 * 
 * <p>This class provides methods for performing transactions such as deposits,
 * withdrawals, and balance checks.</p>
 * 
 * @author Ntlakanipho Mgaguli
 */
public class Accounts {
    
    private int accountNumber;
    private int userId;
    private String accountType;  // Type of account, e.g., "savings" or "debit"
    private double balance;

    /**
     * Constructs a new Account with the specified details.
     * 
     * @param accountNumber the unique account number
     * @param userId        the ID of the user associated with this account
     * @param accountType   the type of the account (e.g., "savings", "debit")
     * @param balance       the initial balance of the account
     */
    public Accounts(int accountNumber, int userId, String accountType, double balance) {
        this.accountNumber = accountNumber;
        this.userId = userId;
        this.accountType = accountType;
        this.balance = balance;
    }

    /**
     * Gets the account number of this account.
     * 
     * @return the account number
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the account number for this account.
     * 
     * @param accountNumber the account number to set
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Gets the user ID associated with this account.
     * 
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID for this account.
     * 
     * @param userId the user ID to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the type of this account (e.g., "savings", "debit").
     * 
     * @return the account type
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Sets the type of this account.
     * 
     * @param accountType the account type to set
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * Gets the current balance of this account.
     * 
     * @return the account balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the balance of this account.
     * 
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Deposits a specified amount into this account.
     * 
     * @param amount the amount to deposit
     */
    public void deposit(double amount) {
        this.balance += amount;
    }

    /**
     * Withdraws a specified amount from this account if sufficient funds are available.
     * If the balance is insufficient, a message is displayed.
     * 
     * @param amount the amount to withdraw
     */
    public void withdraw(double amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    /**
     * Checks the current balance of this account.
     * 
     * @return the current balance
     */
    public double checkBalance() {
        return this.balance;
    }
}
