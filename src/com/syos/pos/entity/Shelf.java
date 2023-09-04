/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.entity;

/**
 *
 * @author senu2k
 */
public class Shelf {
    
    private String shelf_code;
    private String product_code;
    private double capacity;
    private double available_qty;

    public Shelf() {
    }

    public Shelf(String shelf_code, String product_code, double capacity, double available_qty) {
        this.shelf_code = shelf_code;
        this.product_code = product_code;
        this.capacity = capacity;
        this.available_qty = available_qty;
    }


    @Override
    public String toString() {
        return "Shelf{"+ "Shelf code=" + shelf_code + "Product code=" + product_code + ", Capacity=" + capacity + ", Available qty=" + available_qty + '}';
    }

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
