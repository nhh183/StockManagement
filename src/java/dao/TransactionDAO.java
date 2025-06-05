/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.TransactionDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DBUtils;

/**
 *
 * @author User
 */
public class TransactionDAO {

    public boolean createTrasaction(TransactionDTO transaction) throws SQLException {
        String sql = "INSERT INTO tblTransactions(UserID, ticker, type, quantity, price, status) VALUES(?,?,?,?,?,?)";
        boolean isCreated = false;
        try ( Connection cnn = DBUtils.getConnection();  PreparedStatement ps = cnn.prepareStatement(sql);) {
            ps.setString(1, transaction.getUserID());
            ps.setString(2, transaction.getTicker());
            ps.setString(3, transaction.getType());
            ps.setInt(4, transaction.getQuantity());
            ps.setFloat(5, transaction.getPrice());
            ps.setString(6, transaction.getStatus());
            isCreated = ps.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return isCreated;
    }

    public ArrayList<TransactionDTO> searchTransaction(String search,String userId) throws SQLException {
        ArrayList<TransactionDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM tblTransactions WHERE (ticker LIKE ? OR userID LIKE ? OR status LIKE ?) AND userID=?";
        try ( Connection cnn = DBUtils.getConnection();  
            PreparedStatement ps = cnn.prepareStatement(sql)) {
            ps.setString(1, '%' + search + '%');
            ps.setString(2, '%' + search + '%');
            ps.setString(3, '%' + search + '%');
            ps.setString(4, userId);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String userID = rs.getString("userID");
                    String ticker = rs.getString("ticker");
                    String type = rs.getString("type");
                    int quantity = rs.getInt("quantity");
                    float price = rs.getFloat("price");
                    String status = rs.getString("status");
                    list.add(new TransactionDTO(id, userID, ticker, type, quantity, price, status));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    public boolean updateTransaction(TransactionDTO transaction) throws Exception {
        String sql = "UPDATE tblTransactions SET ticker=?,type=?,quantity=?,price=?,status=? WHERE id=?";
        boolean isUpdated = false;
        try ( Connection cnn = DBUtils.getConnection();  PreparedStatement ps = cnn.prepareStatement(sql)) {
            ps.setString(1,transaction.getTicker());
            ps.setString(2, transaction.getType());
            ps.setInt(3, transaction.getQuantity());
            ps.setFloat(4, transaction.getPrice());
            ps.setString(5, transaction.getStatus());
            ps.setInt(6, transaction.getId());
            isUpdated = ps.executeUpdate() > 0;
        }
        return isUpdated;
    }

    public boolean deleteTransaction(int TransactionID) throws Exception {
        String sql = "DELETE FROM tblTransactions WHERE id=?";
        boolean isDeleted = false;
        try ( Connection cnn = DBUtils.getConnection();  PreparedStatement ps = cnn.prepareStatement(sql)) {
            ps.setInt(1, TransactionID);
            isDeleted = ps.executeUpdate() > 0;
        }
        return isDeleted;
    }
    
    public TransactionDTO getTransactionByID(int TransactionID) throws Exception{
        String sql="SELECT * FROM tblTransaction WHERE id=?";
        TransactionDTO transaction=null;
        try ( Connection cnn = DBUtils.getConnection();  PreparedStatement ps = cnn.prepareStatement(sql)) {
            ps.setInt(1, TransactionID);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String userID = rs.getString("userID");
                    String ticker = rs.getString("ticker");
                    String type = rs.getString("type");
                    int quantity = rs.getInt("quantity");
                    float price = rs.getFloat("price");
                    String status = rs.getString("status");
                    transaction=new TransactionDTO(id, userID, ticker, type, quantity, price, status);
                }
            }
        }
        return transaction;
        
    }
}
