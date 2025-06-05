/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.oracle.wls.shaded.org.apache.bcel.classfile.Constant;
import dto.Stock;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NHH
 */
public class StockDAO {
    
    final String GET_STOCKS="";
    final String UPDATE_STOCK="";
    final String DELETE_STOCK="";
    
    public ArrayList<Stock> getStockList(){
        ArrayList<Stock> stocks = new ArrayList<>();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        String sql = GET_STOCKS;
        return stocks;
    }
}
