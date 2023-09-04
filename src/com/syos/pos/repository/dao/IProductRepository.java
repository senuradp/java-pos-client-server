/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.repository.dao;

import com.syos.pos.entity.Product;

/**
 *
 * @author senu2k
 */
public interface IProductRepository extends IRepositoryDAO<Product>{
    
//    get product by code (product code)
        Product getProductByCode(String code) throws Exception;
 
        boolean checkProductCodeExists(String product_code) throws Exception;
        
}