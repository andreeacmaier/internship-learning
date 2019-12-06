package com.arobs.entities;

public class Item {

    private int itemId;
    private String product;
    private int stock;

    public Item() {
    }

    public Item(String product) {
        this.product = product;
    }

    public Item(String product, int stock) {
        this.product = product;
        this.stock = stock;
    }

    public Item(int itemId, String product, int stock) {
        this.itemId = itemId;
        this.product = product;
        this.stock = stock;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int quantity) {
        this.stock = quantity;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
