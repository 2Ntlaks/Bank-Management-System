package com.mycompany.banksystem;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


/**
 *
 * @author ntlak
 */
public class LoginForm extends javax.swing.JFrame {

    /**
     * Creates new form LoginForm
     */
    public LoginForm() {
        initComponents();
        this.setTitle("LOGIN PAGE");
  
        //add icons
        // Load the icon and scale it to fit
        ImageIcon userIcon = new ImageIcon(getClass().getResource("/images/user.png"));
        Image img = userIcon.getImage();
        Image scaledImg = img.getScaledInstance(23, 23, java.awt.Image.SCALE_SMOOTH); // Adjust the size to fit
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
         // Set the scaled icon to the label
        usernameLabel.setText("Username");
        usernameLabel.setIcon(scaledIcon);
        
        // Load and scale the password icon
        ImageIcon passwordIcon = new ImageIcon(getClass().getResource("/images/reset-password.png"));
        Image imgPassword = passwordIcon.getImage();
        Image scaledImgPassword = imgPassword.getScaledInstance(23, 23, java.awt.Image.SCALE_SMOOTH); // Adjust the size
        ImageIcon scaledPasswordIcon = new ImageIcon(scaledImgPassword);

        // Set the scaled icon to the password label
        passwordLabel.setText("Password");
        passwordLabel.setIcon(scaledPasswordIcon);
        
        // Load and scale the group icon
        ImageIcon roleIcon = new ImageIcon(getClass().getResource("/images/people.png"));
        Image imgRole = roleIcon.getImage();
        Image scaledImgRole = imgRole.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH); // Adjust the size
        ImageIcon scaledRoleIcon = new ImageIcon(scaledImgRole);
// Set the scaled icon to the role label
        roleLabel.setText("Select Role");
        roleLabel.setIcon(scaledRoleIcon);
        
        //add hover effect for clean button
        ClearBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ClearBTN.setBackground(Color.LIGHT_GRAY);  // Hover color
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ClearBTN.setBackground(Color.DARK_GRAY);  // Normal color
            }
        });
        //add hover effect for exit button
                ExitBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ExitBTN.setBackground(Color.LIGHT_GRAY);  // Hover color
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ExitBTN.setBackground(Color.DARK_GRAY);  // Normal color
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel = new javax.swing.JPanel();
        roleLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        usernameTF = new javax.swing.JTextField();
        RoleBox = new javax.swing.JComboBox<>();
        passwordTF = new javax.swing.JPasswordField();
        ExitBTN = new javax.swing.JButton();
        ClearBTN = new javax.swing.JButton();
        role = new javax.swing.JLabel();
        usernameIcon = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();

        jLabel2.setText("jLabel2");

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel1.setText("LOGIN PAGE");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(275, 275, 275)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(232, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel.setBackground(new java.awt.Color(204, 204, 204));

        roleLabel.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        roleLabel.setText("SELECT ROLE");

        usernameLabel.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        usernameLabel.setText("USERNAME");

        passwordLabel.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        passwordLabel.setText("PASSWORD");

        usernameTF.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        RoleBox.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        RoleBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User" }));

        passwordTF.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        ExitBTN.setBackground(new java.awt.Color(58, 42, 42));
        ExitBTN.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        ExitBTN.setText("EXIT");
        ExitBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitBTNActionPerformed(evt);
            }
        });

        ClearBTN.setBackground(new java.awt.Color(58, 42, 42));
        ClearBTN.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        ClearBTN.setText("CLEAR");
        ClearBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearBTNActionPerformed(evt);
            }
        });

        loginButton.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        loginButton.setText("LOGIN");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(ClearBTN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ExitBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(role)
                    .addComponent(usernameIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usernameLabel)
                    .addComponent(roleLabel)
                    .addComponent(passwordLabel))
                .addGap(65, 65, 65)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(usernameTF)
                    .addComponent(RoleBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(passwordTF, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(240, 240, 240))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(roleLabel)
                        .addComponent(role))
                    .addComponent(RoleBox, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameLabel)
                    .addComponent(usernameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordTF, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ExitBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ClearBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ClearBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearBTNActionPerformed
        // TODO add your handling code here:
        usernameTF.setText("");
        passwordTF.setText("");
        RoleBox.setSelectedIndex(0);
    }//GEN-LAST:event_ClearBTNActionPerformed

    private void ExitBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitBTNActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_ExitBTNActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
    // Get user inputs
    String selectedRole = RoleBox.getSelectedItem().toString();
    String username = usernameTF.getText();
    String password = new String(passwordTF.getPassword());

    // Check if fields are empty
    if (username.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Login Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (selectedRole.equals("Admin")) {
        // Direct Admin to Admin Dashboard
        AdminDashboard adminDashboard = new AdminDashboard();
        this.dispose(); // Close the login window
        adminDashboard.setVisible(true);

    } else if (selectedRole.equals("User")) {
        // Initialize UserMethods for user login validation
        UserMethods userMethods = new UserMethods(); 
        int accountNumber = userMethods.checkCredentials(username, password);  // returns account number or -1 on failure

        if (accountNumber != -1) {
            // Successful login
            JOptionPane.showMessageDialog(null, "Login Successful", "Login", JOptionPane.INFORMATION_MESSAGE);

            // Pass username to UserDashboard and open it
            UserDashboard userDashboard = new UserDashboard(username);
            this.dispose();  // Close the login window
            userDashboard.setVisible(true);

        } else {
            // Failed login attempt
            JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.", "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        // Optionally handle unexpected role selections
        JOptionPane.showMessageDialog(null, "Please select a valid role.", "Login Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_loginButtonActionPerformed

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
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ClearBTN;
    private javax.swing.JButton ExitBTN;
    private javax.swing.JComboBox<String> RoleBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField passwordTF;
    private javax.swing.JLabel role;
    private javax.swing.JLabel roleLabel;
    private javax.swing.JLabel usernameIcon;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameTF;
    // End of variables declaration//GEN-END:variables
}
