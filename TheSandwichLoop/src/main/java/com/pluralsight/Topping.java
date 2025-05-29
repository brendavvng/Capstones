package com.pluralsight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Topping {

    // meat, cheese, regular, sauce
    private int sandwichSize;
    private List<String> meats = new ArrayList<>();
    private List<String> cheeses = new ArrayList<>();
    private List<String> toppings = new ArrayList<>();
    private List<String> sauces = new ArrayList<>();

    private double meatCost = 0;
    private double cheeseCost = 0;


    private static final List<String> regularToppingsList = Arrays.asList("lettuce", "peppers",
            "onions", "tomatoes", "jalapenos", "cucumbers", "pickles", "guacamole",
            "mushroom");

    public Toppings(int sandwichSize) {
        this.sandwichSize = sandwichSize;
    }

    public void addMeat(String meat, boolean extra) {
        meats.add(meat);
        meatCost += getMeatPrice(extra);
    }

    public void addCheese(String cheese, boolean extra) {
        cheeses.add(cheese);
        cheeseCost += getCheeseCost(extra);
    }

    public void addTopping (String topping) {
        if (regularToppingsList.contains(topping.toLowerCase())) {
            regularToppingsList.add(topping);
        }
    }

    public void addSauce (String sauce) {
        sauces.add(sauce);
    }


}
