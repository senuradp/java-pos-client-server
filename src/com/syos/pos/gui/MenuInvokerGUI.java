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

import com.syos.pos.menucommand.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.syos.pos.menucommand.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuInvokerGUI extends JFrame {
    private JComboBox<String> entityComboBox;
    private JComboBox<String> operationComboBox;
    private JButton executeButton;
    private String username, role;

    public MenuInvokerGUI(String username, String role) {
        this.username = username; 
        this.role = role; 
        
        setTitle("Menu Invoker " +username + role);
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold the form components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        // Label and combo box for selecting the entity
        JLabel entityLabel = new JLabel("Select Entity:");
        String[] entities = {"Batch", "Product", "Shelf", "Order"};
        entityComboBox = new JComboBox<>(entities);

        // Label and combo box for selecting the operation
        JLabel operationLabel = new JLabel("Select Operation:");
        String[] operations = {"Add", "Update", "Delete", "Get All"};
        operationComboBox = new JComboBox<>(operations);

        // Create an "Execute" button
        executeButton = new JButton("Execute");
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected entity and operation
                String selectedEntity = entityComboBox.getSelectedItem().toString().toLowerCase();
                String selectedOperation = operationComboBox.getSelectedItem().toString();

                String userType = role;

                // Create the necessary services
                BatchService batchService = new BatchService();
                ProductService productService = new ProductService();
                ShelfService shelfService = new ShelfService();
                OrderServiceMenu orderService = new OrderServiceMenu();

                // Execute the command based on the selected entity and operation
                switch (selectedOperation.toLowerCase()) {
                    case "add":
                        Command addCommand = new AddCommand(selectedEntity, batchService, productService, shelfService, orderService);
                        execute(addCommand, userType);
                        break;
                    case "update":
                        Command updateCommand = new UpdateCommand(selectedEntity, batchService, productService, shelfService, orderService);
                        execute(updateCommand, userType);
                        break;
                    case "delete":
                        Command deleteCommand = new DeleteCommand(selectedEntity, batchService, productService, shelfService, orderService);
                        execute(deleteCommand, userType);
                        break;
                    case "get all":
                        // Create and execute the get all command if needed
                        break;
                    default:
                        System.out.println("Invalid operation!");
                        break;
                }
            }
        });

        // Add components to the panel
        panel.add(entityLabel);
        panel.add(entityComboBox);
        panel.add(operationLabel);
        panel.add(operationComboBox);
        panel.add(executeButton);

        // Add the panel to the frame
        getContentPane().add(panel);

        // Center the frame on the screen
        setLocationRelativeTo(null);
    }

    private void execute(Command command, String userType) {
        // if user type is cashier, restrict access to delete operation
        if (userType.equals("cashier") && command instanceof DeleteCommand) {
            JOptionPane.showMessageDialog(null, "You don't have access to this operation!", "No Access", JOptionPane.ERROR_MESSAGE);
        } else {
            command.execute();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               // Replace these values with the actual username
                String username = null;
                String role = null;

                MainMenuGUI mainMenu = new MainMenuGUI(username, role);
                mainMenu.setVisible(true);

                // Create an instance of MenuInvokerGUI and pass the username
                MenuInvokerGUI menuInvokerGUI = new MenuInvokerGUI(username, role);
                menuInvokerGUI.setVisible(true);
            }
        });
    }
}
