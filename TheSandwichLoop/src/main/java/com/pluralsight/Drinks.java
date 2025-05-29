package com.pluralsight;
import java.util.List;
import java.util.Arrays;


public class Drinks {

    // initializing properties
    private String name;
    private String size;
    private double price;

    // list of drinks
    public static final List<String> DRINKSSS = Arrays.asList("Coke\n",
            "Pepsi\n",
            "Dr Pepper\n",
            "Sprite\n",
            "Sweet Tea\n",
            "Lemonade\n",
            "Water\n");

    // constructor
    // only doing name and size since price is set
    public Drinks(String name, String size) {
        if (!isValidDrink(name)) {
            //
            throw new IllegalArgumentException("Invalid drink name: " + name);
        }

        this.name = name;
        // setting size so it also sets price
        setSize(size);

        if (!setSize(size)) {
            throw new IllegalArgumentException("Invalid size: " + size);
        }
    }

    // validating the drink names
    private boolean isValidDrink (String drinkName) {
        return DRINKSSS.contains(drinkName);
    }

    // getters and setters
    public String getName() {
        return name;
    }


    public String getSize() {
        return size;
    }

    // setting sizes and prices
    // returning if true or else returns false
    public boolean setSize(String size) {
        size = size.toLowerCase();
        switch (size) {
            case "small":
                this.size = "Small";
                this.price = 2.00;
                return true;
            case "medium":
                this.size = "Medium";
                this.price = 2.50;
                return true;
            case "large":
                this.size = "Large";
                this.price = 3.00;
                return true;
            default:
                // returning false if invalid
                return false;
        }
    }

    public double getPrice() {
        return price;
    }


    @Override
    public String toString() {
        return size + " " + name + "($" + String.format("%.2f", price) +")";
    }

}
