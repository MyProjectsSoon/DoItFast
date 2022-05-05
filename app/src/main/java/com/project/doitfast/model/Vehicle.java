package com.project.doitfast.model;

public class Vehicle {

    private int plateNo;
    private String category;
    private String source;
    private String code;
    private String payment;

    public Vehicle() {
    }

    public Vehicle(int plateNo, String category, String source, String code, String payment) {
        this.plateNo = plateNo;
        this.category = category;
        this.source = source;
        this.code = code;
        this.payment = payment;
    }

    public int getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(int plateNo) {
        this.plateNo = plateNo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }


    @Override
    public String toString() {
        return "Vehicle{" +
                "plateNo=" + plateNo +
                ", category='" + category + '\'' +
                ", source='" + source + '\'' +
                ", code='" + code + '\'' +
                ", payment='" + payment + '\'' +
                '}';
    }
}
