/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.repository;

import com.syos.pos.core.RepositoryCRUD;
import com.syos.pos.entity.BillHeader;
import com.syos.pos.repository.dao.IBillHeaderRepository;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author senu2k
 */
public class BillHeaderRepository implements IBillHeaderRepository{

    @Override
    public boolean add(BillHeader billHeader) {
        
        try {
            return RepositoryCRUD.executeUpdate("INSERT INTO bill_header VALUES(?,?,?,?,?,?,?)", billHeader.getBill_serial_number(),billHeader.getPayment_type(),billHeader.getDate(),billHeader.getTotal_bill_price(),billHeader.getAmount_tendered(),billHeader.getDiscount(),billHeader.getChange());
        } catch (Exception ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }

    @Override
    public boolean update(BillHeader billHeader) {
        try {
            return RepositoryCRUD.executeUpdate("UPDATE bill_header SET bill_serial_number=?, payment_type=?, date=?, total_bill_price=?, amount_tendered=?, discount=?, balance=? WHERE bill_serial_number=?", billHeader.getBill_serial_number(),billHeader.getPayment_type(),billHeader.getDate(),billHeader.getTotal_bill_price(),billHeader.getAmount_tendered(),billHeader.getDiscount(),billHeader.getChange(), billHeader.getBill_serial_number());
        } catch (Exception ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(String bill_serial_number) throws Exception {
        try{
            return RepositoryCRUD.executeUpdate("DELETE FROM bill_header WHERE bill_serial_number = ?", bill_serial_number);
        } catch (Exception ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<BillHeader> getAll() throws Exception {
        ResultSet rst = RepositoryCRUD.executeQuery("SELECT * FROM bill_header");
        List<BillHeader> arrayList = new ArrayList<>();
        while (rst.next()) {
            BillHeader billHeaders = new BillHeader();
                billHeaders.setBill_serial_number(rst.getString(1));
                billHeaders.setPayment_type(rst.getString(2));
                billHeaders.setDate(rst.getDate(3));
                billHeaders.setTotal_bill_price(rst.getDouble(4));
                billHeaders.setAmount_tendered(rst.getDouble(5));
                billHeaders.setDiscount(rst.getDouble(6));
                billHeaders.setChange(rst.getDouble(7));
            
            arrayList.add(billHeaders);
        }
        
        return arrayList;
    }
    
}
