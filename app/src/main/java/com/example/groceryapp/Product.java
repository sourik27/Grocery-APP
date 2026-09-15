package com.example.groceryapp;

public class Product implements java.io.Serializable{
    private String name;
    private String price;
    private String barcodeID;
    public Product() {
    }

    public Product(String name, String price, String barcodeID) {
        this.name = name;
        this.price = price;
        this.barcodeID = barcodeID;
    }


    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getBarcodeID() {
        return barcodeID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setBarcodeID(String barcodeID) {
        this.barcodeID = barcodeID;
    }
}