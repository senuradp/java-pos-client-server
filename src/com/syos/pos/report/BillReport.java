/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.report;

import com.syos.pos.core.RepositoryCRUD;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author senu2k
 */

public class BillReport implements IReport {

    @Override
    public String generateReport() {
        try {
            String sql = "SELECT bh.bill_serial_number, bh.payment_type, bh.date, " +
                    "bh.total_bill_price, bh.amount_tendered, bh.discount, bh.balance, " +
                    "bd.product_code, bd.product_name, bd.product_qty, bd.product_price, bd.total_item_price " +
                    "FROM bill_header bh " +
                    "JOIN bill_detail bd ON bh.bill_serial_number = bd.bill_serial_number";
            ResultSet resultSet = RepositoryCRUD.executeQuery(sql);

            StringBuilder reportBuilder = new StringBuilder();
            reportBuilder.append("==========================================\n");
            reportBuilder.append("============== Bill Report ===============\n");
            reportBuilder.append("==========================================\n");
            String currentBillSerialNumber = "";
            while (resultSet.next()) {
                String billSerialNumber = resultSet.getString("bill_serial_number");
                String paymentType = resultSet.getString("payment_type");
                String date = resultSet.getString("date");
                double totalBillPrice = resultSet.getDouble("total_bill_price");
                double amountTendered = resultSet.getDouble("amount_tendered");
                double discount = resultSet.getDouble("discount");
                double balance = resultSet.getDouble("balance");
                String productCode = resultSet.getString("product_code");
                String productName = resultSet.getString("product_name");
                int productQty = resultSet.getInt("product_qty");
                double productPrice = resultSet.getDouble("product_price");
                double totalItemPrice = resultSet.getDouble("total_item_price");

                if (!billSerialNumber.equals(currentBillSerialNumber)) {
                    reportBuilder.append("==========================================\n");
                    reportBuilder.append("Bill Serial Number: ").append(billSerialNumber).append("\n");
                    reportBuilder.append("==========================================\n");
                    reportBuilder.append("Payment Type: ").append(paymentType).append("\n");
                    reportBuilder.append("Date: ").append(date).append("\n");
                    reportBuilder.append("Total Bill Price: ").append(totalBillPrice).append("\n");
                    reportBuilder.append("Amount Tendered: ").append(amountTendered).append("\n");
                    reportBuilder.append("Discount: ").append(discount).append("\n");
                    reportBuilder.append("Balance: ").append(balance).append("\n");
                    reportBuilder.append("------------------------------------------\n");
                    currentBillSerialNumber = billSerialNumber;
                }

                reportBuilder.append("Product Code: ").append(productCode).append("\n");
                reportBuilder.append("Product Name: ").append(productName).append("\n");
                reportBuilder.append("Quantity: ").append(productQty).append("\n");
                reportBuilder.append("Price per Item: ").append(productPrice).append("\n");
                reportBuilder.append("Total Item Price: ").append(totalItemPrice).append("\n");
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
        return null;
    }
}
