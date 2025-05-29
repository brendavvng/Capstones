package com.pluralsight;

public class Drinks {

    // initializing properties
    private String name;
    private String size;
    private double price;

    // constructors
    public Drinks(String name, String size, double price) {
        this.name = name;
        this.size = size;
        this.price = price;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return size + " " + name + "($" + String.format("%.2f", price) +")";
    }

}
