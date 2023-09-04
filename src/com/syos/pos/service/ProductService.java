/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.service;

import com.syos.pos.core.RepositoryFactory;
import com.syos.pos.dto.ProductDTO;
import com.syos.pos.entity.Product;
import com.syos.pos.repository.ProductRepository;
import java.util.List;
import com.syos.pos.repository.dao.IProductRepository;
import com.syos.pos.service.dao.IProductService;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author senu2k
 */
public class ProductService implements IProductService{
    
    private IProductRepository productRepositoryDAO = (IProductRepository) RepositoryFactory.getInstance().getDAO(RepositoryFactory.RepositoryType.PRODUCT);

    @Override
    public boolean add(ProductDTO productDTO) {
        
        try{
            Product product = new Product();
            product.setProduct_code(productDTO.getProduct_code());
            product.setProduct_name(productDTO.getProduct_name());
            product.setProduct_price(productDTO.getProduct_price());
            
            return productRepositoryDAO.add(product);
            
        }catch (Exception ex) {
             Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    @Override
    public boolean update(ProductDTO productDTO) {
            
        try{
            Product product = new Product();
            product.setProduct_code(productDTO.getProduct_code());
            product.setProduct_name(productDTO.getProduct_name());
            product.setProduct_price(productDTO.getProduct_price());
            return productRepositoryDAO.update(product);
        }catch (Exception ex) {
             Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(String code) throws Exception {
        
        try{
            return productRepositoryDAO.delete(code);
        }catch (Exception ex) {
             Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }

    @Override
    public List<ProductDTO> getAll() throws Exception {
        
        try{
            List<Product> allProducts = productRepositoryDAO.getAll();
            List<ProductDTO> allProductDTOs = new ArrayList<>();
            for (Product product : allProducts) {
                ProductDTO productDTO = new ProductDTO();
                productDTO.setProduct_code(product.getProduct_code());
                productDTO.setProduct_name(product.getProduct_name());
                productDTO.setProduct_price(product.getProduct_price());

                allProductDTOs.add(productDTO);

            }
            return allProductDTOs;
        }catch (Exception ex) {
             Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
                
    }

    @Override
    public ProductDTO getProductByCode(String code) throws Exception {
        Product getProductByCode = productRepositoryDAO.getProductByCode(code);
        
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProduct_code(getProductByCode.getProduct_code());
        productDTO.setProduct_name(getProductByCode.getProduct_name());
        productDTO.setProduct_price(getProductByCode.getProduct_price());
        
        return productDTO;
    }

    @Override
    public boolean checkProductCodeExists(String product_code) {
        try {
            return productRepositoryDAO.checkProductCodeExists(product_code);
        } catch (Exception ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
