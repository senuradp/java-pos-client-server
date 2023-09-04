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
public class BillDetailDTO {
    
    private String bill_serial_number;
    private String product_code;
    private String item_name;
    private double item_qty;
    private double item_price;
    private double total_item_price;

    public BillDetailDTO(String bill_serial_number, String product_code, String item_name, double item_qty, double item_price) {
        this.product_code = product_code;
        this.bill_serial_number = bill_serial_number;
        this.item_name = item_name;
        this.item_qty = item_qty;
        this.item_price = item_price;
        this.total_item_price = this.item_price * this.item_qty;
    }

    public BillDetailDTO() {
      
    }
    
   
    public String getBill_serial_number() {
        return bill_serial_number;
    }

    public void setBill_serial_number(String bill_serial_number) {
        this.bill_serial_number = bill_serial_number;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }


    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public double getItem_qty() {
        return item_qty;
    }

    public void setItem_qty(double item_qty) {
        this.item_qty = item_qty;
    }

    public double getItem_price() {
        return item_price;
    }

    public void setItem_price(double item_price) {
        this.item_price = item_price;
    }

    public double getTotal_item_price() {
        return total_item_price;
    }

    public void setTotal_item_price(double total_item_price) {
        this.total_item_price = total_item_price;
    }
    
    
    
}
