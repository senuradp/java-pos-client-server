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

public class ProductUpdateGUI extends JFrame {
    private JTextField productCodeField;
    private JTextField productNameField;
    private JTextField productPriceField;
    private JLabel resultArea;
    
    private ProductService productService;

    public ProductUpdateGUI(ProductService productService) {
        this.productService = productService;
        
        setTitle("Update Product");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a panel to hold the form components
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5); // Add some padding

        // Label and input field for product code
        JLabel productCodeLabel = new JLabel("Product Code:");
        productCodeField = new JTextField(20);

        // Create a "Get Product" button
        JButton getProductButton = new JButton("Get Product");
        getProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the product code entered by the user
                String productCode = productCodeField.getText();

                // Call the service to retrieve product details
                String[] productDetails = productService.getByCode(productCode);

                if (productDetails != null) {
                    // Set the retrieved values to the text fields
                    productNameField.setText(productDetails[0]); // Assuming the product name is at index 0
                    productPriceField.setText(productDetails[1]); // Assuming the product price is at index 1
                } else {
                    // Handle the case where the product is not found
                    JOptionPane.showMessageDialog(null, "Product not found for code: " + productCode, "Product Not Found", JOptionPane.ERROR_MESSAGE);
                    System.out.println("not found");
                }
            }
        });

        // Labels and input fields for product details
        JLabel productNameLabel = new JLabel("Product Name:");
        productNameField = new JTextField(20);

        JLabel productPriceLabel = new JLabel("Price:");
        productPriceField = new JTextField(20);

        // Create an "Update" button
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the updated input values
                String updatedProductCode = productCodeField.getText();
                String updatedProductName = productNameField.getText();
                String updatedProductPriceText = productPriceField.getText();
                
                // Validate the input
                if (updatedProductCode.isEmpty() || updatedProductName.isEmpty() || updatedProductPriceText.isEmpty()) {
                    resultArea.setText("Please fill in all fields.");
                } else {
                    try {
                        // Parse the product price as a double
                        double productPrice = Double.parseDouble(updatedProductPriceText);

                        // Call the service to add the product
                        String result = productService.update(updatedProductCode, updatedProductName, productPrice);

                        // Display the result in the GUI
                        resultArea.setText(result);
                    } catch (NumberFormatException ex) {
                        resultArea.setText("Invalid product price format.");
                    }
                }

                // Clear the input fields
                productCodeField.setText("");
                productNameField.setText("");
                productPriceField.setText("");
            }
        });

        // Result area to display the output
        resultArea = new JLabel();
//        JScrollPane resultScrollPane = new JScrollPane(resultArea);

        // Add components to the panel
        // Add components to the panel with constraints
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(productCodeLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(productCodeField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(getProductButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(productNameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(productNameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(productPriceLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(productPriceField, constraints);

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
                ProductService productService = new ProductService();
                ProductUpdateGUI updateProductGUI = new ProductUpdateGUI(productService);
                updateProductGUI.setVisible(true);
            }
        });
    }
}