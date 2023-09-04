/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.service.dao;

import com.syos.pos.dto.BatchDTO;
import com.syos.pos.entity.Batch;
import java.util.List;

/**
 *
 * @author senu2k
 */
public interface IBatchService extends IServiceDAO<BatchDTO>{

    // check batch code in database
    boolean checkBatchCodeExists(String batch_code) throws Exception;

    // get batch details
    BatchDTO getBatchDetails(String batch_code) throws Exception;
    
    // update batch qty by product code
    // boolean updateBatchQty(String product_code, double qty) throws Exception;
    
    public List<BatchDTO> getExpiringBatchDetails(String product_code);

}
