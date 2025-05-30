package com.pluralsight.models;

import java.util.*;

public class Sandwich {

    // store bread type (white, wheat, rye, wrap)
    // store sandwich size (4, 8, or 12 inches)
    // store if sandwich is toasted
    // store meats, cheeses, toppings, sauces
    // keep track of total price

    // property for name of sandwich user can type in
    private String name;

    private String breadType;
    private int sandwichSize;
    private boolean toasted;
    private double basePrice;
    // manages meats, cheeses, and regular toppings
    private Topping toppingsTop;
    // list of sauces
    private List<String> sauces = new ArrayList<>();
    // list of sides
    private List<String> sides = new ArrayList<>();


    // constructor: takes bread type, size, toasted
    //   - initialize properties
    //   - set base price based on size
    public Sandwich(String breadType, int sandwichSize, boolean toasted) {
        // user can only choose sizes 4, 8, and 12
        if (sandwichSize != 4 && sandwichSize != 8 && sandwichSize != 12) {
            System.out.println("Invalid size. Please choose a valid size: 4, 8, or 12 inches.");
        }

        this.breadType = breadType;
        this.sandwichSize = sandwichSize;
        this.toasted = toasted;
        // setting base price based on the size
        this.basePrice = getBasePrice();
        // initializing toppings w/ sandwich size, helps handle the prices
        this.toppingsTop = new Topping(sandwichSize);
    }

    // getter & setter for user input for sandwich name
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // base prices for sandwiche sizes
    private double getBasePrice() {
        switch (sandwichSize) {
            case 4:
                return 5.50;
            case 8:
                return 7.00;
            case 12:
                return 8.50;
            // if invalid size, will default to this:
            default:
                return 0;
        }
    }

    public boolean isToasted() {
        return toasted;
    }


    // addMeat method:
    //   - add meat(s) to sandwich
    //   - what is the price based on size and whether it's extra?
    public void addMeat(String meat, boolean extra) {
        toppingsTop.addMeat(meat, extra);
    }


    // addCheese method:
    //   - add cheese(s) to sandwich
    //   - add price depending on size and whether it's extra
    public void addCheese(String cheese, boolean extra) {
        toppingsTop.addCheese(cheese, extra);
    }


    // addTopping method:
    //   - add topping(s) to sandwich
    //   - no charge
    public void addTopping(String topping) {
        toppingsTop.addTopping(topping);
    }

    // addSauce method:
    //   - add sauce(s)
    //   - no charge
    public void addSauce(String sauce) {
        sauces.add(sauce);
    }

    // adds sides to order
    public void addSide(String side) {
        sides.add(side);
    }

    // getPrice method:
    //   - return current total price
    public double getPrice() {
        return basePrice + toppingsTop.calculateToppingCost();
    }

    // getter for bread type
    public String getBreadType() {
        return breadType;
    }

    public int getSandwichSize() {
        return sandwichSize;
    }


    public Topping getToppingsTop () {
        return toppingsTop;
    }

    // getSummary method:
    //   - return a string with all sandwich details and price
    public String getSummary() {
        // using string builder to build info all together
        StringBuilder summary = new StringBuilder();

        // append to add onto string builder
        summary.append(".¸¸.*♡*.¸¸.*☆*¸.*♡*.¸¸.*☆*.¸¸.*♡*.¸\n");

        // displays sandwich name if set to a name
        if (name != null && !name.isEmpty()) {
            summary.append("Sandwich Name: ").append(name).append("\n");
        }

        // adding bread type info
        summary.append("Bread Type: ").append(breadType).append("\n");
        // adding bread size info
        summary.append("Bread Size: ").append(sandwichSize).append(" inches \n");
        // adding if toasted or not
        summary.append("Toasted: ").append(toasted ? "Yes" : "No").append("\n");

        // if meats is empty, return 'none' otherwise return string of meats
        summary.append("Meats: ").append(toppingsTop.getMeats().isEmpty() ? "None" :
                String.join(", ", toppingsTop.getMeats())).append("\n");

        // if cheeses is empty, return 'none', otherwise return string of cheeses
        summary.append("Cheeses: ").append(toppingsTop.getCheeses().isEmpty() ? "None" :
                String.join(", ", toppingsTop.getCheeses())).append("\n");

        // adding toppings to list, if empty will print 'none', otherwise return string of toppings
        summary.append("Toppings: ").append(toppingsTop.getToppings().isEmpty() ? "None" :
                String.join(", ", toppingsTop.getToppings())).append("\n");


        // adding sauces to list if empty will print 'none', otherwise return string of sauces
        summary.append("Sauces: ").append(sauces.isEmpty() ? "None" :
                String.join(", ", sauces)).append("\n");

        // if sides list exists and is not empty, add to existing list
        if (sides != null && !sides.isEmpty()) {
            summary.append("Sides: ").append(String.join(", ", sides)).append("\n");
        }

        // adding summary of total price and formatting to 2 decimal places
        summary.append(String.format("Total Price: $%.2f\n", getPrice()));

        summary.append(".¸¸.*♡*.¸¸.*☆*¸.*♡*.¸¸.*☆*.¸¸.*♡*.¸");

        // returning the full string of summary
        return summary.toString();
    }

}
