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
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIMenuSystem extends JFrame {
    public GUIMenuSystem() {
        setTitle("Menu System");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold the buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));

        // Create "Login" button
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Load the login GUI when the "Login" button is clicked
                LoginGUI loginGUI = new LoginGUI();
                loginGUI.setVisible(true);
            }
        });

        // Create "Register" button
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Load the register GUI when the "Register" button is clicked
                RegisterGUI registerGUI = new RegisterGUI();
                registerGUI.setVisible(true);
            }
        });

        // Add buttons to the panel
        panel.add(loginButton);
        panel.add(registerButton);

        // Add the panel to the frame
        getContentPane().add(panel);

        // Center the frame on the screen
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUIMenuSystem menuSystem = new GUIMenuSystem();
                menuSystem.setVisible(true);
            }
        });
    }
}
