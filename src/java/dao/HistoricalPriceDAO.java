package dao;

import dto.HistoricalPrice;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import utils.DBUtils;

public class HistoricalPriceDAO {

    public ArrayList<HistoricalPrice> getAllHistoricalPrices() throws Exception {
        ArrayList<HistoricalPrice> list = new ArrayList<>();
        String sql = "SELECT ticker, date, [open], [close], [high], [low] FROM HistoricalPrices ORDER BY ticker, date";
        try ( Connection con = DBUtils.getConnection();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                HistoricalPrice hp = new HistoricalPrice(
                        rs.getString("ticker"),
                        rs.getDate("date"),
                        rs.getFloat("open"),
                        rs.getFloat("close"),
                        rs.getFloat("high"),
                        rs.getFloat("low")
                );
                list.add(hp);
            }
        }
        return list;
    }

    public boolean createHistoricalPrice(HistoricalPrice price) throws Exception {
        String sql = "INSERT INTO HistoricalPrices (ticker, date, [open], [close], [high], [low]) VALUES (?, ?, ?, ?, ?, ?)";
        try ( Connection con = DBUtils.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, price.getTicker());
            ps.setDate(2, new Date(price.getDate().getTime()));
            ps.setFloat(3, price.getOpen());
            ps.setFloat(4, price.getClose());
            ps.setFloat(5, price.getHigh());
            ps.setFloat(6, price.getLow());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean updateHistoricalPrice(HistoricalPrice price) throws Exception {
        String sql = "UPDATE HistoricalPrices SET [open] = ?, [close] = ?, [high] = ?, [low] = ? WHERE ticker = ? AND date = ?";
        try ( Connection con = DBUtils.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setFloat(1, price.getOpen());
            ps.setFloat(2, price.getClose());
            ps.setFloat(3, price.getHigh());
            ps.setFloat(4, price.getLow());
            ps.setString(5, price.getTicker());
            ps.setDate(6, new Date(price.getDate().getTime()));
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteHistoricalPrice(String ticker, Date date) throws Exception {
        String sql = "DELETE FROM HistoricalPrices WHERE ticker = ? AND date = ?";
        try ( Connection con = DBUtils.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ticker);
            ps.setDate(2, date);
            return ps.executeUpdate() > 0;
        }
    }

    public ArrayList<HistoricalPrice> searchHistoricalPrices(Date fromDate, Date toDate) throws Exception {
        ArrayList<HistoricalPrice> list = new ArrayList<>();
        String sql = "SELECT ticker, date, [open], [close], [high], [low] FROM HistoricalPrices WHERE date BETWEEN ? AND ? ORDER BY ticker, date";
        try ( Connection con = DBUtils.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, fromDate);
            ps.setDate(2, toDate);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new HistoricalPrice(
                            rs.getString("ticker"),
                            rs.getDate("date"),
                            rs.getFloat("open"),
                            rs.getFloat("close"),
                            rs.getFloat("high"),
                            rs.getFloat("low")
                    ));
                }
            }
        }
        return list;
    }
    public HistoricalPrice getHistoricalPrice(String ticker, Date date) throws Exception {
    String sql = "SELECT ticker, date, [open], [close], [high], [low] FROM HistoricalPrices WHERE ticker = ? AND date = ?";
    try (Connection con = DBUtils.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, ticker);
        ps.setDate(2, date);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return new HistoricalPrice(
                    rs.getString("ticker"),
                    rs.getDate("date"),
                    rs.getFloat("open"),
                    rs.getFloat("close"),
                    rs.getFloat("high"),
                    rs.getFloat("low")
                );
            }
        }
    }
    return null;
}


}
