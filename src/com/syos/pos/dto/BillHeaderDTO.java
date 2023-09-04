/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author senu2k
 */
public class BillHeaderDTO {
    
    private String bill_serial_number;
    private String payment_type;
    private Date date;
    private double total_bill_price;
    private double amount_tendered;
    private double discount;
    private double change;
    
    private List<BillDetailDTO> typeOfBillDetails;
    
    
    public BillHeaderDTO() {
        typeOfBillDetails = new ArrayList<BillDetailDTO>();
    } 
    
    public void addProduct(String product_code, String product_name, double qty, double price){
        BillDetailDTO product = new BillDetailDTO(this.bill_serial_number, product_code, product_name, qty, price);
        typeOfBillDetails.add(product);
        calculateTotalPrice();
    }
    
    public void calculateTotalPrice(){
        double sum = 0;
        
        for(int i=0; i<typeOfBillDetails.size(); i++){
            sum = sum + typeOfBillDetails.get(i).getTotal_item_price();
        }
        
        double discountAmount = sum * (this.discount / 100);
        this.total_bill_price = sum - discountAmount;
    
    }

    public String getBill_serial_number() {
        return bill_serial_number;
    }

    public void setBill_serial_number(String bill_serial_number) {
        this.bill_serial_number = bill_serial_number;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal_bill_price() {
        return total_bill_price;
    }

    public void setTotal_bill_price(double total_bill_price) {
        this.total_bill_price = total_bill_price;
    }

    public double getAmount_tendered() {
        return amount_tendered;
    }

    public void setAmount_tendered(double amount_tendered) {
        this.amount_tendered = amount_tendered;
    }

    public double getDiscount() {
        return discount;
    }

    // when we get the discount again calculate the toatal price and then go to total price and update the discounted value
    public void setDiscount(double discount) {
        this.discount = discount;
        calculateTotalPrice();
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public List<BillDetailDTO> getTypeOfBillDetails() {
        return typeOfBillDetails;
    }

    public void setTypeOfBillDetails(List<BillDetailDTO> typeOfBillDetails) {
        this.typeOfBillDetails = typeOfBillDetails;
    }
    
}
