package com.arobs.entities;

public class OrderDetails {

    private int id;
    private int itemId;
    private int orderNumber;
    private int quantity;

    public OrderDetails(){}

    public OrderDetails( Item item, Order order, int quantity) {
        this.itemId = item.getItemId();
        this.orderNumber = order.getOrderNumber();
        this.quantity = quantity;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId =  itemId;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
