package com.mycompany.banksystem;

import Connectivity.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ntlak
 */
public class UserDashboard extends javax.swing.JFrame {

    /**
     * Creates new form UserDashboard
     */
      private int accountNumber; // Store account number for the user
      Connection con = DB.Con();  // Set up DB connection
    
      // Non-parameterized constructor
    public UserDashboard() {
        initComponents();
    }

    // Parameterized constructor
    public UserDashboard(String username) {
        initComponents();
         // Fetch and display user details
        populateUserDetails(username);
        
        this.setTitle("USER DASHBOARD");
    }
    public void updateBalanceLabel(double balance) {
    // Assuming jLabel9 is the label displaying the current balance
    jLabel9.setText(String.format("R %.2f", balance));
}
    
     private void populateUserDetails(String username) {
         UserMethods userMethods = new UserMethods();
    
    // Fetch account number using username
    accountNumber = userMethods.fetchAccountNumber(username);
    
    // Fetch account type and balance from the database
    String accountType = getAccountType(accountNumber);
    double balance = checkBalance(accountNumber);
    
    // Set the labels to display the fetched details
    nameLabel.setText(username);
    accountNumberLabel.setText(String.valueOf(accountNumber));
    jLabel8.setText(accountType);  // Account Type Label
    jLabel9.setText(String.format("R %.2f", balance));  // Current Balance Label
    }
     //method to get account type of the 
     public String getAccountType(int accountNumber) {
    String query = "SELECT accountType FROM Accounts WHERE accountNumber = ?";
    try (PreparedStatement stmt = con.prepareStatement(query)) {
        stmt.setInt(1, accountNumber);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getString("accountType");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return "Unknown";  // Return "Unknown" if the account type is not found
}
     //method to check user balance
public double checkBalance(int accountNumber) {
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
    return 0.0;  // Return 0 if balance is not found
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel1 = new javax.swing.JPanel();
        jpl2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        accountNumberLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        DepositBTN = new javax.swing.JButton();
        WithdrawBTN = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "USER DETAILS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(51, 0, 102))); // NOI18N

        jpl2.setText("Name:");

        jLabel2.setText("Account Number:");

        jLabel3.setText("Account Type:");

        jLabel4.setText("Current Balance: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jpl2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nameLabel))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(accountNumberLabel))))
                .addContainerGap(369, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jpl2)
                    .addComponent(nameLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(accountNumberLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Transaction ID", "Type", "Amount", "Date/Time"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        DepositBTN.setText("Deposit");
        DepositBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DepositBTNActionPerformed(evt);
            }
        });

        WithdrawBTN.setText("Withdraw");
        WithdrawBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WithdrawBTNActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 153));
        jLabel5.setText("Transaction History");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 153));
        jLabel6.setText("USER DASHBOARD");

        jMenu1.setText("Transactions");
        jMenu1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jMenuItem1.setText("Transfer Money");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jMenuItem2.setText("View Transaction History");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenu2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jMenuItem3.setText("Logout");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jMenuItem4.setText("Exit");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Help & Support");
        jMenu3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jMenuItem5.setText("Guide");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(DepositBTN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(WithdrawBTN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(215, 215, 215))
            .addGroup(layout.createSequentialGroup()
                .addGap(475, 475, 475)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(160, 160, 160)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DepositBTN)
                            .addComponent(WithdrawBTN))
                        .addGap(85, 85, 85))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );

        jPanel1.getAccessibleContext().setAccessibleName("User details");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DepositBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DepositBTNActionPerformed
     // Prompt user to enter deposit amount
    String amountStr = JOptionPane.showInputDialog(this, "Enter amount to deposit:");
    try {
        double amount = Double.parseDouble(amountStr);
        // Check if amount is positive
        if (amount <= 0) {
            JOptionPane.showMessageDialog(this, "Please enter a valid positive amount.");
            return;
        }
        
        // Call the deposit method
        UserMethods.depositFunds(accountNumber, amount);
        
        // Update the balance label after deposit
        double updatedBalance = checkBalance(accountNumber);
        jLabel9.setText(String.format("R %.2f", updatedBalance));
        
        // Show confirmation message
        JOptionPane.showMessageDialog(this, "Deposit successful! Amount: R " + amount, "Success", JOptionPane.INFORMATION_MESSAGE);
        
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid amount entered. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_DepositBTNActionPerformed

    private void WithdrawBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WithdrawBTNActionPerformed
        // TODO add your handling code here:
    // Prompt user to enter withdrawal amount
    String amountStr = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
    try {
        double amount = Double.parseDouble(amountStr);
        if (amount <= 0) {
            JOptionPane.showMessageDialog(this, "Please enter a valid positive amount.");
            return;
        }
        
        // Call the withdraw method
        UserMethods.withdrawFunds(accountNumber, amount);
        
        // Update the balance label after withdrawal
        double updatedBalance = checkBalance(accountNumber);
        jLabel9.setText(String.format("R %.2f", updatedBalance));
        
        // Show confirmation message
        JOptionPane.showMessageDialog(this, "Withdrawal successful! Amount: R " + amount, "Success", JOptionPane.INFORMATION_MESSAGE);
        
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid amount entered. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_WithdrawBTNActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        // Inside UserDashboard, when opening TransferMoney
        TransferMoney transferFrame = new TransferMoney(accountNumber, this);  // Pass the current dashboard instance
        transferFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
         // Assuming 'accountNumber' is the account number of the logged-in user
    List<Transaction> transactions = (List<Transaction>) UserMethods.getUserTransactionHistory(accountNumber);
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0);  // Clear existing rows

    // Populate table with transaction data
    for (Transaction transaction : transactions) {
        model.addRow(new Object[]{
            transaction.getTransactionId(),
            transaction.getTransactionType(),
            transaction.getAmount(),
            transaction.getTransactionDate()
        });
    }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
         HelpSupport helpSupport = new HelpSupport();
        helpSupport.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        LoginForm login = new LoginForm();
        this.dispose();
        login.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DepositBTN;
    private javax.swing.JButton WithdrawBTN;
    private javax.swing.JLabel accountNumberLabel;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jpl2;
    private javax.swing.JLabel nameLabel;
    // End of variables declaration//GEN-END:variables
}
