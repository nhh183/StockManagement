/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author loan1
 */
public class Alert {
    private int alertID;
    private String userID, ticker, direction, status;
    private float threshold;

    public Alert() {}

    public Alert(int alertID, String userID, String ticker, float threshold, String direction, String status) {
        this.alertID = alertID;
        this.userID = userID;
        this.ticker = ticker;
        this.threshold = threshold;
        this.direction = direction;
        this.status = status;
    }

        public int getAlertID() {
            return alertID;
        }

        public String getUserID() {
            return userID;
        }

        public String getTicker() {
            return ticker;
        }

        public String getDirection() {
            return direction;
        }

        public String getStatus() {
            return status;
        }

        public float getThreshold() {
            return threshold;
        }

        public void setAlertID(int alertID) {
            this.alertID = alertID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public void setTicker(String ticker) {
            this.ticker = ticker;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setThreshold(float threshold) {
            this.threshold = threshold;
        }


    }
