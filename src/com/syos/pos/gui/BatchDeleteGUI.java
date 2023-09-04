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

public class BatchDeleteGUI extends JFrame {
    private JTextField batchCodeField;
    private JLabel resultLabel;

    private BatchService batchService;

    public BatchDeleteGUI(BatchService batchService) {
        this.batchService = batchService;

        setTitle("Delete Batch");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a panel to hold the form components
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5); // Add some padding

        // Label and input field for batch code
        JLabel batchCodeLabel = new JLabel("Batch Code:");
        batchCodeField = new JTextField(20);

        // Create a "Delete Batch" button
        JButton deleteBatchButton = new JButton("Delete Batch");
        deleteBatchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the batch code entered by the user
                String batchCode = batchCodeField.getText();

                // Call the BatchService to delete the batch
                String deletionResult = batchService.delete(batchCode);

                // Display the result in the GUI
                resultLabel.setText(deletionResult);
            }
        });

        // Result area to display the output
        resultLabel = new JLabel();

        // Add components to the panel with constraints
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(batchCodeLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(batchCodeField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(deleteBatchButton, constraints);

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
                BatchService batchService = new BatchService();
                BatchDeleteGUI deleteBatchGUI = new BatchDeleteGUI(batchService);
                deleteBatchGUI.setVisible(true);
            }
        });
    }
}

