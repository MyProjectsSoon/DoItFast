package com.example.doitfast.model;

public class Invoice {

    private long invoiceID;
    private long userID;
    private String username;
    private long parkingID;
    private long vehicleID;
    private long cardID;

    public Invoice() {
    }

    public Invoice(long invoiceID, long userID, String username, long parkingID, long vehicleID, long cardID) {
        this.invoiceID = invoiceID;
        this.userID = userID;
        this.username = username;
        this.parkingID = parkingID;
        this.vehicleID = vehicleID;
        this.cardID = cardID;
    }

    public long getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(long invoiceID) {
        this.invoiceID = invoiceID;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getparkingID() {
        return parkingID;
    }

    public void setparkingID(long parkingID) {
        this.parkingID = parkingID;
    }

    public long getvehicleID() {
        return vehicleID;
    }

    public void setvehicleID(long vehicleID) {
        this.vehicleID = vehicleID;
    }

    public long getcardID() {
        return cardID;
    }

    public void setcardID(long cardID) {
        this.cardID = cardID;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceID=" + invoiceID +
                ", userID=" + userID +
                ", username='" + username + '\'' +
                ", parkingID=" + parkingID +
                ", vehicleID=" + vehicleID +
                ", cardID=" + cardID +
                '}';
    }
}
