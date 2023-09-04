/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.repository;

import com.syos.pos.core.RepositoryCRUD;
import com.syos.pos.entity.Batch;
import com.syos.pos.entity.Product;
import com.syos.pos.entity.Shelf;
import com.syos.pos.repository.dao.IShelfRepository;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author senu2k
 */
public class ShelfRepository implements IShelfRepository{

    @Override
    public boolean add(Shelf shelf) {
        try{
            return RepositoryCRUD.executeUpdate("INSERT INTO shelf VALUES(?,?,?,?)", shelf.getShelf_code(), shelf.getProduct_code(), shelf.getCapacity(), shelf.getAvailable_qty());
        }catch(Exception ex){
           Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Shelf shelf) {
        try{
            return RepositoryCRUD.executeUpdate("UPDATE shelf SET shelf_code=?, product_code=?, capacity=?, available_qty=? WHERE shelf_code=?" ,shelf.getShelf_code(), shelf.getProduct_code(), shelf.getCapacity(), shelf.getAvailable_qty(), shelf.getShelf_code());
            
        }catch(Exception ex){
           Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(String code) throws Exception {
        try{
            return RepositoryCRUD.executeUpdate("DELETE FROM shelf WHERE shelf_code = ?", code);
        }catch(Exception ex){
           Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Shelf> getAll() throws Exception {
        
        ResultSet rst = RepositoryCRUD.executeQuery("SELECT * FROM shelf");
        List<Shelf> arrayList = new ArrayList<>();
        while (rst.next()) {
            Shelf shelfs = new Shelf();
            shelfs.setShelf_code(rst.getString(1));
            shelfs.setProduct_code(rst.getString(2));
            shelfs.setCapacity(rst.getDouble(3));
            shelfs.setAvailable_qty(rst.getDouble(4));
                        
            arrayList.add(shelfs);
        }
        
        return arrayList;
        
    }

    @Override
    public boolean updateShelf(String product_code, double qty) throws Exception {
        try{
            
            return RepositoryCRUD.executeUpdate("UPDATE shelf SET available_qty =? WHERE product_code =?", qty, product_code);
            
        }catch(Exception ex){
           Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public double getAvailableQty(String product_code) throws Exception {
        try{
            ResultSet rst = RepositoryCRUD.executeQuery("SELECT * FROM shelf WHERE product_code = ?", product_code);
            // what is rst.next() 
            // rst.next() is a boolean value
            // if there is a value in the result set then rst.next() will return true else false

            if (rst.next()) {
                return rst.getDouble(4);
            } else {
                return 0;
            }
        }catch(Exception ex){
           Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public String getBatchCode(String product_code) throws Exception {
        try{
            ResultSet rst = RepositoryCRUD.executeQuery("SELECT * FROM batch WHERE product_code = ?", product_code);
            // get the batch code that is in shelf
            if (rst.next()) {
                return rst.getString(1);
            } else {
                return null;
            }
        }catch(Exception ex){
           Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean checkExpiryDate(String product_code, String batch_code) throws Exception {
        try{
            ResultSet rst = RepositoryCRUD.executeQuery("SELECT * FROM batch WHERE batch_code = ? AND product_code = ?", batch_code, product_code);
            if (rst.next()) {
                // check if expiry date is closer to todays date
                Date expiry_date = rst.getDate(3);
                Date today = new Date();
                if (expiry_date.compareTo(today) > 0) { 
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }catch(Exception ex){
           Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean checkShelfCodeExists(String shelf_code) throws Exception {

        ResultSet rst = RepositoryCRUD.executeQuery("SELECT * FROM shelf WHERE shelf_code = ?", shelf_code);
        if (rst.next()) {
            return true;
        }
        return false;
    }

    @Override
    public double getCapacity(String shelf_code) {
        try{
            ResultSet rst = RepositoryCRUD.executeQuery("SELECT * FROM shelf WHERE shelf_code = ?", shelf_code);
            if (rst.next()) {
                return rst.getDouble(3);
            } else {
                return 0;
            }
        }catch(Exception ex){
           Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public Shelf getShelfDetails(String shelf_code) throws Exception {
        // get shelf details
        ResultSet rst = RepositoryCRUD.executeQuery("SELECT * FROM shelf WHERE shelf_code = ?", shelf_code);
        if (rst.next()) {
            Shelf shelf = new Shelf();
            shelf.setShelf_code(rst.getString(1));
            shelf.setProduct_code(rst.getString(2));
            shelf.setCapacity(rst.getDouble(3));
            shelf.setAvailable_qty(rst.getDouble(4));

            return shelf;
        }
        return null;
    }

    
    
}
