package com.pluralsight;

public class Chips {

    // initializing properties
    private String name;
    private String size;
    private double price;


    // constructor
    public Chips(String name, String size, double price) {
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return size + " " + name + "($" + String.format("%.2f", price) +")";
    }

}
