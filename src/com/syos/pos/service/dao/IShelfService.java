/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.service.dao;

import com.syos.pos.dto.ShelfDTO;
import com.syos.pos.entity.Batch;
import java.util.List;

/**
 *
 * @author senu2k
 */
public interface IShelfService extends IServiceDAO<ShelfDTO>{

    public boolean updateShelf(String product_code, double qty);

    public double getAvailableQty(String product_code);

    public String getBatchCode(String product_code);
    
    public boolean checkExpiryDate(String product_code, String batch_code);

    public boolean reStockShelf(String product_code, double qty);
    
    boolean checkShelfCodeExists(String shelf_code) throws Exception;

    // get  capacity of shelf
    public double getShelfCapacity(String shelf_code);

    // get shelf details
    ShelfDTO getShelfDetails(String shelf_code) throws Exception;

}
