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

import com.syos.pos.report.FacadeReport;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

public class MainMenuGUI extends JFrame {

    private String username,role;
    private JComboBox<String> menuSelectionComboBox;
    private JComboBox<String> submenuSelectionComboBox;
    private JComboBox<String> actionSelectionComboBox;
    private JLabel menuSelectionComboBoxLabel;
    private JLabel submenuSelectionComboBoxLabel;
    private JLabel actionSelectionComboBoxLabel;
    
    private JButton submitButton; 
    
    private JTable reportTable;
    private DefaultTableModel tableModel;

    private FacadeReport report = new FacadeReport();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public MainMenuGUI(String username,String role) {
        this.username = username;
        this.role = role;

        setTitle("Main Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create the "File" menu
        JMenu fileMenu = new JMenu("Exit");
        JMenuItem exitMenuItem = new JMenuItem("Logout");

        // Add action listener to the "Exit" menu item
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform any necessary cleanup and exit the application
                System.exit(0);
            }
        });

        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        // Create the menu selection combo box
        menuSelectionComboBoxLabel = new JLabel("Select Menu:");
        menuSelectionComboBox = new JComboBox<>();
        menuSelectionComboBox.addItem("");
        menuSelectionComboBox.addItem("Normal Menu");
        menuSelectionComboBox.addItem("Restock Menu");
        menuSelectionComboBox.addItem("Report Menu");

        // Create the submenu selection combo box
        submenuSelectionComboBoxLabel = new JLabel("Select Submenu:");
        submenuSelectionComboBox = new JComboBox<>();

        // Create the action selection combo box
        actionSelectionComboBoxLabel = new JLabel("Select Action:");
        actionSelectionComboBox = new JComboBox<>();

        // Create the Submit button
        submitButton = new JButton("Submit");

        // Initialize the table and its model
//        reportTable = new JTable();
//        tableModel = new DefaultTableModel();
//        reportTable.setModel(tableModel);
//        
//        // Add the table to a JScrollPane for scrollability
//        JScrollPane scrollPane = new JScrollPane(reportTable);
        // Add action listener to the menu selection combo box
        menuSelectionComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the submenu and action combo boxes based on the selected menu
                updateSubmenuAndActionComboBoxes();
            }
        });

        // Add action listener to the Submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the submission here
                String selectedMenu = (String) menuSelectionComboBox.getSelectedItem();
                String selectedSubmenu = (String) submenuSelectionComboBox.getSelectedItem();

                if ("Normal Menu".equals(selectedMenu)) {

                    MenuInvokerGUI menuInvokerGUI = new MenuInvokerGUI(username, role);
                    menuInvokerGUI.setVisible(true);

                } else if ("Restock Menu".equals(selectedMenu)) {

                    RestockGUI restockGUI = new RestockGUI();
                    restockGUI.setVisible(true);

                } else if ("Report Menu".equals(selectedMenu)) {

                    // Handle report generation based on user input
                    if ("Sales report".equals(selectedSubmenu)) {
                        // Get user input for date using a dialog
                        String date = JOptionPane.showInputDialog(null, "Enter date in yyyy-MM-dd format:");

                        if (date != null) { // Check if the user didn't cancel the input dialog
                            try {
                                // Generate the Sales report as a single String
                                String salesReport = report.getSalesReport().generateReportByDate(dateFormat.parse(date));

                                // Create a JTextArea to display the report content
                                JTextArea textArea = new JTextArea(20, 40);
                                textArea.setEditable(false);
                                textArea.setText(salesReport); // Set the report text

                                // Create a JScrollPane for the JTextArea
                                JScrollPane scrollPane = new JScrollPane(textArea);

                                // Show the report in a dialog
                                JOptionPane.showMessageDialog(null, scrollPane, "Sales Report", JOptionPane.PLAIN_MESSAGE);
                            } catch (ParseException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            // User canceled the input, you can handle this as needed
                            JOptionPane.showMessageDialog(null, "Date input canceled by the user.");
                        }

                    } else if ("Shelf report".equals(selectedSubmenu)) {

//                        // Get user input for date using a dialog
                        String date = JOptionPane.showInputDialog(null, "Enter date in yyyy-MM-dd format:");

                        if (date != null) { // Check if the user didn't cancel the input dialog
                            try {
                                // Generate the Shelf report as a single String
                                String shelfReport = report.getShelfReport().generateReportByDate(dateFormat.parse(date));;

                                // Create a JTextArea to display the report content
                                JTextArea textArea = new JTextArea(20, 40);
                                textArea.setEditable(false);
                                textArea.setText(shelfReport); // Set the report text

                                // Create a JScrollPane for the JTextArea
                                JScrollPane scrollPane = new JScrollPane(textArea);

                                // Show the report in a dialog
                                JOptionPane.showMessageDialog(null, scrollPane, "Shelf Report", JOptionPane.PLAIN_MESSAGE);
                            } catch (ParseException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            // User canceled the input, you can handle this as needed
                            JOptionPane.showMessageDialog(null, "Date input canceled by the user.");
                        }

                    } else if ("Stock report".equals(selectedSubmenu)) {
                        
                        try {
                            // Generate the Stock report as a single String
                            String stockReport = report.getStockReport().generateReport();;

                            // Create a JTextArea to display the report content
                            JTextArea textArea = new JTextArea(20, 40);
                            textArea.setEditable(false);
                            textArea.setText(stockReport); // Set the report text

                            // Create a JScrollPane for the JTextArea
                            JScrollPane scrollPane = new JScrollPane(textArea);

                            // Show the report in a dialog
                            JOptionPane.showMessageDialog(null, scrollPane, "Stock Report", JOptionPane.PLAIN_MESSAGE);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        
                    } else if ("Bill report".equals(selectedSubmenu)) {
                        
                        try {
                            // Generate the Bill report as a single String
                            String billReport = report.getBillReport().generateReport();

                            // Create a JTextArea to display the report content
                            JTextArea textArea = new JTextArea(20, 40);
                            textArea.setEditable(false);
                            textArea.setText(billReport); // Set the report text

                            // Create a JScrollPane for the JTextArea
                            JScrollPane scrollPane = new JScrollPane(textArea);

                            // Show the report in a dialog
                            JOptionPane.showMessageDialog(null, scrollPane, "Bill Report", JOptionPane.PLAIN_MESSAGE);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid report choice. Please try again.");
                    }

                }
            }
        });
        

        // Add the combo boxes and Submit button to a panel
        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new GridLayout(4, 2, 10, 10));
        comboBoxPanel.add(menuSelectionComboBoxLabel);
        comboBoxPanel.add(menuSelectionComboBox);
        comboBoxPanel.add(submenuSelectionComboBoxLabel);
        comboBoxPanel.add(submenuSelectionComboBox);
        comboBoxPanel.add(actionSelectionComboBoxLabel);
        comboBoxPanel.add(actionSelectionComboBox);
        comboBoxPanel.add(submitButton); // Add the Submit button to the panel

        // Add the combo box panel to the frame
        getContentPane().add(comboBoxPanel, BorderLayout.NORTH);

        // Set the menu bar for the frame
        setJMenuBar(menuBar);
    }

    private void updateSubmenuAndActionComboBoxes() {
        String selectedMenu = (String) menuSelectionComboBox.getSelectedItem();
        submenuSelectionComboBox.removeAllItems();
        actionSelectionComboBox.removeAllItems();

        if ("Normal Menu".equals(selectedMenu)) {

            submenuSelectionComboBoxLabel.setVisible(false);
            submenuSelectionComboBox.setVisible(false);

            actionSelectionComboBoxLabel.setVisible(false);
            actionSelectionComboBox.setVisible(false);

        } else if ("Restock Menu".equals(selectedMenu)) {
                                   
            submenuSelectionComboBoxLabel.setVisible(false);
            submenuSelectionComboBox.setVisible(false);

            // No actions for "Report Menu"
            actionSelectionComboBoxLabel.setVisible(false);
            actionSelectionComboBox.setVisible(false);
            
            
        }else if ("Report Menu".equals(selectedMenu)) {

            submenuSelectionComboBox.addItem("Sales report");
            submenuSelectionComboBox.addItem("Shelf report");
            submenuSelectionComboBox.addItem("Stock report");
            submenuSelectionComboBox.addItem("Bill report");
            
            submenuSelectionComboBoxLabel.setVisible(true);
            submenuSelectionComboBox.setVisible(true);

            // No actions for "Report Menu"
            actionSelectionComboBoxLabel.setVisible(false);
            actionSelectionComboBox.setVisible(false);
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
            }
        });
    }
}
