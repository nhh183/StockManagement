/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Stock;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DBUtils;

/**
 *
 * @author NHH
 */
public class StockDAO {
    
    private final String GET_STOCKS = "SELECT ticker, name, sector, price, status FROM tblStocks";
    private final String GET_STOCK = "SELECT * FROM tblStocks WHERE ticker = ?";
    private final String SEARCH_STOCKS = "SELECT ticker, name, sector, price, status FROM tblStocks WHERE ticker LIKE ? OR name LIKE ? OR sector LIKE ?";
    private final String UPDATE_STOCK="UPDATE tblStocks SET name=?, sector=?, price=?, status=? WHERE ticker=?";
    private final String DELETE_STOCK="DELETE tblStocks WHERE ticker=?";
    private final String CREATE_STOCK="INSERT INTO tblStocks(ticker, name, sector, price, status) VALUES(?, ?, ?, ?, ?)";
    
    public ArrayList<Stock> getStockList(){
        ArrayList<Stock> stocks = new ArrayList<>();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        String sql = GET_STOCKS;

        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String ticker = rs.getString("ticker");
                String name = rs.getString("name");
                String sector = rs.getString("sector");
                float price = rs.getFloat("price");
                boolean status = rs.getBoolean("status");

                Stock stock = new Stock(ticker, name, sector, price, status);
                stocks.add(stock);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return stocks;
    }
    
    public ArrayList<Stock> search(String query){
        ArrayList<Stock> stocks = new ArrayList<>();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        String sql = SEARCH_STOCKS;

        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement(sql); 
            ps.setString(1, '%' + query + '%');
            ps.setString(2, '%' + query + '%');
            ps.setString(3, '%' + query + '%');
            rs = ps.executeQuery();
            while (rs.next()) {
                String ticker = rs.getString("ticker");
                String name = rs.getString("name");
                String sector = rs.getString("sector");
                float price = rs.getFloat("price");
                boolean status = rs.getBoolean("status");
                stocks.add(new Stock(ticker, name, sector, price, status));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stocks;

    }
    
    
    public boolean update(Stock stock){
        String sql = UPDATE_STOCK;
        try (Connection con = DBUtils.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, stock.getName());
        ps.setString(2, stock.getSector());
        ps.setDouble(3, stock.getPrice());
        ps.setBoolean(4, stock.isStatus());
        ps.setString(5, stock.getTicker());

        return ps.executeUpdate() > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
    }
    
    public Stock getStockByTicker(String ticker){
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        String sql = GET_STOCK;

        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ticker);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new Stock(
                    rs.getString("ticker"),
                    rs.getString("name"),
                    rs.getString("sector"),
                    rs.getFloat("price"),
                    rs.getBoolean("status")
                );
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
    
    public boolean create(Stock stock) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            String sql = CREATE_STOCK;
            ps = conn.prepareStatement(sql);
            ps.setString(1, stock.getTicker());
            ps.setString(2, stock.getName());
            ps.setString(3, stock.getSector());
            ps.setFloat(4, stock.getPrice());
            ps.setBoolean(5, stock.isStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return false;
    }
    
    public boolean delete(String ticker) throws Exception{
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "DELETE tblStocks WHERE ticker=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, ticker);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
    }
    
}
