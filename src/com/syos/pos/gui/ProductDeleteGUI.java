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
import com.syos.pos.menucommand.ProductService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductDeleteGUI extends JFrame {
    private JTextField productCodeField;
    private JLabel resultLabel;

    private ProductService productService;

    public ProductDeleteGUI(ProductService productService) {
        this.productService = productService;

        setTitle("Delete Product");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a panel to hold the form components
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5); // Add some padding

        // Label and input field for product code
        JLabel productCodeLabel = new JLabel("Product Code:");
        productCodeField = new JTextField(20);

        // Create a "Delete Product" button
        JButton deleteProductButton = new JButton("Delete Product");
        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the product code entered by the user
                String productCode = productCodeField.getText();

                // Call the ProductService to delete the product
                String deletionResult = productService.delete(productCode);

                // Display the result in the GUI
                resultLabel.setText(deletionResult);
            }
        });

        // Result area to display the output
        resultLabel = new JLabel();

        // Add components to the panel with constraints
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(productCodeLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(productCodeField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(deleteProductButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2; // Span across two columns
        panel.add(resultLabel, constraints);

        // Add the panel to the frame
        getContentPane().add(panel);

        // Center the frame on the screen
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ProductService productService = new ProductService();
                ProductDeleteGUI deleteProductGUI = new ProductDeleteGUI(productService);
                deleteProductGUI.setVisible(true);
            }
        });
    }
}