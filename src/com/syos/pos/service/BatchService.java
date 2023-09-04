/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.service;

import com.syos.pos.core.RepositoryFactory;
import com.syos.pos.dto.BatchDTO;
import com.syos.pos.entity.Batch;
import com.syos.pos.repository.BatchRepository;
import com.syos.pos.repository.dao.IBatchRepository;
import com.syos.pos.service.dao.IBatchService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author senu2k
 */
public class BatchService implements IBatchService{
    
    private IBatchRepository batchRepositoryDAO = (IBatchRepository) RepositoryFactory.getInstance().getDAO(RepositoryFactory.RepositoryType.BATCH);

    @Override
    public boolean add(BatchDTO batchDTO) {
        try{
            Batch batch = new Batch();
            batch.setBatch_code(batchDTO.getBatch_code());
            batch.setPurchase_date(batchDTO.getPurchase_date());
            batch.setExpiry_date(batchDTO.getExpiry_date());
            batch.setProduct_code(batchDTO.getProduct_code());
            batch.setBatch_qty(batchDTO.getBatch_qty());
            batch.setAvailable_qty(batchDTO.getAvailable_qty());
            batch.setIs_sold(batchDTO.getIs_sold());
            
            return batchRepositoryDAO.add(batch);
            
        }catch (Exception ex) {
             Logger.getLogger(BatchRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(BatchDTO batchDTO) {
        
        try{
            Batch batch = new Batch();
            batch.setBatch_code(batchDTO.getBatch_code());
            batch.setPurchase_date(batchDTO.getPurchase_date());
            batch.setExpiry_date(batchDTO.getExpiry_date());
            batch.setProduct_code(batchDTO.getProduct_code());
            batch.setBatch_qty(batchDTO.getBatch_qty());
            batch.setAvailable_qty(batchDTO.getAvailable_qty());
            batch.setIs_sold(batchDTO.getIs_sold());
            
            return batchRepositoryDAO.update(batch);
            
        }catch (Exception ex) {
             Logger.getLogger(BatchRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
       
    }

    @Override
    public boolean delete(String code) throws Exception {
        
        try{
            
            return batchRepositoryDAO.delete(code);
            
        }catch (Exception ex) {
             Logger.getLogger(BatchRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }

    @Override
    public List<BatchDTO> getAll() throws Exception {
        
        try{
            List<Batch> allBatchs = batchRepositoryDAO.getAll();
            List<BatchDTO> allBatchDTOs = new ArrayList<>();
            for (Batch batch : allBatchs) {
                BatchDTO batchDTO = new BatchDTO();
                batchDTO.setBatch_code(batch.getBatch_code());
                batchDTO.setPurchase_date(batch.getPurchase_date());
                batchDTO.setExpiry_date(batch.getExpiry_date());
                batchDTO.setProduct_code(batch.getProduct_code());
                batchDTO.setBatch_qty(batch.getBatch_qty());
                batchDTO.setAvailable_qty(batch.getAvailable_qty());
                batchDTO.setIs_sold(batch.getIs_sold());

                allBatchDTOs.add(batchDTO);

            }
            return allBatchDTOs;
        }catch (Exception ex) {
             Logger.getLogger(BatchRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }

    @Override
    public boolean checkBatchCodeExists(String batch_code) throws Exception {    
        try{
            
            return batchRepositoryDAO.checkBatchCodeExists(batch_code);
            
        }catch (Exception ex) {
            Logger.getLogger(BatchRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public BatchDTO getBatchDetails(String batch_code) throws Exception {
        try{
            Batch batch = batchRepositoryDAO.getBatchDetails(batch_code);
            BatchDTO batchDTO = new BatchDTO();
            batchDTO.setBatch_code(batch.getBatch_code());
            batchDTO.setPurchase_date(batch.getPurchase_date());
            batchDTO.setExpiry_date(batch.getExpiry_date());
            batchDTO.setProduct_code(batch.getProduct_code());
            batchDTO.setBatch_qty(batch.getBatch_qty());
            batchDTO.setAvailable_qty(batch.getAvailable_qty());
            batchDTO.setIs_sold(batch.getIs_sold());
            
            return batchDTO;
        }catch (Exception ex) {
            Logger.getLogger(BatchRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

   

    // @Override
    // public boolean updateBatchQty(String product_code, double qty) throws Exception {
    //         try{
    //             return batchRepositoryDAO.updateBatchQty(product_code, qty);
    //         }catch (Exception ex) {
    //             Logger.getLogger(BatchRepository.class.getName()).log(Level.SEVERE, null, ex);
    //         }
    //         return false;
    // }

    @Override
    public List<BatchDTO> getExpiringBatchDetails(String product_code) {
        
        List<BatchDTO> resultDTO = new ArrayList<BatchDTO>();
        
        try{
            List<Batch> result = batchRepositoryDAO.getExpiringBatchDetails(product_code);
            
            for(int i=0; i<result.size(); i++){
                Batch batch = result.get(i);
                BatchDTO batchDTO = new BatchDTO();
                batchDTO.setBatch_code(batch.getBatch_code());
                batchDTO.setPurchase_date(batch.getPurchase_date());
                batchDTO.setExpiry_date(batch.getExpiry_date());
                batchDTO.setProduct_code(batch.getProduct_code());
                batchDTO.setBatch_qty(batch.getBatch_qty());
                batchDTO.setAvailable_qty(batch.getAvailable_qty());
                batchDTO.setIs_sold(batch.getIs_sold());
                
                resultDTO.add(batchDTO);
                
            }
            
        }catch(Exception ex){
            Logger.getLogger(BatchRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultDTO;
        
    }
    
}
