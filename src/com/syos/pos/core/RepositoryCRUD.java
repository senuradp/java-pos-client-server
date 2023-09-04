/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.core;

import com.syos.pos.config.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author senu2k
 */
public class RepositoryCRUD {

    private static PreparedStatement getPreparedStatement(String sql, Object... data) throws Exception {
        Connection connection = DBConnection.getInstance().getConnectionFromPool();
        PreparedStatement pstm = connection.prepareStatement(sql);
        for (int i = 0; i < data.length; i++) {
            pstm.setObject(i + 1, data[i]);
        }
        return pstm;
    }

    public static boolean executeUpdate(String sql, Object... data) throws Exception {
        return getPreparedStatement(sql, data).executeUpdate() > 0;
    }

    public static ResultSet executeQuery(String sql, Object... data) throws Exception {
        return getPreparedStatement(sql, data).executeQuery();
    }
}