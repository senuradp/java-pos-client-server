/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.controller;

import com.syos.pos.core.ServiceFactory;
import com.syos.pos.dto.BillHeaderDTO;
import com.syos.pos.service.dao.IBillHeaderService;
import java.util.List;

/**
 *
 * @author senu2k
 */
public class BillHeaderController {
    
    private static final IBillHeaderService billHeaderService = (IBillHeaderService)ServiceFactory.getInstance().getDAO(ServiceFactory.ServiceType.BILL_HEADER);
    
    public static boolean addItem(BillHeaderDTO billHeaderDTO){
        return billHeaderService.add(billHeaderDTO);
    }
    
    public static boolean updateItem(BillHeaderDTO billHeaderDTO){
        return billHeaderService.update(billHeaderDTO);
    }
    
    public static boolean deleteItem(String code) throws Exception{
       return billHeaderService.delete(code);
    }

    public static List<BillHeaderDTO> getAll() throws Exception{
        return billHeaderService.getAll();
    }
     
    
}
