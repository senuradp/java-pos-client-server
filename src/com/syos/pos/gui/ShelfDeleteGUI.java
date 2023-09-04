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

public class ShelfDeleteGUI extends JFrame {
    private JTextField shelfCodeField;
    private JLabel resultLabel;

    private ShelfService shelfService;

    public ShelfDeleteGUI(ShelfService shelfService) {
        this.shelfService = shelfService;

        setTitle("Delete Shelf");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a panel to hold the form components
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5); // Add some padding

        // Label and input field for shelf code
        JLabel shelfCodeLabel = new JLabel("Shelf Code:");
        shelfCodeField = new JTextField(20);

        // Create a "Delete Shelf" button
        JButton deleteShelfButton = new JButton("Delete Shelf");
        deleteShelfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the shelf code entered by the user
                String shelfCode = shelfCodeField.getText();

                // Call the ShelfService to delete the shelf
                String deletionResult = shelfService.delete(shelfCode);

                // Display the result in the GUI
                resultLabel.setText(deletionResult);
            }
        });

        // Result area to display the output
        resultLabel = new JLabel();

        // Add components to the panel with constraints
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(shelfCodeLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(shelfCodeField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(deleteShelfButton, constraints);

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
                ShelfService shelfService = new ShelfService();
                ShelfDeleteGUI deleteShelfGUI = new ShelfDeleteGUI(shelfService);
                deleteShelfGUI.setVisible(true);
            }
        });
    }
}
