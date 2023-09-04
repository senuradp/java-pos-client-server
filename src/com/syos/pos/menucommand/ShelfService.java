/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.menucommand;

import com.syos.pos.controller.ProductController;
import com.syos.pos.controller.ShelfController;
import com.syos.pos.dto.ShelfDTO;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author senu2k
 */
public class ShelfService {
    
     // Define a logger for the ShelfService class
    private static final Logger LOGGER = Logger.getLogger(ShelfService.class.getName());

    public String add(String shelfCode, String productCode, double capacity, double availableQty) {
        ShelfController shelfController = new ShelfController();
        ShelfDTO shelfDTO = new ShelfDTO();

        try {
            // Check if the shelf code already exists
            boolean shelfCodeExists = shelfController.checkShelfCodeExists(shelfCode);

            if (shelfCodeExists) {
                // Log a warning if the shelf code already exists
                LOGGER.warning("Shelf code already exists: " + shelfCode);
                return "Shelf code already exists!";
            } else {
                if (availableQty <= capacity) {
                    shelfDTO.setShelf_code(shelfCode);
                    shelfDTO.setProduct_code(productCode);
                    shelfDTO.setCapacity(capacity);
                    shelfDTO.setAvailable_qty(availableQty);

                    shelfController.addItem(shelfDTO);
                    LOGGER.info("Shelf added successfully: " + shelfCode);
                    return "Shelf added successfully!";
                } else {
                    // Log an error if available quantity exceeds capacity
                    LOGGER.severe("Available quantity exceeds capacity for shelf: " + shelfCode);
                    return "Available quantity exceeds capacity. Please enter a valid quantity.";
                }
            }
        } catch (Exception e) {
            // Log the exception and error message
            LOGGER.log(Level.SEVERE, "Failed to add shelf: " + e.getMessage(), e);
            return "Failed to add shelf.";
        }
    }

    public String update(String shelfCode, String productCode, double capacity, double availableQty) {
        ShelfController shelfController = new ShelfController();
        ShelfDTO shelfDTO = new ShelfDTO();

        try {
            // Check if the shelf code already exists
            boolean shelfCodeExists = shelfController.checkShelfCodeExists(shelfCode);

            if (shelfCodeExists) {
                if (availableQty <= capacity) {
                    shelfDTO.setShelf_code(shelfCode);
                    shelfDTO.setProduct_code(productCode);
                    shelfDTO.setCapacity(capacity);
                    shelfDTO.setAvailable_qty(availableQty);

                    shelfController.updateItem(shelfDTO);
                    LOGGER.info("Shelf added successfully: " + shelfCode);
                    return "Shelf added successfully!";
                } else {
                    // Log an error if available quantity exceeds capacity
                    LOGGER.severe("Available quantity exceeds capacity for shelf: " + shelfCode);
                    return "Available quantity exceeds capacity. Please enter a valid quantity.";
                }
            } else {
                LOGGER.warning("Shelf code does not exist: " + shelfCode);
                return "Shelf code does not exist!";
            }
        } catch (Exception e) {
            // Log the exception and error message
            LOGGER.log(Level.SEVERE, "Failed to add shelf: " + e.getMessage(), e);
            return "Failed to add shelf.";
        }
    }

    
    public String delete(String shelfCode) {
        try {
            ShelfController shelfController = new ShelfController();
            boolean shelfCodeExists = shelfController.checkShelfCodeExists(shelfCode);
            
            if (shelfCodeExists) {
                try {
                    shelfController.deleteItem(shelfCode);
                    return "Shelf deleted successfully!";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "Failed to delete shelf.";
                }
            } else {
                return "Shelf not found.";
            }
        } catch (Exception ex) {
            Logger.getLogger(ShelfService.class.getName()).log(Level.SEVERE, null, ex);
            return "Failed to delete shelf.";
        }
    }

    void getAll() {
        try {
            ShelfController shelfController = new ShelfController();
            List<ShelfDTO> shelves = shelfController.getAll();
            
            if (!shelves.isEmpty()) {
                System.out.println("All Shelves:");
                for (ShelfDTO shelf : shelves) {
                    System.out.println("Shelf Code: " + shelf.getShelf_code());
                    System.out.println("Product Code: " + shelf.getProduct_code());
                    System.out.println("Capacity: " + shelf.getCapacity());
                    System.out.println("Available Quantity: " + shelf.getAvailable_qty());
                    System.out.println("-----------------------");
                }
            } else {
                System.out.println("No shelves found.");
            }
        } catch (Exception ex) {
            Logger.getLogger(ShelfService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public String[] getByCode(String shelfCode) {
        ShelfController shelfController = new ShelfController();

        // Check if the shelf code exists
        boolean shelfCodeExists = false;
        try {
            shelfCodeExists = shelfController.checkShelfCodeExists(shelfCode);
        } catch (Exception ex) {
            Logger.getLogger(ShelfService.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (shelfCodeExists) {
            // If the shelf code exists, proceed to retrieve the shelf details
            ShelfDTO shelfDTO = null;
            try {
                shelfDTO = shelfController.getShelfDetails(shelfCode);
            } catch (Exception ex) {
                Logger.getLogger(ShelfService.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (shelfDTO != null) {
                String[] shelfDetails = new String[3];

                // Populate the array with shelf details
                shelfDetails[0] = shelfDTO.getShelf_code();
                shelfDetails[1] = String.valueOf(shelfDTO.getCapacity());
                shelfDetails[2] = String.valueOf(shelfDTO.getAvailable_qty());

                return shelfDetails;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
