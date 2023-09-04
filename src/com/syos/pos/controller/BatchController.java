/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.controller;


import com.syos.pos.core.ServiceFactory;
import com.syos.pos.dto.BatchDTO;
import com.syos.pos.service.dao.IBatchService;
import java.util.List;

/**
 *
 * @author senu2k
 */
public class BatchController {
 
    private static final IBatchService batchService = (IBatchService)ServiceFactory.getInstance().getDAO(ServiceFactory.ServiceType.BATCH);

    public  boolean addBatch(BatchDTO batchDTO){
        return batchService.add(batchDTO);
    }
    
    public  boolean updateBatch(BatchDTO batchDTO){
        return batchService.update(batchDTO);
    }
    
    public  boolean deleteBatch(String code) throws Exception{
       return batchService.delete(code);
    }

    public  List<BatchDTO> getAll() throws Exception{
        return batchService.getAll();
    }

    // check batch code exists
    public  boolean checkBatchCodeExists(String batch_code) throws Exception{
        return batchService.checkBatchCodeExists(batch_code);
    }

    // get batch details
    public  BatchDTO getBatchDetails(String batch_code) throws Exception{
        return batchService.getBatchDetails(batch_code);
    }
    
    // udpate batch qty
    // public static boolean updateBatchQty(String product_code, double qty) throws Exception{
    //     return batchService.updateBatchQty(product_code, qty);
    // }

    public List<BatchDTO> getExpiringBatchDetails(String product_code){
        return batchService.getExpiringBatchDetails(product_code);
    }

}
