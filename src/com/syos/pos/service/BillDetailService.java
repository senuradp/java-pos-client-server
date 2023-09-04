/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.service;

import com.syos.pos.core.RepositoryFactory;
import com.syos.pos.dto.BillDetailDTO;
import com.syos.pos.entity.BillDetail;
import com.syos.pos.repository.BillDetailRepository;
import com.syos.pos.repository.dao.IBillDetailRepository;
import com.syos.pos.service.dao.IBillDetailService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author senu2k
 */
public class BillDetailService implements IBillDetailService{
    
    private static final IBillDetailRepository billDetailRepositoryDAO = (IBillDetailRepository) RepositoryFactory.getInstance().getDAO(RepositoryFactory.RepositoryType.BILL_DETAIL);

    @Override
    public boolean add(BillDetailDTO billDetailDTO) {
        try{
            BillDetail billDetail = new BillDetail();
            billDetail.setBill_serial_number(billDetailDTO.getBill_serial_number());
            billDetail.setProduct_code(billDetailDTO.getProduct_code());
            billDetail.setItem_name(billDetailDTO.getItem_name());
            billDetail.setItem_qty(billDetailDTO.getItem_qty());
            billDetail.setItem_price(billDetailDTO.getItem_price());
            billDetail.setTotal_item_price(billDetailDTO.getTotal_item_price());

           return billDetailRepositoryDAO.add(billDetail);
            
        }catch (Exception ex) {
             Logger.getLogger(BillDetailRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(BillDetailDTO billDetailDTO) {
        try{
            BillDetail billDetail = new BillDetail();
            billDetail.setBill_serial_number(billDetailDTO.getBill_serial_number());
            billDetail.setProduct_code(billDetailDTO.getProduct_code());
            billDetail.setItem_name(billDetailDTO.getItem_name());
            billDetail.setItem_qty(billDetailDTO.getItem_qty());
            billDetail.setItem_price(billDetailDTO.getItem_price());
            billDetail.setTotal_item_price(billDetailDTO.getTotal_item_price());

           return billDetailRepositoryDAO.update(billDetail);
            
        }catch (Exception ex) {
             Logger.getLogger(BillDetailRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(String bill_serial_number) throws Exception {
        try{
            return billDetailRepositoryDAO.delete(bill_serial_number);
        }catch (Exception ex) {
             Logger.getLogger(BillDetailRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<BillDetailDTO> getAll() throws Exception {
        try{
            List<BillDetail> allBillDetails = billDetailRepositoryDAO.getAll();
            List<BillDetailDTO> allBillDetailDTOs = new ArrayList<>();
            
            for (BillDetail billDetail : allBillDetails) {
                
                BillDetailDTO billDetailDTO = new BillDetailDTO();
                billDetailDTO.setBill_serial_number(billDetail.getBill_serial_number());
                billDetailDTO.setProduct_code(billDetail.getProduct_code());
                billDetailDTO.setItem_name(billDetail.getItem_name());
                billDetailDTO.setItem_qty(billDetail.getItem_qty());
                billDetailDTO.setItem_price(billDetail.getItem_price());
                billDetailDTO.setTotal_item_price(billDetail.getTotal_item_price());
                

                allBillDetailDTOs.add(billDetailDTO);

            }
            return allBillDetailDTOs;
        }catch (Exception ex) {
             Logger.getLogger(BillDetailRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
}
