/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.menucommand;

import java.util.Scanner;

/**
 *
 * @author senu2k
 */
public class GetAllCommand implements Command {
    private final BatchService batchService;
    private final ProductService productService;
    private final ShelfService shelfService;
    private final OrderServiceMenu orderService;
    String entity; // package-private access

    GetAllCommand(BatchService batchService, ProductService productService, ShelfService shelfService, OrderServiceMenu orderService) {
        this.batchService = batchService;
        this.productService = productService;
        this.shelfService = shelfService;
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        switch (entity) {
            case "batch":
                batchService.getAll();
                break;
            case "product":
                productService.getAll();
                break;
            case "shelf":
                shelfService.getAll();
                break;
            case "order":
                orderService.getAll();
                break;
            default:
                System.out.println("Invalid entity!");
                break;
        }
    }
}
