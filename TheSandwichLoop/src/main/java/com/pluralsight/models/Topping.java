package com.pluralsight.models;


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
    private List<String> extraMeat = new ArrayList<>();
    private List<String> extraCheese = new ArrayList<>();

    private double meatCost = 0;
    private double cheeseCost = 0;


    // list of regular toppings
    public static final List<String> REGULARTOPPINGSLIST = Arrays.asList("lettuce",
            " peppers",
            " onions",
            " tomatoes",
            " jalapenos",
            " cucumbers",
            " pickles",
            " guacamole",
            " mushrooms",
            " none");

    public static final List<String> MEATTOPPINGS = Arrays.asList(" steak",
            " ham",
            " salami",
            " roast beef",
            " chicken",
            " bacon",
            "none");

    public static final List<String> CHEESETOPPINGS = Arrays.asList("american",
            " provolone",
            " cheddar",
            " swiss",
            "none");

    public static final List<String> SAUCETOPPINGS = Arrays.asList("mayo",
            " mustard",
            " ketchup",
            " ranch",
            " thousand islands",
            " vinaigrette",
             "none");

    public static final List<String> SIDES = Arrays.asList("au jus", "mayo" +
            "- mustard" +
            "Included Included Included" +
            "- ketchup" +
            "- ranch" +
            "- thousand islands" +
            "- vinaigrette",
            "none");


    public Topping(int sandwichSize) {
        this.sandwichSize = sandwichSize;
    }

    public void addMeat(String meat, boolean extra) {
        if (!meat.equalsIgnoreCase("none")) {
            meats.add(meat);
            meatCost += getMeatPrice();
            if (extra) {
                meatCost += getExtraMeatPrice();
                extraMeat.add(meat);
            }
        }
    }

    public void addCheese(String cheese, boolean extra) {
        if (!cheese.equalsIgnoreCase("none")) {
            cheeses.add(cheese);
            cheeseCost += getCheeseCost();
            if (extra) {
                cheeseCost += getExtraCheeseCost();
                extraCheese.add(cheese);
            }
        }
    }

    public void addTopping (String topping) {
        if (!topping.equalsIgnoreCase("none") && REGULARTOPPINGSLIST.contains(topping.toLowerCase())) {
            toppings.add(topping);
        }
    }

    public void addSauce (String sauce) {
        if (!sauce.equalsIgnoreCase("none") && SAUCETOPPINGS.contains(sauce.toLowerCase())) {
            sauces.add(sauce);
        }
    }


    public double calculateToppingCost() {
        // adding meat and cheese cost, other toppings are free
        return meatCost + cheeseCost;
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

    private double getCheeseCost() {
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

    private double getExtraCheeseCost() {
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


    // getters
    public List<String> getMeats() {
        return meats;
    }

    public List<String> getCheeses() {
        return cheeses;
    }

    public List<String> getSauces() {
        return sauces;
    }

    public List<String> getToppings() {
        return toppings;
    }


}
