/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DBUtils;
import dto.User;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class UserDAO {

    public User login(String userID, String password) throws Exception {
        User user = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM tblUsers WHERE userID = ? AND password = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, userID);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    user = new User(userID, fullName, roleID, "***");
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return user;
    }

    public boolean createUser(User user) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO tblUsers (userID, fullName, roleID, password) VALUES (?, ?, ?, ?)";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareCall(sql);
            ps.setString(1, user.getUserID());
            ps.setString(2, user.getFullName());
            ps.setString(3, user.getRoleID());
            ps.setString(4, user.getPassword());
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

    public ArrayList<User> searchUser(String keyword) throws Exception {
        String sql = "SELECT * FROM tblUsers WHERE userID LIKE ? OR fullName LIKE ?";
        ArrayList<User> userList = new ArrayList<User>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareCall(sql);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                userList.add(new User(rs.getString("userID"), rs.getString("fullName"), rs.getString("roleID"), rs.getString("password")));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return userList;
    }

    public boolean updateUser(User user) throws Exception {
        String sql = "UPDATE tblUsers SET fullName = ?, roleID = ?, password = ? WHERE userID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getRoleID());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getUserID());
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

    public boolean deleteUser(String userID) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "DELETE tblUsers  WHERE userID=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, userID);
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

    public User getUserByID(String userID) throws Exception {
        User user = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM tblUsers WHERE userID = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, userID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return new User(rs.getString("userID"), rs.getString("fullName"), rs.getString("roleID"), rs.getString("password"));
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    public ArrayList<User> getAllUsers() throws Exception {
        ArrayList<User> userList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection(); // chỗ này tuỳ theo project bạn lấy connection sao
            String sql = "select * from tblUsers";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String userID = rs.getString("userID");
                String fullName = rs.getString("fullName");
                String roleID = rs.getString("roleID");
                String password = rs.getString("password");

                User user = new User(userID, fullName, roleID, password);
                userList.add(user);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return userList;
    }

}
