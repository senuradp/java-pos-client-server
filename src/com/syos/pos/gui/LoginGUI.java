/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.gui;

/**
 *
 * @author senu2k
 */
import com.syos.pos.menucommand.AdminUserType;
import com.syos.pos.menucommand.CashierUserType;
import com.syos.pos.menucommand.MySQLUserQueries;
import com.syos.pos.menucommand.User;
import com.syos.pos.menucommand.UserQueries;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private static UserQueries userQueries;
    User user = null;
    String username, role;

    public LoginGUI() {

        userQueries = new MySQLUserQueries();

        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate the username and password
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                boolean loginSuccess = validateLogin(username, password);

                if (loginSuccess) {
                    // Get the user's role here (you may already have this logic)
                    String userRole = getUserRole(username);

                    // Show the main menu and pass the username and role
                    openMainMenu(username, userRole);
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials. Please try again.");
                }
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(loginButton);

        add(panel);
        setVisible(true);
    }
    

    private boolean validateLogin(String username, String password) {
        // Perform the login logic here
        // You can use the userQueries object to check credentials and retrieve the user role
        boolean loginSuccess = userQueries.loginUser(username, password);

        if (loginSuccess) {
            String userRole = userQueries.getUserRole(username);

            if (userRole != null) {
                if (userRole.equalsIgnoreCase("admin")) {
                    // Create an instance of AdminUserType
                    user = new User(username, password, new AdminUserType());
                } else if (userRole.equalsIgnoreCase("cashier")) {
                    user = new User(username, password, new CashierUserType());
                } else {
                    // Invalid role, display a message
                    JOptionPane.showMessageDialog(null, "Invalid role. Please try again.");
                    return false;
                }
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Failed to retrieve user role. Please try again.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid credentials. Please try again.");
        }

        return false;
    }
    
    private String getUserRole(String username) {
        return userQueries.getUserRole(username);
    }
    
    private void openMainMenu(String username, String role) {
        // Create an instance of MainMenuGUI and pass the username and role
        MainMenuGUI mainMenu = new MainMenuGUI(username, role);
        mainMenu.setVisible(true);
        dispose(); // Close the login window
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginGUI());
    }
}
