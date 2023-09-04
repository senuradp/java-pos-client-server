/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syos.pos.report;

/**
 *
 * @author senu2k
 */
public class FacadeReport {
    
    private IReport billReport;
    private IReport shelfReport;
    private IReport salesReport;
    private IReport stockReport;

    public FacadeReport() {
        billReport = new BillReport();
        shelfReport = new ShelfReport();
        salesReport = new SalesReport();
        stockReport = new StockReport();
    }

    public IReport getBillReport() {
        return billReport;
    }

    public IReport getShelfReport() {
        return shelfReport;
    }

    public IReport getSalesReport() {
        return salesReport;
    }

    public IReport getStockReport() {
        return stockReport;
    }

}
