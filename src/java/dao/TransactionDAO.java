/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Transaction;
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

    public boolean createTransaction(Transaction transaction) throws SQLException {
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
        }
        return isCreated;
    }

    public ArrayList<Transaction> getTransactionList(String userId,String roleID) throws SQLException{
        boolean isAdmin="AD".equals(roleID);
        ArrayList<Transaction> list=new ArrayList<>();
        String sql="SELECT * FROM tblTransactions";
        if(isAdmin==false){
            sql+=" WHERE userID=?";
        }
        try(Connection cnn=DBUtils.getConnection();
            PreparedStatement ps=cnn.prepareStatement(sql)){
            if(isAdmin==false){
                ps.setString(1, userId);
            }
            try(ResultSet rs=ps.executeQuery()){
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String userID = rs.getString("userID");
                    String ticker = rs.getString("ticker");
                    String type = rs.getString("type");
                    int quantity = rs.getInt("quantity");
                    float price = rs.getFloat("price");
                    String status = rs.getString("status");
                    list.add(new Transaction(id, userID, ticker, type, quantity, price, status));
                }
            }
        }
        return list;
    }
    
    public ArrayList<Transaction> searchTransaction(String search,String userId,String roleID) throws SQLException {
        boolean isAdmin="AD".equals(roleID);
        ArrayList<Transaction> list = new ArrayList<>();
        
        String sql = "SELECT * FROM tblTransactions WHERE (ticker LIKE ? OR type LIKE ? OR status LIKE ?)";
        if(isAdmin==false){
            sql+=" AND userID=?";
        }
        try ( Connection cnn = DBUtils.getConnection();  
            PreparedStatement ps = cnn.prepareStatement(sql)) {
            ps.setString(1, '%' + search + '%');
            ps.setString(2, '%' + search + '%');
            ps.setString(3, '%' + search + '%');
            if(isAdmin==false){
                ps.setString(4, userId);
            }
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String userID = rs.getString("userID");
                    String ticker = rs.getString("ticker");
                    String type = rs.getString("type");
                    int quantity = rs.getInt("quantity");
                    float price = rs.getFloat("price");
                    String status = rs.getString("status");
                    list.add(new Transaction(id, userID, ticker, type, quantity, price, status));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    public boolean updateTransaction(Transaction transaction) throws SQLException {
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

    public boolean deleteTransaction(int TransactionID) throws SQLException {
        String sql = "DELETE FROM tblTransactions WHERE id=?";
        boolean isDeleted = false;
        try ( Connection cnn = DBUtils.getConnection();  PreparedStatement ps = cnn.prepareStatement(sql)) {
            ps.setInt(1, TransactionID);
            isDeleted = ps.executeUpdate() > 0;
        }
        return isDeleted;
    }
    
    public Transaction getTransactionByID(int TransactionID) throws SQLException{
        String sql="SELECT * FROM tblTransactions WHERE id=?";
        Transaction result=null;
        try ( Connection cnn = DBUtils.getConnection();  PreparedStatement ps = cnn.prepareStatement(sql)) {
            ps.setInt(1, TransactionID);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String userID = rs.getString("userID");
                    String ticker = rs.getString("ticker");
                    String type = rs.getString("type");
                    int quantity = rs.getInt("quantity");
                    float price = rs.getFloat("price");
                    String status = rs.getString("status");
                    result=new Transaction(id, userID, ticker, type, quantity, price, status);
                }
            }
        }
        return result;
        
    }
}
