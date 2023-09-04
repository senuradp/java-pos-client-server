/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.controller;

import com.syos.pos.core.ServiceFactory;
import com.syos.pos.dto.BillDetailDTO;
import com.syos.pos.service.dao.IBillDetailService;
import java.util.List;

/**
 *
 * @author senu2k
 */
public class BillDetailController {
    
    private static final IBillDetailService billDetailService = (IBillDetailService)ServiceFactory.getInstance().getDAO(ServiceFactory.ServiceType.BILL_DETAIL);
    
    public static boolean addItem(BillDetailDTO billDetailDTO){
        return billDetailService.add(billDetailDTO);
    }
    
    public static boolean updateItem(BillDetailDTO billDetailDTO){
        return billDetailService.update(billDetailDTO);
    }
    
    public static boolean deleteItem(String code) throws Exception{
       return billDetailService.delete(code);
    }

    public static List<BillDetailDTO> getAll() throws Exception{
        return billDetailService.getAll();
    }
    
    
}
