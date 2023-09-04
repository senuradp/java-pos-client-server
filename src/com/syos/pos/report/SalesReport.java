/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.report;

import com.syos.pos.config.DBConnection;
import com.syos.pos.core.RepositoryCRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author senu2k
 */
public class SalesReport implements IReport {

    @Override
    public String generateReport() {
        return null;
    }

    @Override
    public String generateReportByDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(date);

        String sql = "SELECT bd.product_code, p.name AS product_name, bd.product_qty, bd.total_item_price " +
                "FROM bill_detail bd " +
                "JOIN product p ON bd.product_code = p.product_code " +
                "JOIN bill_header bh ON bd.bill_serial_number = bh.bill_serial_number " +
                "WHERE bh.date = ?";

        try (Connection connection = DBConnection.getInstance().getConnectionFromPool();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, formattedDate);
            ResultSet resultSet = statement.executeQuery();

            double totalRevenue = 0;
            int totalQuantity = 0;

            StringBuilder reportBuilder = new StringBuilder();
            reportBuilder.append("Sales Report for ").append(formattedDate).append(":\n");
            reportBuilder.append("==========================================\n");

            while (resultSet.next()) {
                String productCode = resultSet.getString("product_code");
                String productName = resultSet.getString("product_name");
                int quantity = resultSet.getInt("product_qty");
                double itemPrice = resultSet.getDouble("total_item_price");

                reportBuilder.append("Product Code: ").append(productCode).append("\n");
                reportBuilder.append("Product Name: ").append(productName).append("\n");
                reportBuilder.append("Quantity: ").append(quantity).append("\n");
                reportBuilder.append("Item Price: ").append(itemPrice).append("\n");

                totalRevenue += itemPrice;
                totalQuantity += quantity;

                reportBuilder.append("------------------------------------------\n");
            }

            reportBuilder.append("Total Quantity: ").append(totalQuantity).append(" items\n");
            reportBuilder.append("Total Revenue: LKR ").append(totalRevenue).append("\n");
            reportBuilder.append("==========================================\n");

            resultSet.close();
            statement.close();

            // Return the connection to the pool when done
            DBConnection.getInstance().returnConnectionToPool(connection);

            return reportBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately
            return "An error occurred while generating the report.";
        }
    }
}
