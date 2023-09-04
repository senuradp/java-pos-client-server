/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.service.dao;

import java.util.List;

/**
 *
 * @author senu2k
 */
public interface IServiceDAO <T>{
    
    boolean add(T t);
    
    boolean update(T t);
        
    boolean delete(String id) throws Exception;
    
    List<T> getAll() throws Exception;
    
}
