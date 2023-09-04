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
import com.syos.pos.menucommand.BatchService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BatchAddGUI extends JFrame {
    private JTextField batchCodeField;
    private JTextField productCodeField;
    private JTextField expiryDateField;
    private JTextField purchaseDateField;
    private JTextField batchQtyField;
    private JTextField availableQtyField;
    private JComboBox<String> isSoldComboBox;
    private JLabel resultArea;
    
    private final BatchService batchService; // Accept the service in the constructor

    public BatchAddGUI(BatchService batchService) {
        
        this.batchService = batchService;
        
        setTitle("Add Batch");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a panel to hold the form components
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5); // Add some padding

        // Labels and input fields for batch details
        JLabel batchCodeLabel = new JLabel("Batch Code:");
        batchCodeField = new JTextField(20);

        JLabel productCodeLabel = new JLabel("Product Code:");
        productCodeField = new JTextField(20);

        JLabel expiryDateLabel = new JLabel("Expiry Date (yyyy-MM-dd):");
        expiryDateField = new JTextField(20);

        JLabel purchaseDateLabel = new JLabel("Purchase Date (yyyy-MM-dd):");
        purchaseDateField = new JTextField(20);

        JLabel batchQtyLabel = new JLabel("Batch Quantity:");
        batchQtyField = new JTextField(20);

        JLabel availableQtyLabel = new JLabel("Available Quantity:");
        availableQtyField = new JTextField(20);

        JLabel isSoldLabel = new JLabel("Is Sold:");
        String[] isSoldOptions = {"Yes", "No"};
        isSoldComboBox = new JComboBox<>(isSoldOptions);

        // Create a "Add Batch" button
        JButton addBatchButton = new JButton("Add Batch");
        addBatchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the input values from the GUI
                String batchCode = batchCodeField.getText();
                String productCode = productCodeField.getText();
                String expiryDate = expiryDateField.getText();
                String purchaseDate = purchaseDateField.getText();
                double batchQty = Double.parseDouble(batchQtyField.getText());
                double availableQty = Double.parseDouble(availableQtyField.getText());
                boolean isSold = isSoldComboBox.getSelectedItem().equals("Yes");

                // Call the batch service to add the batch
                String result = batchService.add(batchCode, productCode, expiryDate, purchaseDate, batchQty, availableQty, isSold);

                // Display the result in the GUI
                resultArea.setText(result);
            }
        });

        // Result area to display the output
        resultArea = new JLabel();

        // Batch Code
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(batchCodeLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(batchCodeField, constraints);

        // Product Code
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(productCodeLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(productCodeField, constraints);

        // Expiry Date
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(expiryDateLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(expiryDateField, constraints);

        // Purchase Date
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(purchaseDateLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(purchaseDateField, constraints);

        // Batch Quantity
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(batchQtyLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        panel.add(batchQtyField, constraints);

        // Available Quantity
        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(availableQtyLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        panel.add(availableQtyField, constraints);

        // Is Sold
        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(isSoldLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 6;
        panel.add(isSoldComboBox, constraints);

        // Add Batch button
        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 2; // Span across two columns
        panel.add(addBatchButton, constraints);

        // Result area
        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.gridwidth = 2; // Span across two columns
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
                
                BatchService batchService = new BatchService();
                BatchAddGUI addBatchGUI = new BatchAddGUI(batchService);
                addBatchGUI.setVisible(true);
            }
        });
    }
}
