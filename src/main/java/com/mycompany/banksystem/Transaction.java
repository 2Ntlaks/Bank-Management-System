package com.mycompany.banksystem;

import java.sql.Timestamp;

/**
 * Represents a transaction in the bank management system.
 * Each transaction includes details such as the transaction ID, account number,
 * transaction type (e.g., deposit, withdrawal, or transfer), amount, and date.
 * 
 * <p>This class is used to store and retrieve transaction information
 * related to an account's transaction history.</p>
 * 
 * @author Ntlakanipho Mgaguli
 */
public class Transaction {

    private int transactionId;
    private int accountNumber;
    private String transactionType;  // Type of transaction: "deposit", "withdrawal", or "transfer"
    private double amount;
    private Timestamp transactionDate;

    /**
     * Constructs a new Transaction with the specified details.
     * 
     * @param transactionId   the unique ID of the transaction
     * @param accountNumber   the account number associated with this transaction
     * @param transactionType the type of transaction (e.g., deposit, withdrawal, transfer)
     * @param amount          the amount involved in the transaction
     * @param transactionDate the date and time when the transaction occurred
     */
    public Transaction(int transactionId, int accountNumber, String transactionType, double amount, Timestamp transactionDate) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    /**
     * Gets the unique ID of the transaction.
     * 
     * @return the transaction ID
     */
    public int getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the unique ID of the transaction.
     * 
     * @param transactionId the transaction ID to set
     */
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Gets the account number associated with this transaction.
     * 
     * @return the account number
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the account number associated with this transaction.
     * 
     * @param accountNumber the account number to set
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Gets the type of transaction (e.g., deposit, withdrawal, transfer).
     * 
     * @return the transaction type
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Sets the type of transaction (e.g., deposit, withdrawal, transfer).
     * 
     * @param transactionType the transaction type to set
     */
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * Gets the amount involved in the transaction.
     * 
     * @return the transaction amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the amount involved in the transaction.
     * 
     * @param amount the transaction amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Gets the date and time when the transaction occurred.
     * 
     * @return the transaction date
     */
    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    /**
     * Sets the date and time when the transaction occurred.
     * 
     * @param transactionDate the transaction date to set
     */
    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }
}
