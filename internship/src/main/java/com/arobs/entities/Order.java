package com.arobs.entities;

public class Order {

    private int orderNumber;
    private int userId;

    public Order(){}

    public Order(int orderNumber, int userId) {
        this.orderNumber = orderNumber;
        this.userId = userId;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
