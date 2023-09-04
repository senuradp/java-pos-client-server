/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.repository.dao;

import com.syos.pos.dto.BatchDTO;
import com.syos.pos.entity.Batch;
import com.syos.pos.entity.Product;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author senu2k
 */
public interface IBatchRepository extends IRepositoryDAO<Batch>{
    
    // update batch qty by product code
    // boolean updateBatchQty(String product_code, double qty) throws Exception;

    // check batch code exists in database
    boolean checkBatchCodeExists(String batch_code) throws Exception;

    // get batch details
    Batch getBatchDetails(String batch_code) throws Exception;
    
    List<Batch> getExpiringBatchDetails(String product_code) throws Exception ;

}
