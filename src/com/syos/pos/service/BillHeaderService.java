/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.service;

import com.syos.pos.core.RepositoryFactory;
import com.syos.pos.dto.BillHeaderDTO;
import com.syos.pos.entity.BillHeader;
import com.syos.pos.repository.BillHeaderRepository;
import com.syos.pos.repository.dao.IBillHeaderRepository;
import com.syos.pos.service.dao.IBillHeaderService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author senu2k
 */
public class BillHeaderService implements IBillHeaderService{
    
    private static final IBillHeaderRepository billHeaderRepositoryDAO = (IBillHeaderRepository) RepositoryFactory.getInstance().getDAO(RepositoryFactory.RepositoryType.BILL_HEADER);

    @Override
    public boolean add(BillHeaderDTO billHeaderDTO) {
        try{
            BillHeader billHeader = new BillHeader();
            billHeader.setBill_serial_number(billHeaderDTO.getBill_serial_number());
            billHeader.setPayment_type(billHeaderDTO.getPayment_type());
            billHeader.setDate(billHeaderDTO.getDate());
            billHeader.setTotal_bill_price(billHeaderDTO.getTotal_bill_price());
            billHeader.setAmount_tendered(billHeaderDTO.getAmount_tendered());
            billHeader.setDiscount(billHeaderDTO.getDiscount());
            billHeader.setChange(billHeaderDTO.getChange());
            

           return billHeaderRepositoryDAO.add(billHeader);
            
        }catch (Exception ex) {
             Logger.getLogger(BillHeaderRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(BillHeaderDTO billHeaderDTO) {
        try{
            BillHeader billHeader = new BillHeader();
            billHeader.setBill_serial_number(billHeaderDTO.getBill_serial_number());
            billHeader.setPayment_type(billHeaderDTO.getPayment_type());
            billHeader.setDate(billHeaderDTO.getDate());
            billHeader.setTotal_bill_price(billHeaderDTO.getTotal_bill_price());
            billHeader.setAmount_tendered(billHeaderDTO.getAmount_tendered());
            billHeader.setDiscount(billHeaderDTO.getDiscount());
            billHeader.setChange(billHeaderDTO.getChange());
            

           return billHeaderRepositoryDAO.update(billHeader);
            
        }catch (Exception ex) {
             Logger.getLogger(BillHeaderRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(String bill_serialNumber) throws Exception {
     
         try{
            return billHeaderRepositoryDAO.delete(bill_serialNumber);
        }catch (Exception ex) {
             Logger.getLogger(BillHeaderRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
        
    }

    @Override
    public List<BillHeaderDTO> getAll() throws Exception {
        try{
            List<BillHeader> allBillHeaders = billHeaderRepositoryDAO.getAll();
            List<BillHeaderDTO> allBillHeaderDTOs = new ArrayList<>();
            
            for (BillHeader billHeader : allBillHeaders) {
                
                BillHeaderDTO billHeaderDTO = new BillHeaderDTO();
                billHeaderDTO.setBill_serial_number(billHeader.getBill_serial_number());
                billHeaderDTO.setPayment_type(billHeader.getPayment_type());
                billHeaderDTO.setDate(billHeader.getDate());
                billHeaderDTO.setTotal_bill_price(billHeader.getTotal_bill_price());
                billHeaderDTO.setAmount_tendered(billHeader.getAmount_tendered());
                billHeaderDTO.setDiscount(billHeader.getDiscount());
                billHeaderDTO.setChange(billHeader.getChange());
                

                allBillHeaderDTOs.add(billHeaderDTO);

            }
            return allBillHeaderDTOs;
        }catch (Exception ex) {
             Logger.getLogger(BillHeaderRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
}
