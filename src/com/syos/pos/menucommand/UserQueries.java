/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.menucommand;

/**
 *
 * @author senu2k
 */
public interface UserQueries {
    boolean registerUser(String username, String password, String role);
    boolean loginUser(String username, String password);
    String getUserRole(String username);
    boolean checkUsernameExist(String username);
}
