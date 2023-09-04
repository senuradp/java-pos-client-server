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

import com.syos.pos.menucommand.ShelfService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShelfUpdateGUI extends JFrame {
    private JTextField shelfCodeField;
    private JTextField productCodeField;
    private JTextField capacityField;
    private JTextField availableQtyField;
    private JLabel resultArea;

    private ShelfService shelfService;

    public ShelfUpdateGUI(ShelfService shelfService) {
        this.shelfService = shelfService;

        setTitle("Update Shelf");
        setSize(700, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a panel to hold the form components
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5); // Add some padding

        // Labels and input fields for shelf details
        JLabel shelfCodeLabel = new JLabel("Shelf Code:");
        shelfCodeField = new JTextField(20);
//        shelfCodeField.setEditable(false);

        JLabel productCodeLabel = new JLabel("Product Code:");
        productCodeField = new JTextField(20);

        JLabel capacityLabel = new JLabel("Capacity:");
        capacityField = new JTextField(20);

        JLabel availableQtyLabel = new JLabel("Available Quantity:");
        availableQtyField = new JTextField(20);

        // Create a "Get Shelf" button
        JButton getShelfButton = new JButton("Get Shelf");
        getShelfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the shelf code entered by the user
                String shelfCode = shelfCodeField.getText();

                // Call the service to retrieve shelf details
                String[] shelfDetails = shelfService.getByCode(shelfCode);

                if (shelfDetails != null) {
                    // Set the retrieved values to the text fields
                    productCodeField.setText(shelfDetails[0]); // Assuming product code is at index 1
                    capacityField.setText(shelfDetails[1]); // Assuming capacity is at index 2
                    availableQtyField.setText(shelfDetails[2]); // Assuming available quantity is at index 3
                } else {
                    // Handle the case where the shelf is not found
                    JOptionPane.showMessageDialog(null, "Shelf not found for code: " + shelfCode, "Shelf Not Found", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Create an "Update" button
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the updated input values
                String updatedShelfCode = shelfCodeField.getText();
                String updatedProductCode = productCodeField.getText();
                String updatedCapacity = capacityField.getText();
                String updatedAvailableQty = availableQtyField.getText();

                // Validate the input
                if (updatedProductCode.isEmpty() || updatedCapacity.isEmpty() || updatedAvailableQty.isEmpty()) {
                    resultArea.setText("Please fill in all fields.");
                } else {
                    try {
                        // Parse capacity and availableQty as doubles
                        double capacity = Double.parseDouble(updatedCapacity);
                        double availableQty = Double.parseDouble(updatedAvailableQty);

                        // Call the service to update the shelf
                        String result = shelfService.update(updatedShelfCode, updatedProductCode, capacity, availableQty);

                        // Display the result in the GUI
                        resultArea.setText(result);
                    } catch (NumberFormatException ex) {
                        resultArea.setText("Invalid quantity format.");
                    }
                }

                // Clear the input fields
                productCodeField.setText("");
                capacityField.setText("");
                availableQtyField.setText("");
            }
        });

        // Result area to display the output
        resultArea = new JLabel();

        // Add components to the panel with constraints
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(shelfCodeLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(shelfCodeField, constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        panel.add(getShelfButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(productCodeLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(productCodeField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(capacityLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(capacityField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(availableQtyLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(availableQtyField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 3; // Span across three columns
        panel.add(updateButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 3; // Span across three columns
        panel.add(resultArea, constraints);

        // Add the panel to the frame
        getContentPane().add(panel);

        // Center the frame on the screen
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ShelfService shelfService = new ShelfService();
                ShelfUpdateGUI updateShelfGUI = new ShelfUpdateGUI(shelfService);
                updateShelfGUI.setVisible(true);
            }
        });
    }
}
