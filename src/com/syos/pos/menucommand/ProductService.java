/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.menucommand;

import com.syos.pos.controller.ProductController;
import com.syos.pos.dto.ProductDTO;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author senu2k
 */
public class ProductService {
    public String add(String productCode, String productName, double productPrice) {
        ProductController productController = new ProductController();
        ProductDTO productDTO = new ProductDTO();

        // Check if the product code already exists
        boolean productCodeExists = productController.checkProductCodeExists(productCode);

        try {
            if (productCodeExists) {
                return "Product code already exists!";
            } else {
                productDTO.setProduct_code(productCode);
                productDTO.setProduct_name(productName);
                productDTO.setProduct_price(productPrice);

                productController.addItem(productDTO);
                return "Product added successfully!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to add product.";
        }
    }

    public String update(String productCode, String productName, double productPrice) {
       ProductController productController = new ProductController();
        ProductDTO productDTO = new ProductDTO();

        // Check if the product code already exists
        boolean productCodeExists = productController.checkProductCodeExists(productCode);

        try {
            if (productCodeExists) {
                productDTO.setProduct_code(productCode);
                productDTO.setProduct_name(productName);
                productDTO.setProduct_price(productPrice);

                productController.updateItem(productDTO);
                return "Product updated successfully!";
            } else {
                return "Product code does not exist!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to updated product.";
        }
    }

    public String delete(String productCode) {
        try {
            ProductController productController = new ProductController();
            boolean productCodeExists = productController.checkProductCodeExists(productCode);
            
            if (productCodeExists) {
                try {
                    productController.deleteItem(productCode);
                    return "Product deleted successfully!";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "Failed to delete product.";
                }
            } else {
                return "Product not found.";
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
            return "Failed to delete product.";
        }
    }


    void getAll() {
        ProductController productController = new ProductController();
        
        try {
            List<ProductDTO> products = productController.getAll();
            
            if (products.isEmpty()) {
                System.out.println("No products found!");
                return;
            }
            
            for (ProductDTO product : products) {
                System.out.println("Product code: " + product.getProduct_code());
                System.out.println("Product name: " + product.getProduct_name());
                System.out.println("Product price: " + product.getProduct_price());
                System.out.println("--------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String[] getByCode(String productCode) {
        ProductController productController = new ProductController();

        // Check if the product code exists
        boolean productCodeExists = productController.checkProductCodeExists(productCode);

        if (productCodeExists) {
            // If the product code exists, proceed to retrieve the product details
            ProductDTO productDTO = null;
            try {
                productDTO = productController.getProductByCode(productCode);
            } catch (Exception ex) {
                Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (productDTO != null) {
                // Convert the product details to an array of strings
                String[] productDetails = new String[2];
                productDetails[0] = productDTO.getProduct_name();
                productDetails[1] = String.valueOf(productDTO.getProduct_price());
                return productDetails;
            } else {
                return null; // Return a specific message
            }
        } else {

            return null;
        }
    }



}
