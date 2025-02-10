package com.mycompany.banksystem;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * BankSystem class serves as the main entry point for the Bank Management System.
 * It provides both GUI and Console-based options for interaction.
 * <p>
 * The class includes main functionalities such as user login, admin login,
 * and dashboards for both users and admins with various account management options.
 * </p>
 * <p>
 * Usage:
 * Run the application and choose between the console or GUI mode for user interaction.
 * </p>
 * 
 * @version 1
 * @since 2024
 * 
 * @author Ntlakanipho Mgaguli
 */
public class BankSystem {

    Scanner scanner = new Scanner(System.in);
    UserMethods users = new UserMethods();

    /**
     * Displays the main menu for console-based interaction.
     * Users can log in as either a user or an admin, or exit the application.
     * 
     * @throws SQLException if a database access error occurs
     */
    public void mainMenu() throws SQLException {

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n");
            System.out.println("===================================================================");
            System.out.println("                   BANK MANAGEMENT SYSTEM                          ");
            System.out.println("===================================================================");
            System.out.println("\n");
            System.out.println("1. Login as User");
            System.out.println("2. Login as Admin");
            System.out.println("3. Exit");
            System.out.println("=============================================================\n");
            System.out.print("Please choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();

                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    int accountNumber = users.fetchAccountNumber(username);

                    if (users.userLogin(username, password)) {
                        System.out.println("Login successful!");
                        userMenu(accountNumber);
                    } else {
                        System.out.println("Login failed. Please try again.");
                    }
                    break;
                case 2:
                    adminLogin();
                    break;
                case 3:
                    isRunning = false;
                    System.out.println("\tGoodbye!\n");
                    break;
                default:
                    System.out.println("\n*********************Invalid choice. Please try again.****************\n");
            }
        }
        scanner.close();
    }

    /**
     * Displays the admin menu with options for adding users, viewing accounts, 
     * searching users by ID, and unlocking user accounts.
     */
    public static void adminMenu() {

        AdminMethods adminMethods = new AdminMethods();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("*******************************************************************");
            System.out.println("\tAdmin Dashboard:");
            System.out.println("*******************************************************************");
            System.out.println("1. Add New User");
            System.out.println("2. View All Users");
            System.out.println("3. View All Accounts");
            System.out.println("4. Search for User by ID");
            System.out.println("5. Unlock User Account by User ID");
            System.out.println("6. Logout");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter new username: ");
                    String newUsername = scanner.next();
                    System.out.print("Enter password: ");
                    String newPassword = scanner.next();
                    String role = "user";

                    adminMethods.addUserToDatabase(newUsername, newPassword, role);
                    break;
                case 2:
                    adminMethods.getAllUsers();
                    break;
                case 3:
                    adminMethods.getAllAccounts();
                    break;
                case 4:
                    adminMethods.searchUserById();
                    break;
                case 5:
                    adminMethods.unlockAccount();
                    break;
                case 6:
                    isRunning = false;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Displays the user dashboard with options for managing transactions such as checking balance, 
     * depositing, withdrawing, transferring funds, viewing transaction history, and logging out.
     * 
     * @param accountNumber the account number of the logged-in user
     * @throws SQLException if a database access error occurs
     */
    public static void userMenu(int accountNumber) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("*******************************************************************");
            System.out.println("\tUser Dashboard");
            System.out.println("*******************************************************************");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Funds");
            System.out.println("3. Withdraw Funds");
            System.out.println("4. Transfer Funds");
            System.out.println("5. View Transaction History");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                       double balance = UserMethods.checkBalance(accountNumber);
                            System.out.println("Your current balance is: R" + balance);
                        break;
                    case 2:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        UserMethods.depositFunds(accountNumber, depositAmount);
                        break;
                    case 3:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        UserMethods.withdrawFunds(accountNumber, withdrawAmount);
                        break;
                    case 4:
                        System.out.print("Enter recipient account number: ");
                        int recipientAccountNumber = scanner.nextInt();
                        System.out.print("Enter amount to transfer: ");
                        double transferAmount = scanner.nextDouble();
                        BankTransactionService bankTransactionService = new BankTransactionService();
                        bankTransactionService.transferFunds(accountNumber, recipientAccountNumber, transferAmount, true);
                        break;
                    case 5:
                        UserMethods.viewTransactionHistory(accountNumber);
                        break;
                    case 6:
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number for the menu option.");
                scanner.nextLine();
            }
        }
    }

    /**
     * Handles the admin login process and navigates to the admin menu on successful authentication.
     */
    public void adminLogin() {
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        String role = AdminMethods.checkCredentials(username, password);

        if (role != null && role.equals("admin")) {
            System.out.println("Welcome Admin: " + username + "!");
            adminMenu();
        } else {
            System.out.println("Invalid username or password. Try again.");
        }
    }

    /**
     * Main method to run the Bank Management System.
     * It prompts the user to choose between GUI and Console-based interaction.
     * 
     * @param args command-line arguments
     * @throws SQLException if a database access error occurs
     */
    public static void main(String[] args) throws SQLException {
        BankSystem bank = new BankSystem();

        String[] options = {"GUI", "Console"};
        int choice = JOptionPane.showOptionDialog(null,
                "Choose the version you want to review",
                "Mode Selection",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        if (choice == 0) {
            SplashScreen spl = new SplashScreen();
            spl.setVisible(true);

            for (int i = 0; i <= 100; i++) {
                try {
                    Thread.sleep(85);
                    spl.loadingNum.setText("Loading..." + i + " %");
                    spl.jProgressBarload.setValue(i);

                    if (i == 20) {
                        spl.guides_add.setText("Please wait while setting things in order");
                    } else if (i == 99) {
                        spl.guides_add.setText("Enjoy our service, Thanks!!");
                    }
                    if (i == 100) {
                        JOptionPane.showMessageDialog(null, "Welcome");
                        LoginForm login = new LoginForm();
                        login.setVisible(true);
                        spl.setVisible(false);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (choice == 1) {
            bank.mainMenu();
        }
    }
}
