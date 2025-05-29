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
        // user can only choose sizes 4, 8, and 12
        if (sandwichSize != 4 && sandwichSize != 8 && sandwichSize != 12) {
            System.out.println("Invalid size. Please choose a valid size: 4, 8, or 12 inches.");
        }

        this.breadType = breadType;
        this.sandwichSize = sandwichSize;
        this.toasted = toasted;
        // adding base price to total price
        this.totalPrice = getBasePrice();
    }

    private static final List<String> regularToppingsList = Arrays.asList("lettuce", "peppers",
            "onions", "tomatoes", "jalapenos", "cucumbers", "pickles", "guacamole",
            "mushroom");


    private double getBasePrice() {
        switch (sandwichSize) {
            case 4:
                return 5.50;
            case 8:
                return 7.00;
            case 12:
                return 8.50;
            default:
                return 0;
        }
    }

    public boolean isToasted() {
        return toasted;
    }


    // addMeat method:
    //   - add meat(s)
    //   - what is the price based on size and whether it's extra?
    public void addMeat(String meat, boolean extra) {
        meats.add(meat);
        // will always charge w base price for meats
        totalPrice += getMeatPrice();
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
                return 0.50;
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
                return 0.90;
            default:
                return 0;
        }
    }



    // addTopping method:
    //   - add topping(s)
    //   - no charge
    public void addTopping(String topping) {
        if (regularToppingsList.contains(topping.toLowerCase())) {
            toppings.add(topping);
        } else {
            System.out.println("Invalid topping: " + topping);
        }
    }


    // addSauce method:
    //   - add sauce(s)
    //   - no charge
    public void addSauce(String sauce) {
        sauces.add(sauce);
    }


    // getPrice method:
    //   - return current total price
    public double getPrice() {
        return totalPrice;
    }


    // getSummary method:
    //   - return a string with all sandwich details and price
    public String getSummary() {
        // using string builder to build info all together
        StringBuilder summary = new StringBuilder();

        // append to add onto string builder
        summary.append("Sandwich Order Summary \n");
        summary.append(".¸¸.*♡*.¸¸.*☆*¸.*♡*.¸¸.*☆*.¸¸.*♡*.¸\n");
        summary.append("Bread Type: ").append(breadType).append("\n");
        summary.append("Bread Size: ").append(sandwichSize).append(" inches \n");
        summary.append("Toasted: ").append(toasted ? "Yes" : "No").append("\n");

        summary.append("Meats: ");
        // if meats is empty, return 'none' otherwise return string of meats
        summary.append(meats.isEmpty() ? "None" : String.join(", ", meats));
        summary.append("\n");

        // if cheeses is empty, return 'none', otherwise return string of cheeses
        summary.append("Cheeses: ");
        summary.append(cheeses.isEmpty() ? "None" : String.join(", ", cheeses));
        summary.append("\n");

        summary.append("Toppings: ");
        summary.append(toppings.isEmpty() ? "None" : String.join(", ", toppings));
        summary.append("\n");

        summary.append("Sauces: ");
        summary.append(sauces.isEmpty() ? "None" : String.join(", ", sauces));
        summary.append("\n");

        summary.append(String.format("Total Price: $%.2f\n", totalPrice));
        summary.append(".¸¸.*♡*.¸¸.*☆*¸.*♡*.¸¸.*☆*.¸¸.*♡*.¸");

        // returning the full string input
        return summary.toString();
    }
}
