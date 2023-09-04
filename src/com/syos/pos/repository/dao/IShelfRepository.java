/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.repository.dao;

import com.syos.pos.entity.Shelf;

/**
 *
 * @author senu2k
 */
public interface IShelfRepository extends IRepositoryDAO<Shelf>{

    // update stock based on parameters product code and qty
    boolean updateShelf(String product_code, double qty) throws Exception;

    // get available qty based on product code
    double getAvailableQty(String product_code) throws Exception;

    // check the expiry date of the batch to be stocked next
    boolean checkExpiryDate(String product_code, String batch_code) throws Exception;

    // get the batch code of the product
    String getBatchCode(String product_code) throws Exception;
    
    boolean checkShelfCodeExists(String shelf_code) throws Exception;

    // get capacity of the shelf
    public double getCapacity(String shelf_code);
    
    // get shelf details
    Shelf getShelfDetails(String shelf_code) throws Exception;
    
    
}
