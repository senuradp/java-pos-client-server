/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.menucommand;

import com.syos.pos.gui.*;
import java.util.Scanner;

/**
 *
 * @author senu2k
 */
public class AddCommand implements Command {
    private final BatchService batchService;
    private final ProductService productService;
    private final ShelfService shelfService;
    private final OrderServiceMenu orderService;
    String entity; // package-private access

    public AddCommand(String entity, BatchService batchService, ProductService productService, ShelfService shelfService, OrderServiceMenu orderService) {
        this.entity = entity;
        this.batchService = batchService;
        this.productService = productService;
        this.shelfService = shelfService;
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        // Check the entity and open the corresponding GUI
        switch (entity.toLowerCase()) {
            case "batch":
                BatchAddGUI batchAddGUI = new BatchAddGUI(batchService);
                batchAddGUI.setVisible(true);
                break;
            case "product":
                ProductAddGUI productAddGUI = new ProductAddGUI(productService);
                productAddGUI.setVisible(true);
                break;
            case "shelf":
                ShelfAddGUI shelfAddGUI = new ShelfAddGUI(shelfService);
                shelfAddGUI.setVisible(true);
                break;
            case "order":
//                OrderAddGUI orderAddGUI = new OrderAddGUI(orderService);
//                orderAddGUI.setVisible(true);
                break;
            default:
                System.out.println("Invalid entity!");
                break;
        }
    }
    
}
