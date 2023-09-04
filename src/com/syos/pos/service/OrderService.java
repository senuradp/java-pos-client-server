/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.service;

import com.syos.pos.core.ServiceFactory;
import com.syos.pos.dto.BillDetailDTO;
import com.syos.pos.dto.BillHeaderDTO;
import com.syos.pos.dto.ProductDTO;
import com.syos.pos.entity.Product;
import com.syos.pos.repository.ShelfRepository;
import com.syos.pos.service.dao.IBillDetailService;
import com.syos.pos.service.dao.IBillHeaderService;
import com.syos.pos.service.dao.IProductService;
import com.syos.pos.service.dao.IShelfService;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author senu2k
 */
public class OrderService {
    
    private static final IProductService productService = (IProductService) ServiceFactory.getInstance().getDAO(ServiceFactory.ServiceType.PRODUCT);
    private static final IShelfService shelfService = (IShelfService) ServiceFactory.getInstance().getDAO(ServiceFactory.ServiceType.SHELF);
    private static final IBillHeaderService billHeaderService = (IBillHeaderService) ServiceFactory.getInstance().getDAO(ServiceFactory.ServiceType.BILL_HEADER);
    private static final IBillDetailService billDetailService = (IBillDetailService) ServiceFactory.getInstance().getDAO(ServiceFactory.ServiceType.BILL_DETAIL);

    private static OrderService orderServiceInstance;
    private BillHeaderDTO billHeaderDTO;
    private BillDetailDTO billDetailDTO;

    
    private OrderService() {
    }

    public static OrderService getInstance() {
        if (orderServiceInstance == null) {
            orderServiceInstance = new OrderService();
        }
        return orderServiceInstance;
    }

    public String createOrder() {

        Date currentDate = new Date();

        billHeaderDTO = new BillHeaderDTO();
        billHeaderDTO.setBill_serial_number(generateSerialNumber());
        billHeaderDTO.setDate(currentDate);

        return billHeaderDTO.getBill_serial_number();
    }

    public double addOrderProduct(String product_code, double qty) throws Exception {
        double total_price = 0;

        // productService.get // product by code (this gives the name and price and
        // thers and pass to th blow function)
        // productService.getProductByCode(product_code);

        ProductDTO product = productService.getProductByCode(product_code);
        
        billHeaderDTO.addProduct(product_code, product.getProduct_name(), qty, product.getProduct_price());

        return billHeaderDTO.getTotal_bill_price();
    }

    public double addDiscount(double discount_amount) {
        double total_price = 0;

        billHeaderDTO.setDiscount(discount_amount);

        return billHeaderDTO.getTotal_bill_price();
    }
//    amount_tendered > billHeaderDTO.getTotal_bill_price()
 
    public double checkoutPay(double amount_tendered, String payment_type) throws Exception {

        // pass the payment type to bill header
        billHeaderDTO.setPayment_type(payment_type);
        
        if (amount_tendered < billHeaderDTO.getTotal_bill_price()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("The amount tendered is lower than the total bill price which is " + billHeaderDTO.getTotal_bill_price());
            System.out.println("Please enter a sufficient amount: ");
            amount_tendered = scanner.nextDouble();
//            scanner.nextLine(); // Consume newline character

            // Recalculate balance
            return checkoutPay(amount_tendered, payment_type);
        }

        double balance = calculateBalancePay(amount_tendered);
        
        billHeaderDTO.setAmount_tendered(amount_tendered);
        billHeaderDTO.setChange(balance);

        billHeaderService.add(billHeaderDTO);
        
        List<BillDetailDTO> billDetail = billHeaderDTO.getTypeOfBillDetails();

        for (int i = 0; i < billDetail.size(); i++) {
            
            BillDetailDTO billDetailDTO = billDetail.get(i);

            //save
            billDetailService.add(billDetailDTO);

            //update
            String product_code = billDetailDTO.getProduct_code();
            double qty = billDetailDTO.getItem_qty();
            
            
            double availableShelfQty = getAvailableQty(product_code);

            qty = availableShelfQty - qty;

            updateShelf(product_code, qty);
        }


        return balance;
    }

    public double getAvailableQty(String product_code) throws Exception {

        return shelfService.getAvailableQty(product_code);

    }

    public void updateShelf(String product_code, double qty) throws Exception {

        shelfService.updateShelf(product_code, qty);

    }

    public String generateSerialNumber() {

        String prefix = "B00";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = dateFormat.format(new Date());

        String serial_number = prefix + timestamp;

        return serial_number;
    }

//    public double calculateTotalPrice(double price, double qty) {
//        double total_price = 0;
//
//        return 0;
//    }

    public double calculateBalancePay(double amount_tendered) {
        double balance = 0;
        
        balance = amount_tendered - billHeaderDTO.getTotal_bill_price();

        return balance;
    }

}
