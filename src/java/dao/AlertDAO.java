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

    private final String INSERT_ALERT = "INSERT INTO tblAlerts(userID, ticker, threshold, direction, status) VALUES (?, ?, ?, ?, ?)";
    private final String SELECT_ALERTS = "SELECT * FROM tblAlerts";
    private final String SELECT_ALERT_BY_ID = "SELECT * FROM tblAlerts WHERE alertID = ?";
    private final String UPDATE_ALERT = "UPDATE tblAlerts SET ticker=?, threshold=?, direction=?, status=? WHERE alertID=?";
    private final String DELETE_ALERT = "DELETE FROM tblAlerts WHERE alertID=?";
    private final String SEARCH_ALERT = "SELECT * FROM tblAlerts WHERE (ticker LIKE ? OR direction LIKE ? OR status LIKE ?)";
    
    public boolean createAlert(Alert alert) throws SQLException {
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_ALERT)) {
            ps.setString(1, alert.getUserID());
            ps.setString(2, alert.getTicker());
            ps.setFloat(3, alert.getThreshold());
            ps.setString(4, alert.getDirection());
            ps.setString(5, alert.getStatus());
            return ps.executeUpdate() > 0;
        }
    }


    public ArrayList<Alert> getAlertList(String userID, String roleID) throws SQLException {
        boolean isAdmin = "AD".equals(roleID);
        ArrayList<Alert> list = new ArrayList<>();
        String sql = SELECT_ALERTS;
        if (!isAdmin) {
            sql += " WHERE userID=?";
        }

        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            if (!isAdmin) {
                ps.setString(1, userID);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Alert(
                        rs.getInt("alertID"),
                        rs.getString("userID"),
                        rs.getString("ticker"),
                        rs.getFloat("threshold"),
                        rs.getString("direction"),
                        rs.getString("status")
                    ));
                }
            }
        }
        return list;
    }

    public ArrayList<Alert> searchAlert(String keyword, String userID, String roleID) throws SQLException {
        boolean isAdmin = "AD".equals(roleID);
        ArrayList<Alert> list = new ArrayList<>();
        String sql = SEARCH_ALERT;
        if (!isAdmin) {
            sql += " AND userID=?";
        }

        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            if (!isAdmin) {
                ps.setString(4, userID);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Alert(
                        rs.getInt("alertID"),
                        rs.getString("userID"),
                        rs.getString("ticker"),
                        rs.getFloat("threshold"),
                        rs.getString("direction"),
                        rs.getString("status")
                    ));
                }
            }
        }
        return list;
    }

    public Alert getAlertByID(int alertID) throws SQLException {
        Alert alert = null;
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALERT_BY_ID)) {
            ps.setInt(1, alertID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    alert = new Alert(
                        rs.getInt("alertID"),
                        rs.getString("userID"),
                        rs.getString("ticker"),
                        rs.getFloat("threshold"),
                        rs.getString("direction"),
                        rs.getString("status")
                    );
                }
            }
        }
        return alert;
    }
    public ArrayList<Alert> advancedSearchAlert(String keyword, String direction, String status, String userID, String roleID) throws SQLException {
    ArrayList<Alert> list = new ArrayList<>();
    boolean isAdmin = "AD".equals(roleID);

    StringBuilder sql = new StringBuilder("SELECT * FROM tblAlerts WHERE 1=1");

    if (keyword != null && !keyword.isEmpty()) {
        sql.append(" AND ticker LIKE ?");
    }
    if (direction != null && !direction.isEmpty()) {
        sql.append(" AND direction = ?");
    }
    if (status != null && !status.isEmpty()) {
        sql.append(" AND status = ?");
    }
    if (!isAdmin) {
        sql.append(" AND userID = ?");
    }

    try (Connection con = DBUtils.getConnection();
         PreparedStatement ps = con.prepareStatement(sql.toString())) {

        int index = 1;

        if (keyword != null && !keyword.isEmpty()) {
            ps.setString(index++, "%" + keyword + "%");
        }
        if (direction != null && !direction.isEmpty()) {
            ps.setString(index++, direction);
        }
        if (status != null && !status.isEmpty()) {
            ps.setString(index++, status);
        }
        if (!isAdmin) {
            ps.setString(index++, userID);
        }

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Alert(
                    rs.getInt("alertID"),
                    rs.getString("userID"),
                    rs.getString("ticker"),
                    rs.getFloat("threshold"),
                    rs.getString("direction"),
                    rs.getString("status")
                ));
            }
        }
    }

    return list;
}

    
    public boolean updateAlert(Alert alert) throws SQLException {
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_ALERT)) {
            ps.setString(1, alert.getTicker());
            ps.setFloat(2, alert.getThreshold());
            ps.setString(3, alert.getDirection());
            ps.setString(4, alert.getStatus());
            ps.setInt(5, alert.getAlertID());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteAlert(int alertID) throws SQLException {
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_ALERT)) {
            ps.setInt(1, alertID);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean canEditOrDeleteAlert(int alertID, String userID) throws SQLException {
        String sql = "SELECT * FROM tblAlerts WHERE alertID = ? AND userID = ? AND status = 'inactive'";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, alertID);
            ps.setString(2, userID);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // true if found = can edit/delete
            }
        }
    }
    
    
    public ArrayList<Alert> filterAlerts(String ticker, String direction, String status, String userID, String roleID) throws SQLException {
    ArrayList<Alert> list = new ArrayList<>();
    boolean isAdmin = "AD".equals(roleID);

    StringBuilder sql = new StringBuilder("SELECT * FROM tblAlerts WHERE 1=1");

    if (!isAdmin) {
        sql.append(" AND userID = ?");
    }

    if (ticker != null && !ticker.trim().isEmpty()) {
        sql.append(" AND ticker LIKE ?");
    }
    if (direction != null && !direction.isEmpty()) {
        sql.append(" AND direction = ?");
    }
    if (status != null && !status.isEmpty()) {
        sql.append(" AND status = ?");
    }

    try (Connection con = DBUtils.getConnection();
         PreparedStatement ps = con.prepareStatement(sql.toString())) {

        int index = 1;
        if (!isAdmin) {
            ps.setString(index++, userID);
        }
        if (ticker != null && !ticker.trim().isEmpty()) {
            ps.setString(index++, "%" + ticker.trim().toUpperCase() + "%");
        }
        if (direction != null && !direction.isEmpty()) {
            ps.setString(index++, direction);
        }
        if (status != null && !status.isEmpty()) {
            ps.setString(index++, status);
        }

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Alert(
                    rs.getInt("alertID"),
                    rs.getString("userID"),
                    rs.getString("ticker"),
                    rs.getFloat("threshold"),
                    rs.getString("direction"),
                    rs.getString("status")
                ));
            }
        }
    }

    return list;
}

    public ArrayList<Alert> searchAlert(String keyword, String direction, String status, String userID, String roleID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}