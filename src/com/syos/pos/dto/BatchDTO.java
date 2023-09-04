/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.dto;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author senu2k
 */
public class BatchDTO {
    
    private String batch_code;
    private Date expiry_date;
    private Date purchase_date;
    private String product_code;
    private double batch_qty;
    private double available_qty;
    private boolean is_sold; // in store | in shelf

    public String getBatch_code() {
        return batch_code;
    }

    public void setBatch_code(String batch_code) {
        this.batch_code = batch_code;
    }

    public Date getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(Date expiry_date) {
        this.expiry_date = expiry_date;
    }

    public Date getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(Date purchase_date) {
        this.purchase_date = purchase_date;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public double getBatch_qty() {
        return batch_qty;
    }

    public void setBatch_qty(double batch_qty) {
        this.batch_qty = batch_qty;
    }

    public double getAvailable_qty() {
        return available_qty;
    }

    public void setAvailable_qty(double available_qty) {
        this.available_qty = available_qty;
    }

    public boolean getIs_sold() {
        return is_sold;
    }

    public void setIs_sold(boolean is_sold) {
        this.is_sold = is_sold;
    }
    
    
    
}
