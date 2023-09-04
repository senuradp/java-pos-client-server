/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.report;

import com.syos.pos.core.RepositoryCRUD;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author senu2k
 */
public class StockReport implements IReport {

    @Override
    public String generateReport() {
        try {
            String sql = "SELECT b.batch_code, b.purchase_date, b.expiry_date, " +
                    "b.batch_qty, b.available_qty, p.name AS product_name " +
                    "FROM batch b " +
                    "JOIN product p ON b.product_code = p.product_code";
            ResultSet resultSet = RepositoryCRUD.executeQuery(sql);

            StringBuilder reportBuilder = new StringBuilder();
            reportBuilder.append("Stock Report:\n");
            reportBuilder.append("==========================================\n");
            while (resultSet.next()) {
                String batchCode = resultSet.getString("batch_code");
                String purchaseDate = resultSet.getString("purchase_date");
                String expiryDate = resultSet.getString("expiry_date");
                int batchQty = resultSet.getInt("batch_qty");
                int availableQty = resultSet.getInt("available_qty");
                String productName = resultSet.getString("product_name");

                reportBuilder.append("Batch Code: ").append(batchCode).append("\n");
                reportBuilder.append("Product Name: ").append(productName).append("\n");
                reportBuilder.append("Purchase Date: ").append(purchaseDate).append("\n");
                reportBuilder.append("Expiry Date: ").append(expiryDate).append("\n");
                reportBuilder.append("Quantity Received: ").append(batchQty).append("\n");
                reportBuilder.append("Available Quantity: ").append(availableQty).append("\n");
                reportBuilder.append("------------------------------------------\n");
            }

            reportBuilder.append("==========================================\n");

            resultSet.close();

            return reportBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately
            return "An error occurred while generating the report.";
        }
    }

    @Override
    public String generateReportByDate(Date date) {
        // Implement this method to generate a report by date if needed
        return "Not implemented yet"; // You can replace this with the actual implementation
    }
}
