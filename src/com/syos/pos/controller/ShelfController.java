/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.controller;

import java.util.List;

import com.syos.pos.core.ServiceFactory;
import com.syos.pos.dto.ShelfDTO;
import com.syos.pos.service.dao.IShelfService;

/**
 *
 * @author senu2k
 */
public class ShelfController {
    
    private static final IShelfService shelfService = (IShelfService)ServiceFactory.getInstance().getDAO(ServiceFactory.ServiceType.SHELF);

    public static boolean addItem(ShelfDTO shelfDTO){
        return shelfService.add(shelfDTO);
    }
    
    public static boolean updateItem(ShelfDTO shelfDTO){
        return shelfService.update(shelfDTO);
    }
    
    public static boolean deleteItem(String code) throws Exception{
       return shelfService.delete(code);
    }

    public static List<ShelfDTO> getAll() throws Exception{
        return shelfService.getAll();
    }
    
    // restock
    public boolean reStockShelf(String product_code, double restock_qty) throws Exception{
        return shelfService.reStockShelf(product_code, restock_qty);
    }
    
    public  boolean checkShelfCodeExists(String shelf_code) throws Exception{
        return shelfService.checkShelfCodeExists(shelf_code);
    }

    // get shelf capacity
    public double getShelfCapacity(String shelf_code) throws Exception{
        return shelfService.getShelfCapacity(shelf_code);
    }
    
    // get batch details
    public  ShelfDTO getShelfDetails(String shelf_code) throws Exception{
        return shelfService.getShelfDetails(shelf_code);
    }
    
}
