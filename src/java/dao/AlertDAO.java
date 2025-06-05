/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import util.DBUtil;
import dto.Alerts;
import java.sql.*;
import java.util.*;
/**
 *
 * @author loan1
 */
public class AlertDAO {
     public List<Alerts> getAlertsByUser(String userID, String keyword) throws SQLException {
        List<Alerts> list = new ArrayList<>();
        String sql = "SELECT * FROM tblAlerts WHERE userID = ? AND ticker LIKE ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userID);
            ps.setString(2, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Alerts(
                        rs.getInt("alertID"),
                        rs.getString("userID"),
                        rs.getString("ticker"),
                        rs.getDouble("threshold"),
                        rs.getString("direction"),
                        rs.getString("status")
                    ));
                }
            }
        }
        return list;
    }

    public boolean createAlert(Alerts alert) throws SQLException {
        String sql = "INSERT INTO tblAlerts (userID, ticker, threshold, direction, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, alert.getUserID());
            ps.setString(2, alert.getTicker());
            ps.setDouble(3, alert.getThreshold());
            ps.setString(4, alert.getDirection());
            ps.setString(5, alert.getStatus());
            return ps.executeUpdate() > 0;
        }
    }

    public Alerts getAlertById(int alertID) throws SQLException {
        String sql = "SELECT * FROM tblAlerts WHERE alertID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, alertID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Alerts(
                        rs.getInt("alertID"),
                        rs.getString("userID"),
                        rs.getString("ticker"),
                        rs.getDouble("threshold"),
                        rs.getString("direction"),
                        rs.getString("status")
                    );
                }
            }
        }
        return null;
    }

    public boolean updateAlert(Alerts alert) throws SQLException {
        String sql = "UPDATE tblAlerts SET threshold = ?, direction = ?, status = ? WHERE alertID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, alert.getThreshold());
            ps.setString(2, alert.getDirection());
            ps.setString(3, alert.getStatus());
            ps.setInt(4, alert.getAlertID());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteAlert(int alertID) throws SQLException {
        String sql = "DELETE FROM tblAlerts WHERE alertID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, alertID);
            return ps.executeUpdate() > 0;
        }
    }
    public List<Alerts> searchAll(String keyword) throws SQLException {
    List<Alerts> list = new ArrayList<>();
    String sql = "SELECT * FROM tblAlerts WHERE ticker LIKE ? OR userID LIKE ?";
    try (Connection conn = DBUtil.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, "%" + keyword + "%");
        ps.setString(2, "%" + keyword + "%");

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Alerts(
                    rs.getInt("alertID"),
                    rs.getString("userID"),
                    rs.getString("ticker"),
                    rs.getDouble("threshold"),
                    rs.getString("direction"),
                    rs.getString("status")
                ));
            }
        }
    }
    return list;
}


}
