package dto_business_rating;

import java.time.LocalDateTime;

public class Rating {

    private String userID;  // user đánh giá
    private String ticker;      // mã công ty
    private int score;           // điểm từ 0 đến 10
    private String note;         // ghi chú
    private LocalDateTime createdAt; // thời điểm tạo đánh giá

    public Rating(String userID, String ticker, int score, String note, LocalDateTime createdAt) {
        this.userID = userID;
        this.ticker = ticker;
        this.score = score;
        this.note = note;
        this.createdAt = createdAt;
    }

    public String getUserID() {
        return userID;
    }

    public String getTicker() {
        return ticker;
    }

    public int getScore() {
        return score;
    }

    public String getNote() {
        return note;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
