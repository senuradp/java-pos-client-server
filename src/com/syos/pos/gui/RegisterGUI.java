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

import com.syos.pos.menucommand.MySQLUserQueries;
import com.syos.pos.menucommand.UserQueries;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;
    private static UserQueries userQueries;
    
    public RegisterGUI() {
    
        userQueries = new MySQLUserQueries();

        setTitle("Register");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel roleLabel = new JLabel("Role:");
        
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        
        // Create a combo box for the role selection
        String[] roles = {"Admin", "User"};
        roleComboBox = new JComboBox<>(roles);
        
        JButton registerButton = new JButton("Register");

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the registration details
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String role = (String) roleComboBox.getSelectedItem();
                
                // Perform registration logic (e.g., save to a database)
                boolean registrationSuccess = registerUser(username, password, role);

                if (registrationSuccess) {
                    JOptionPane.showMessageDialog(null, "Registration successful. Please login.");
                    clearFields();
                    
                    // Close the registration panel
                    dispose(); // Close the registration window
                    
                    // Open the login panel
                    openLoginPanel();
                } else {
                    JOptionPane.showMessageDialog(null, "Registration failed. Please try again.");
                }
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(roleLabel);
        panel.add(roleComboBox);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(registerButton);

        add(panel);
        setVisible(true);
    }

    private boolean registerUser(String username, String password, String role) {
        // Check if the username already exists and return false if it does
        boolean usernameExists = userQueries.checkUsernameExist(username);

        if (usernameExists) {
            // Display an error message using JOptionPane
            JOptionPane.showMessageDialog(null, "Username already exists. Please choose another username.");
            return false;
        }

        // If the username doesn't exist, proceed with registration
        boolean registrationSuccess = userQueries.registerUser(username, password, role);

        return registrationSuccess;
    }

    private void clearFields() {
        // Clear the input fields after successful registration
        usernameField.setText("");
        passwordField.setText("");
        roleComboBox.setSelectedIndex(0); // Reset the role to the first option
    }
    
    private void openLoginPanel() {
        // Implement the code to open the login panel
        // You can create a new instance of LoginGUI or any other login-related code here
        LoginGUI loginGUI = new LoginGUI();
        loginGUI.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegisterGUI());
    }
}
