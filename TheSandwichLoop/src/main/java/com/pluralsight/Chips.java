package com.pluralsight;

import java.util.List;
import java.util.Arrays;

public class Chips {

    // initializing properties
    private String name;
    // chips are always going to cost 1.50
    private static final double chipPrice = 1.50;

    // list of chips
    public static final List<String> CHIPPIES = Arrays.asList("Classic Lays\n",
            "BBQ Lays\n",
            "Doritos\n",
            "Cheetos\n",
            "SunChips\n");


    // constructor
    public Chips(String name) {
        this.name = name;

    }

    // getters and setters
    public String getName() {
        return name;
    }


    public double getPrice() {
        return chipPrice;
    }

    @Override
    public String toString() {
        return name + " ($" + String.format("%.2f", chipPrice) + ")";
    }

}
