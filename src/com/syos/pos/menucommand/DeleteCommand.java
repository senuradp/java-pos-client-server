/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.menucommand;

import com.syos.pos.gui.BatchDeleteGUI;
import com.syos.pos.gui.ProductDeleteGUI;
import com.syos.pos.gui.ShelfDeleteGUI;
import java.util.Scanner;

/**
 *
 * @author senu2k
 */
public class DeleteCommand implements Command {
    private final BatchService batchService;
    private final ProductService productService;
    private final ShelfService shelfService;
    private final OrderServiceMenu orderService;
    String entity; // package-private access

    public DeleteCommand(String entity, BatchService batchService, ProductService productService, ShelfService shelfService, OrderServiceMenu orderService) {
        this.entity = entity;
        this.batchService = batchService;
        this.productService = productService;
        this.shelfService = shelfService;
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        switch (entity) {
            case "batch":
                BatchDeleteGUI batchdeleteGUI = new BatchDeleteGUI(batchService);
                batchdeleteGUI.setVisible(true);
                break;
            case "product":
                ProductDeleteGUI productDeleteGUI = new ProductDeleteGUI(productService);
                productDeleteGUI.setVisible(true);
                break;
            case "shelf":
                ShelfDeleteGUI shelfDeleteGUI = new ShelfDeleteGUI(shelfService);
                shelfDeleteGUI.setVisible(true);
                break;
            case "order":
                orderService.delete();
                break;
            default:
                System.out.println("Invalid entity!");
                break;
        }
    }
}
