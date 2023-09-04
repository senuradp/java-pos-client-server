/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.core;

import com.syos.pos.repository.BatchRepository;
import com.syos.pos.repository.BillDetailRepository;
import com.syos.pos.repository.BillHeaderRepository;
import com.syos.pos.repository.ProductRepository;
import com.syos.pos.repository.ShelfRepository;
import com.syos.pos.repository.dao.IRepositoryDAO;

/**
 *
 * @author senu2k
 */
public class RepositoryFactory {
    
    private static RepositoryFactory repositoryFactory;
    
    private final ProductRepository itemRepository;
    private final BatchRepository batchRepository;
    private final ShelfRepository shelfRepository;
    private final BillHeaderRepository billHeaderRepository;
    private final BillDetailRepository billDetailRepository;

    public enum RepositoryType {
        PRODUCT, BATCH, SHELF, BILL_HEADER, BILL_DETAIL;
    }
    
    public RepositoryFactory() {
        itemRepository = new ProductRepository();
        batchRepository = new BatchRepository();
        shelfRepository = new ShelfRepository();
        billHeaderRepository = new BillHeaderRepository();
        billDetailRepository = new BillDetailRepository();
    }
    
    public static RepositoryFactory getInstance(){
        
        if (repositoryFactory == null) {
            repositoryFactory = new RepositoryFactory();
        }
        
        return repositoryFactory;
    }
    
    
    public IRepositoryDAO getDAO(RepositoryType repositoryType) {
        switch (repositoryType) {
            case PRODUCT:
                return itemRepository;
            case BATCH:
                return batchRepository;
            case SHELF:
                return shelfRepository;
            case BILL_HEADER:
                return billHeaderRepository;
            case BILL_DETAIL:
                return billDetailRepository;
            default:
                return null;
        }
    }
    
}
