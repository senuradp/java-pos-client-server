/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.menucommand;

import com.syos.pos.controller.ProductController;
import com.syos.pos.service.OrderService;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author senu2k
 */
public class OrderServiceMenu {
    void add() {
         try {
            Scanner scanner = new Scanner(System.in);
            ProductController productController = new ProductController();
            OrderService orderService = OrderService.getInstance();
            orderService.createOrder();
            
            System.out.println("Adding a new order...");
            
            
            while (true) {
                System.out.println("Enter product code (or 'done' to finish adding products): ");
                String productCode = scanner.nextLine();
                
                if (productCode.equals("done")) {
                    break; // Exit the loop if 'done' is entered
                }
                
                System.out.println("Enter quantity: ");
                double quantity = scanner.nextDouble();
                
                // Check if the product code exists
                boolean productCodeExists = productController.checkProductCodeExists(productCode);
                
                if (!productCodeExists) {
                    System.out.println("Product code does not exist!");
                    continue; // Skip to the next iteration if product code does not exist
                }
                
                orderService.addOrderProduct(productCode, quantity);
                System.out.println("Product added to the order!");
                
                // Clear the scanner buffer
                scanner.nextLine();
            }
            
            // Get payment type and discount once
            System.out.println("Enter payment type (Cash/Card): ");
            String pmt_type = scanner.nextLine();
            
            System.out.println("Enter customer amount: ");
            double customer_amount = scanner.nextDouble();
            
            System.out.println("Enter discount: ");
            double discount = scanner.nextDouble();
            
            // Perform payment and checkout
            orderService.addDiscount(discount);
//            orderService.checkoutPay(customer_amount, pmt_type);
            
            
//            double balanceAmount = orderService.calculateBalancePay(customer_amount);
            double balanceAmount = orderService.checkoutPay(customer_amount, pmt_type);

//            balance
            System.out.println("Balance: " + balanceAmount);
             

            System.out.println("Order completed!");
            
        } catch (Exception ex) {
            Logger.getLogger(OrderServiceMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void update() {
        System.out.println("Not available");
    }

    void delete() {
        System.out.println("Not available");
    }

    void getAll() {
        System.out.println("Not available");
    }
}
