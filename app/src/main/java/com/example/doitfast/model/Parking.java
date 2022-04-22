package com.example.doitfast.model;

import java.util.Date;

public class Parking {

    private int parking_code;
    private Date arrive;
    private int hours;
    private float price;

    public Parking() {
    }

    public Parking(Date arrive, int hours, float price) {
        this.arrive = arrive;
        this.hours = hours;
        this.price = price;
    }

    public Parking(int parking_code, Date arrive, int hours, float price) {
        this.parking_code = parking_code;
        this.arrive = arrive;
        this.hours = hours;
        this.price = price;
    }

    public int getParking_code() {
        return parking_code;
    }

    public void setParking_code(int parking_code) {
        this.parking_code = parking_code;
    }

    public Date getArrive() {
        return arrive;
    }

    public void setArrive(Date arrive) {
        this.arrive = arrive;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "parking_code=" + parking_code +
                ", arrive=" + arrive +
                ", hours=" + hours +
                ", price=" + price +
                '}';
    }
}
