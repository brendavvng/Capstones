package com.pluralsight;

import java.util.*;

public class Sandwich {

    // store bread type (white, wheat, rye, wrap)
    // store sandwich size (4, 8, or 12 inches)
    // store if sandwich is toasted
    // store meats, cheeses, toppings, sauces
    // keep track of total price

    private String breadType;
    private int sandwichSize;
    private boolean toasted;
    private double totalPrice;

    private List<String> meats = new ArrayList<>();
    private List<String> cheeses = new ArrayList<>();
    private List<String> toppings = new ArrayList<>();
    private List<String> sauces = new ArrayList<>();

    // constructor: takes bread type, size, toasted
    //   - initialize properties
    //   - set base price based on size
    public Sandwich(String breadType, int sandwichSize, boolean toasted) {
        this.breadType = breadType;
        this.sandwichSize = sandwichSize;
        this.toasted = toasted;
    }

    public boolean isToasted() {
        return toasted;
    }

    // addMeat method:
    //   - add meat(s)
    //   - what is the price based on size and whether it's extra?
    public void addMeat(String meat, boolean extra) {
        meats.add(meat);
        if (extra) {
            totalPrice += getExtraMeatPrice();
        }
    }

    private double getMeatPrice() {
        switch (sandwichSize){
            case 4:
                return 1.00;
            case 8:
                return 2.00;
            case 12:
                return 3.00;
            default:
                return 0;

        }
    }

    private double getExtraMeatPrice() {
        switch (sandwichSize) {
            case 4:
                return .50;
            case 8:
                return 1.00;
            case 12:
                return 1.50;
            default:
                return 0;
        }
    }


    // addCheese method:
    //   - add cheese(s)
    //   - add price depending on size and whether it's extra
    public void addCheese(String cheese, boolean extra) {
        cheeses.add(cheese);
        totalPrice += getCheesePrice();
        if (extra) {
            totalPrice += getExtraCheese();
        }
    }

    private double getCheesePrice() {
        switch (sandwichSize){
            case 4:
                return 0.75;
            case 8:
                return 1.50;
            case 12:
                return 2.25;
            default:
                return 0;

        }
    }

    private double getExtraCheese () {
        switch (sandwichSize) {
            case 4:
                return 0.30;
            case 8:
                return 0.60;
            case 12:
                return .90;
            default:
                return 0;
        }
    }

    // addTopping method:
    //   - add topping(s)
    //   - no charge
    public void addTopping(String topping) {
        toppings.add(topping);
    }


    // addSauce method:
    //   - add sauce(s)
    //   - no charge

    // getPrice method:
    //   - return current total price

    // getSummary method:
    //   - return a string with all sandwich details and price
}
