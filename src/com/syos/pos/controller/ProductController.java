/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.controller;

import com.syos.pos.core.ServiceFactory;
import com.syos.pos.dto.ProductDTO;
import com.syos.pos.service.dao.IProductService;
import java.util.List;

/**
 *
 * @author senu2k
 */
public class ProductController {
    
    private final IProductService productService = (IProductService)ServiceFactory.getInstance().getDAO(ServiceFactory.ServiceType.PRODUCT);

    public boolean addItem(ProductDTO productDTO){
        return productService.add(productDTO);
    }
    
    public  boolean updateItem(ProductDTO productDTO){
        return productService.update(productDTO);
    }
    
    public  boolean deleteItem(String code) throws Exception{
       return productService.delete(code);
    }

    public  List<ProductDTO> getAll() throws Exception{
        return productService.getAll();
    }
    
    public  ProductDTO getProductByCode(String code)throws Exception{
        return productService.getProductByCode(code);
    }    

    public  boolean checkProductCodeExists(String code) {
        return productService.checkProductCodeExists(code);
    }
    
}
