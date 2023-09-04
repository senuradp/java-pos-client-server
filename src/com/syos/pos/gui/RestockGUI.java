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

import com.syos.pos.controller.ProductController;
import com.syos.pos.controller.ShelfController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestockGUI extends JFrame {
    private JTextField productCodeField;
    private JTextField quantityField;
    private JButton restockButton;

    public RestockGUI() {
        setTitle("Restock Shelf");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        JLabel productCodeLabel = new JLabel("Enter product code:");
        productCodeField = new JTextField(10);

        JLabel quantityLabel = new JLabel("Enter quantity to restock:");
        quantityField = new JTextField(10);

        restockButton = new JButton("Restock");

        // Create panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.add(productCodeLabel);
        panel.add(productCodeField);
        panel.add(quantityLabel);
        panel.add(quantityField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(restockButton);

        // Add action listener to the restock button
        restockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restockShelf();
            }
        });

        // Add panel to the frame
        getContentPane().add(panel, BorderLayout.CENTER);
    }

    private void restockShelf() {
        String productCode = productCodeField.getText();
        String quantityText = quantityField.getText();

        if (productCode.isEmpty() || quantityText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter product code and quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double quantity;
        try {
            quantity = Double.parseDouble(quantityText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid quantity format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ShelfController shelfController = new ShelfController();
        ProductController productController = new ProductController();

        if (productController.checkProductCodeExists(productCode)) {
            try {
                shelfController.reStockShelf(productCode, quantity);
                JOptionPane.showMessageDialog(this, "Shelf restocked successfully!");
                // You can close the GUI or perform other actions here
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to restock shelf.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Product code not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                RestockGUI restockGUI = new RestockGUI();
                restockGUI.setVisible(true);
            }
        });
    }
}
