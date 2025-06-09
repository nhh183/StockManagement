package dao;

import dto.Alert;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;        

public class AlertDAO {

    private final String GET_ALERTS = "SELECT * FROM tblAlerts WHERE userID = ?";
    private final String GET_ALERT_BY_ID = "SELECT * FROM tblAlerts WHERE alertID = ?";
    private final String SEARCH_ALERTS = "SELECT * FROM tblAlerts WHERE userID = ? AND (ticker LIKE ? OR direction LIKE ? OR status LIKE ?)";
    private final String CREATE_ALERT = "INSERT INTO tblAlerts (userID, ticker, threshold, direction, status) VALUES (?, ?, ?, ?, ?)";
    private final String UPDATE_ALERT = "UPDATE tblAlerts SET threshold = ?, direction = ?, status = ? WHERE alertID = ?";
    private final String DELETE_ALERT = "DELETE FROM tblAlerts WHERE alertID = ?";

    public ArrayList<Alert> getAlertList(String userID) {
        ArrayList<Alert> alerts = new ArrayList<>();
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_ALERTS)) {
            ps.setString(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                alerts.add(new Alert(
                        rs.getInt("alertID"),
                        rs.getString("userID"),
                        rs.getString("ticker"),
                        rs.getFloat("threshold"),
                        rs.getString("direction"),
                        rs.getString("status")
                ));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alerts;
    }

//    public ArrayList<Alert> search(String userID, String query) {
//        ArrayList<Alert> alerts = new ArrayList<>();
//        try (Connection con = DBUtils.getConnection();
//             PreparedStatement ps = con.prepareStatement(SEARCH_ALERTS)) {
//            ps.setString(1, userID);
//            ps.setString(2, "%" + query + "%");
//            ps.setString(3, "%" + query + "%");
//            ps.setString(4, "%" + query + "%");
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                alerts.add(new Alert(
//                        rs.getInt("alertID"),
//                        rs.getString("userID"),
//                        rs.getString("ticker"),
//                        rs.getFloat("threshold"),
//                        rs.getString("direction"),
//                        rs.getString("status")
//                ));
//            }
//            rs.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return alerts;
//    }
    
    public ArrayList<Alert> search(String userID, String query, String direction, String status) {
    ArrayList<Alert> alerts = new ArrayList<>();
    StringBuilder sql = new StringBuilder("SELECT * FROM tblAlerts WHERE userID = ?");
    
    if (query != null && !query.trim().isEmpty()) {
        sql.append(" AND ticker LIKE ?");
    }
    if (direction != null && !direction.trim().isEmpty()) {
        sql.append(" AND direction = ?");
    }
    if (status != null && !status.trim().isEmpty()) {
        sql.append(" AND status = ?");
    }

    try (Connection con = DBUtils.getConnection();
         PreparedStatement ps = con.prepareStatement(sql.toString())) {

        int index = 1;
        ps.setString(index++, userID);
        if (query != null && !query.trim().isEmpty()) {
            ps.setString(index++, "%" + query + "%");
        }
        if (direction != null && !direction.trim().isEmpty()) {
            ps.setString(index++, direction);
        }
        if (status != null && !status.trim().isEmpty()) {
            ps.setString(index++, status);
        }

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            alerts.add(new Alert(
                rs.getInt("alertID"),
                rs.getString("userID"),
                rs.getString("ticker"),
                rs.getFloat("threshold"),
                rs.getString("direction"),
                rs.getString("status")
            ));
        }
        rs.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

    return alerts;
}


    
    public Alert getAlertById(int alertID) {
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_ALERT_BY_ID)) {
            ps.setInt(1, alertID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Alert alert = new Alert(
                        rs.getInt("alertID"),
                        rs.getString("userID"),
                        rs.getString("ticker"),
                        rs.getFloat("threshold"),
                        rs.getString("direction"),
                        rs.getString("status")
                );
                rs.close();
                return alert;
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean create(Alert alert) throws Exception {
    try (Connection conn = DBUtils.getConnection();
         PreparedStatement ps = conn.prepareStatement(CREATE_ALERT)) {

        ps.setString(1, alert.getUserID());
        ps.setString(2, alert.getTicker());
        ps.setFloat(3, alert.getThreshold());
        ps.setString(4, alert.getDirection());
        ps.setString(5, alert.getStatus());

        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
            
    }
    return false;
}

    

    public boolean update(Alert alert) throws Exception {
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_ALERT)) {
            ps.setFloat(1, alert.getThreshold());
            ps.setString(2, alert.getDirection());
            ps.setString(3, alert.getStatus());
            ps.setInt(4, alert.getAlertID());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int alertID) throws Exception {
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_ALERT)) {
            ps.setInt(1, alertID);
            return ps.executeUpdate() > 0;
        }
    }
}