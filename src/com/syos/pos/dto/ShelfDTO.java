/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.dto;

/**
 *
 * @author senu2k
 */
public class ShelfDTO {
    
    private String shelf_code;
    private String product_code;
    private double capacity;
    private double available_qty;

    public String getShelf_code() {
        return shelf_code;
    }

    public void setShelf_code(String shelf_code) {
        this.shelf_code = shelf_code;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getAvailable_qty() {
        return available_qty;
    }

    public void setAvailable_qty(double available_qty) {
        this.available_qty = available_qty;
    }

    
    
}
