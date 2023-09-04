/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.repository;

import com.syos.pos.core.RepositoryCRUD;
import com.syos.pos.entity.Product;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.syos.pos.repository.dao.IProductRepository;

/**
 *
 * @author senu2k
 */
public class ProductRepository implements IProductRepository{

    @Override
    public boolean add(Product product) {
        
        try {
            return RepositoryCRUD.executeUpdate("INSERT INTO product VALUES(?,?,?)", product.getProduct_code(),product.getProduct_name(), product.getProduct_price());
        } catch (Exception ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }

    @Override
    public boolean update(Product product) {
        
        try {
            return RepositoryCRUD.executeUpdate("UPDATE product SET product_code=?,name=?,price=? WHERE product_code=?" ,product.getProduct_code(),product.getProduct_name(), product.getProduct_price(), product.getProduct_code());
        } catch (Exception ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }

    @Override
    public boolean delete(String code) throws Exception {
        try{
            return RepositoryCRUD.executeUpdate("DELETE FROM product WHERE product_code = ?", code);
        } catch (Exception ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Product> getAll() throws Exception {
        ResultSet rst = RepositoryCRUD.executeQuery("SELECT * FROM product");
        List<Product> arrayList = new ArrayList<>();
        while (rst.next()) {
            Product products = new Product();
            products.setProduct_code(rst.getString(1));
            products.setProduct_name(rst.getString(2));
            products.setProduct_price(rst.getDouble(3));
            
            arrayList.add(products);
        }
        
        return arrayList;
    }

    @Override
    public Product getProductByCode(String code) throws Exception {
        ResultSet rst = RepositoryCRUD.executeQuery("select * from product where product_code=?", code);
        Product product = new Product();
        if (rst.next()) {
            product = new Product(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3)
            );
        }
        return product;
    }

    @Override
    public boolean checkProductCodeExists(String product_code) throws Exception {
        ResultSet rst = RepositoryCRUD.executeQuery("select * from product where product_code=?", product_code);
        if (rst.next()) {
            return true;
        }
        return false;
    }
    
}
