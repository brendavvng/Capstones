package com.pluralsight;

import java.util.ArrayList;
import java.util.List;


public class Order {
    // 	Holds sandwiches, drinks, chips

    // create List for order items (sandwiches, drinks, and chips)
    // store total price
    private List<Sandwich> sandwiches;
    private List<Drinks> drinks;
    private List<Chips> chips;

    // constructor:
    //   - initialize the class properties
    public Order(List<Sandwich> sandwiches, List<Chips> chips, List<Drinks> drinks) {
        this.sandwiches = new ArrayList<>();
        this.chips = new ArrayList<>();
        this.drinks = new ArrayList<>();
    }

    // addSandwich method:
    //   - add sandwich to list
    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);
    }

    // addDrink method:
    //   - add drink to list
    public void addDrink(Drinks drink) {
        drinks.add(drink);
    }

    // addChips method:
    //   - add chips to list
    public void addChips(Chips chip) {
        chips.add(chip);
    }

    // getTotal method:
    //   - return total price of order
    public double getTotal() {
        double totalPrice = 0;

        for (Sandwich sandwich : sandwiches) {
            totalPrice += sandwich.getPrice();
        }

        for (Drinks drink : drinks) {
            totalPrice += drink.getPrice();
        }

        for (Chips chip : chips) {
            totalPrice += chip.getPrice();
        }

        return totalPrice;

    }

    // getSandwiches, getDrinks, getChips:
    //   - return the orders items

    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public List<Drinks> getDrinks() {
        return drinks;
    }

    public List<Chips> getChips() {
        return chips;
    }

    // getOrderSummary method:
    //   - return formatted string of all items and total

    public String getOrderSummary () {
        // string builder to build the order summary string
        StringBuilder orderSummary = new StringBuilder();

        // append to add onto string builder
        orderSummary.append("Order Summary \n");
        orderSummary.append(".¸¸.*♡*.¸¸.*☆*¸.*♡*.¸¸.*☆*.¸¸.*♡*.¸\n");
        orderSummary.append("Sandwiches: \n");
        if (sandwiches.isEmpty()) {
            // if no sandwiches, will print 'none'
            orderSummary.append("None \n");
        } else {
            // print out sandwich summary
            for (Sandwich sandwich : sandwiches) {
                orderSummary.append("  ")
                        .append(sandwich.getSummary())
                        .append("\n");
            }
        }
        orderSummary.append("Drinks: \n");
        if (drinks.isEmpty()) {
            // if no drinks, will print out 'none'
            orderSummary.append("None \n");
        } else {
            // print out drink summary with price
            for (Drinks drink : drinks) {
                orderSummary.append("  ")
                        .append(drink.toString())
                        .append(" - $")
                        .append(String.format("%.2f", drink.getPrice()))
                        .append("\n");
            }
        }

        orderSummary.append("Chips: \n");
        if (chips.isEmpty()) {
            orderSummary.append("None \n");
        } else {
            // print out list chip & prices
            for (Chips chip : chips) {
                orderSummary.append("  ")
                        .append(chip.toString())
                        .append(" - $")
                        .append(String.format("%.2f", chip.getPrice()))
                        .append("\n");

            }
        }

        orderSummary.append(".¸¸.*♡*.¸¸.*☆*¸.*♡*.¸¸.*☆*.¸¸.*♡*.¸\n");

        // adding total price to order
        orderSummary.append(String.format("Total: $%.2f\n", getTotal()));

        // returning the final string of order summary
        return orderSummary.toString();

    }

}
