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
public class Product {
    
    private String product_code;
    private String product_name;
    private double product_price;

    public Product() {
    }
    

    public Product(String product_code, String product_name, double product_price) {
        this.product_code = product_code;
        this.product_name = product_name;
        this.product_price = product_price;
    }

    @Override
    public String toString() {
        return "Product{" + "Product code=" + product_code + ", Product name=" + product_name + ", Product price=" + product_price +  '}';
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    
    
}
