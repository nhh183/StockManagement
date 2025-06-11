package dao_business_rating;

import dto.User;
import dto_business_rating.Rating;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import utils.DBUtils;

public class RatingDAO {

    private static final String CREATE_RATING = "INSERT INTO Rating (userID, ticker, score, note)  VALUES (?, ?, ?, ?)";
    private static final String UPDATE_RATING = "UPDATE Rating SET ticker = ?, score = ?, note = ? WHERE userID = ? AND DATEDIFF(HOUR, createdAt, GETDATE()) <= 24";
    private static final String DELETE_RATING = "DELETE FROM Rating WHERE userID = ?";
    private static final String SEARCH_RATING = "SELECT * FROM Rating WHERE score >= ?";

    // hàm created
    public boolean createRating(Rating rating) throws SQLException {
        if (rating == null || rating.getScore() < 0 || rating.getScore() > 10) {
            throw new IllegalArgumentException("Invalid rating data: score must be between 0 and 10");
        }
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(CREATE_RATING);
            ps.setString(1, rating.getUserID());
            ps.setString(2, rating.getTicker());
            ps.setInt(3, rating.getScore());
            ps.setString(4, rating.getNote());
            //  ps.setTimestamp(5, Timestamp.valueOf(rating.getCreatedAt()));
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

    public ArrayList<Rating> Search(String keyword) throws Exception {
        String sql = SEARCH_RATING;
        ArrayList<Rating> ratingList = new ArrayList<Rating>();
        Connection conn = null;

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareCall(sql);
            int score = Integer.parseInt(keyword);
            ps.setInt(1, score);
            rs = ps.executeQuery();
            while (rs.next()) {
                Rating rating = new Rating(
                        rs.getString("userID"),
                        rs.getString("ticker"),
                        rs.getInt("score"),
                        rs.getString("note"),
                        rs.getTimestamp("createdAt").toLocalDateTime()
                );
                ratingList.add(rating);
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
        return ratingList;
    }

    public boolean updateRating(Rating rating) throws SQLException {
        String sql = UPDATE_RATING;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            LocalDateTime now = LocalDateTime.now();
            Duration duration = Duration.between(rating.getCreatedAt(), now);
            if (duration.toHours() <= 24) {
                ps.setString(1, rating.getTicker());
                ps.setInt(2, rating.getScore());
                ps.setString(3, rating.getNote());
                ps.setString(4, rating.getUserID());
                return ps.executeUpdate() > 0;
            } else {
                System.out.println("Rating is over 24 hours old, not updated!");
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
        }
        return false;
    }

    public boolean deleteRating(String ratingID) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            String sql = DELETE_RATING;
            ps = conn.prepareStatement(sql);
            ps.setString(1, ratingID);
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

    public Rating getRatingbyUserId(String ratingID) throws Exception {
        User user = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Rating WHERE userID = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, ratingID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    Rating rating = new Rating(
                            rs.getString("userID"),
                            rs.getString("ticker"),
                            rs.getInt("score"),
                            rs.getString("note"),
                            rs.getTimestamp("createdAt").toLocalDateTime()
                    );
                    return rating;
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

    public ArrayList<Rating> getAllRating() throws Exception {
        ArrayList<Rating> ratingList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection(); // chỗ này tuỳ theo project bạn lấy connection sao
            String sql = "select * from Rating";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Rating rating = new Rating(
                        rs.getString("userID"),
                        rs.getString("ticker"),
                        rs.getInt("score"),
                        rs.getString("note"),
                        rs.getTimestamp("createdAt").toLocalDateTime()
                );
                ratingList.add(rating);
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
        return ratingList;
    }
}
