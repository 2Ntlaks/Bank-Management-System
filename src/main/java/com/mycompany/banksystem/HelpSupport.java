package com.mycompany.banksystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * HelpSupport class provides a JFrame window that displays help and support information for users.
 * It includes FAQs, contact support options, and general application guidance in HTML format.
 * <p>
 * Users can navigate back to the User Dashboard from this window.
 * </p>
 * 
 * @version 1.0
 * @since 2024
 * 
 * Developed by Ntlakanipho Mgaguli
 */
public class HelpSupport extends JFrame {

    /**
     * Initializes the HelpSupport window with HTML content and a "Back to Dashboard" button.
     * The window provides information to guide the user through the application.
     */
    public HelpSupport() {
        setTitle("Help & Support");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a JEditorPane to display HTML content
        JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.setEditable(false);
        editorPane.setText(htmlContent);

        // Create a JScrollPane to enable scrolling for HTML content
        JScrollPane scrollPane = new JScrollPane(editorPane);
        
        // Create the "Back to Dashboard" button with styling and a tooltip
        JButton backButton = new JButton("Back to Dashboard");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(70, 130, 180));  // Steel blue color
        backButton.setToolTipText("Click to return to the User Dashboard");

        // Action listener to close HelpSupport window when button is clicked
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // Close the HelpSupport window
                // Optionally, refocus on the UserDashboard if needed
            }
        });

        // Set up layout for content and add scroll pane and button panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(contentPanel);
    }

    /**
     * HTML content for the Help & Support page displayed within the JEditorPane.
     */
    private String htmlContent = """
        <html>
            <head>
                <style>
                    body { font-family: Arial, sans-serif; margin: 20px; background-color: #f9f9f9; }
                    h1 { color: #4A90E2; font-size: 24px; margin-bottom: 20px; border-bottom: 2px solid #4A90E2; padding-bottom: 5px; }
                    h2 { color: #333; font-size: 18px; margin-top: 25px; border-left: 4px solid #4A90E2; padding-left: 10px; }
                    p { margin-bottom: 15px; line-height: 1.6; color: #555; }
                    a { color: #0066cc; text-decoration: none; }
                    a:hover { text-decoration: underline; }
                    .faq { margin-top: 20px; }
                    .faq p { margin: 10px 0; }
                    .footer { margin-top: 40px; font-size: 12px; color: #888; text-align: center; border-top: 1px solid #ccc; padding-top: 10px; }
                </style>
            </head>
            <body>
                <h1>Help & Support</h1>
                <h2>Getting Started</h2>
                <p>Welcome to the Bank Management System! This application allows you to manage your transactions with ease. Use the menu options to navigate through the system.</p>
                <h2 class="faq">FAQs</h2>
                <p><strong>How do I transfer funds?</strong><br>Navigate to <em>Transactions > Transfer Money</em> and enter the required details.</p>
                <p><strong>How do I view my transaction history?</strong><br>Go to <em>Transactions > View Transaction History</em> to see a record of all transactions.</p>
                <h2>Contact Support</h2>
                <p>If you need further assistance, please contact us at <a href='mailto:support@banksystem.com'>support@banksystem.com</a>.</p>
                <h2>About</h2>
                <p>This application was developed by Ntlakanipho Mgaguli as part of a software project. The aim is to provide a user-friendly banking experience.</p>
                <div class="footer">&copy; 2024 Bank Management System. All rights reserved.</div>
            </body>
        </html>
        """;

    /**
     * Main method for testing the HelpSupport JFrame independently.
     * Initializes and displays the HelpSupport window.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HelpSupport helpFrame = new HelpSupport();
            helpFrame.setVisible(true);
        });
    }
}
