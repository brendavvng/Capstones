package com.pluralsight.ui;
import com.pluralsight.models.Chips;
import com.pluralsight.models.Drinks;
import com.pluralsight.models.Topping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class UserInterface {
    private Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    // Home Screen
    public int showHomeScreen() {
        // welcome message
        System.out.print("｡･:*˚:✧｡｡･:*˚:✧｡｡･:*˚:✧｡｡･:*˚:✧｡｡･:*˚:✧｡\n");
        System.out.print("Welcome to The Sandwich Loop!\n");
        System.out.println("₊˚ʚ✧ Please choose one of the following numbers below to move forward: ₊˚ʚ✧ \n");
        System.out.println("1] Place a new order");
        System.out.println("0] Exit");
        System.out.print("ꕤ Enter choice: ");
        return getIntInput();


    }

    public int showOrderMenu() {
        System.out.print("\n");
        System.out.println("₊˚ʚ✧ Please choose one of the following numbers below to move forward: ₊˚ʚ✧\n");
        System.out.println("1] Add a sandwich");
        System.out.println("2] Add a drink");
        System.out.println("3] Add chips");
        System.out.println("4] Checkout");
        System.out.println("0] Cancel order");
        System.out.print("ꕤ Enter choice: ");
        return getIntInput();
    }


    // creating validated input for user input
    private String getValidatedInput(List<String> validOptions) {
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (validOptions.contains(input)) {
                return input;
            }
            System.out.print("ꕤ Invalid input. Please try again: ");
        }
    }


    public String promptForBreadType() {
        System.out.println("₊˚ʚ✧ Choose bread type by number: ₊˚ʚ✧ \n");
        System.out.println("1] white");
        System.out.println("2] wheat");
        System.out.println("3] rye");
        System.out.println("4] wrap");
        System.out.print("ꕤ Enter choice: ");
        int choice = getIntInput();
        switch (choice) {
            case 1:
                return "white";
            case 2:
                return "wheat";
            case 3:
                return "rye";
            case 4:
                return "wrap";
            default:
                System.out.println("ꕤ Invalid choice, defaulting bread to white bread.");
                return "White";
        }
    }

    public int promptForSize() {
        System.out.println("₊˚ʚ✧ Please choose a size: 4, 8, or 12 inches. ₊˚ʚ✧ ");
        int size = getIntInput();
        if (size == 4 || size == 8 || size == 12) {
            return size;
        }
        System.out.println("ꕤ Invalid size, defaulting size to 4.");
        return 4;
    }

    public boolean promptForToasted() {
        // while loop to ensure user enters valid input
        while (true) {
            System.out.println("₊˚ʚ✧ Would you like it toasted? (yes/no) ₊˚ʚ✧");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("yes") || input.equals("y")) {
                return true;
            } else if (input.equals("no") || input.equals("n")) {
                return false;
            } else {
                System.out.println("ꕤ Invalid input. Please type 'yes' or 'no'.");
            }
        }
    }

    public String promptForMeat() {
        // display meats get input
        List<String> meats = List.of("steak", "ham", "salami", "roast beef", "chicken", "bacon", "none");

        System.out.println("₊˚ʚ✧ List of Meats: ₊˚ʚ✧ ");
        for (String meat : meats) {
            System.out.println("𓇣 " + meat);
        }

        System.out.print("₊˚ʚ✧ Please enter the meat you'd like: ₊˚ʚ✧ ");
        return getValidatedInput(meats);

    }


    public boolean promptForExtraMeat() {
        // yes / no input
        return askYesNo("₊˚ʚ✧ Would you like extra meat? (Yes/No): ₊˚ʚ✧ ");
    }

    public String promptForCheese() {
        // display cheeses, get input
        List<String> cheeseOptions = List.of("american", "swiss", "cheddar", "provolone", "none");
        System.out.println("\n₊˚ʚ✧ List of Cheeses: ₊˚ʚ✧ ");
        cheeseOptions.forEach(option -> System.out.println("𓇣 " + option));
        System.out.print("₊˚ʚ✧ Please enter which cheese you'd like: ₊˚ʚ✧ ");
        return getValidatedInput(cheeseOptions);
    }

    public boolean promptForExtraCheese() {
        // yes / no input
        return askYesNo("₊˚ʚ✧ Would you like extra cheese? (Yes/No): ₊˚ʚ✧ ");
    }

    public String promptForTopping() {
        // display toppings and get input

        List<String> toppings = List.of("lettuce", "peppers", "onions", "tomatoes", "jalapenos",
                "cucumbers", "pickles", "guacamole", "mushrooms", "none");

        System.out.println("\n₊˚ʚ✧  List of Regular Toppings: ₊˚ʚ✧ ");
        toppings.forEach(option -> System.out.println("𓇣 " + option));
        System.out.print("₊˚ʚ✧ Please enter which topping you'd like: ₊˚ʚ✧  ");

        String input = scanner.nextLine().trim().toLowerCase();

        // cool hidden words that add effects to sandwich
        if (input.equalsIgnoreCase("love")) {
            System.out.println("❣ You've added a pinch of love to your sandwich! ❣");
            return "love";
        } else if (input.equalsIgnoreCase("sparkles")) {
            System.out.println("✩ Your sandwich now sparkles with joy! ✩");
            return "sparkles";
        }
        while (!toppings.contains(input)) {
            System.out.print("ꕤ Invalid topping. Please choose again: ");
            input = scanner.nextLine().trim().toLowerCase();
        }

        return input;
    }

    public String promptForSauce() {
        // display sauces, get input
        List<String> sauceOptions = List.of("mayo", "mustard", "ketchup",
                "ranch", "thousand islands", "vinaigrette");

        System.out.println("\n₊˚ʚ✧  List of Sauces: ₊˚ʚ✧ ");
        sauceOptions.forEach(option -> System.out.println("𓇣 " + option));
        System.out.print("₊˚ʚ✧ Please enter which sauce you'd like: ₊˚ʚ✧ ");
        return getValidatedInput(sauceOptions);
    }

    public String promptForSide() {
        List<String> sides = List.of(
                "au jus", "mayo", "mustard", "ketchup", "ranch",
                "thousand islands", "vinaigrette"
        );

        System.out.println("\n₊˚ʚ✧ List of Sides: ₊˚ʚ✧ ");
        for (String side : sides) {
            System.out.println(side);
        }

        System.out.print("₊˚ʚ✧ Please enter which side you'd like: ₊˚ʚ✧ ");
        return getValidatedInput(sides);
    }

    // yes or no prompt for user
    public boolean askYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("yes") || input.equals("y")) {
                return true;
            } else if (input.equals("no") || input.equals("n")) {
                return false;
            } else {
                System.out.println("ꕤ Invalid input. Please type 'yes' or 'no'.");
            }
        }
    }

    public boolean promptToAddSide() {
        return askYesNo("₊˚ʚ✧  Would you like to add a side? (Yes/No): ₊˚ʚ✧  ");
    }

    // method to read int input
    private int getIntInput() {
        while (true) {
            String line = scanner.nextLine();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("ꕤ Invalid input. Please enter a number: ");
            }
        }
    }


    public Chips promptForChips() {
        List<String> chipOptions = Chips.CHIPPIES;

        System.out.println("₊˚ʚ✧ Please select what type of chips you'd like: ₊˚ʚ✧ ");
        for (int i = 0; i < chipOptions.size(); i++) {
            System.out.println((i + 1) + "] " + chipOptions.get(i));
        }

        int choice;
        while (true) {
            System.out.print("₊˚ʚ✧ Enter choice: ₊˚ʚ✧ ");
            choice = getIntInput();
            if (choice >= 1 && choice <= chipOptions.size()) {
                break;
            }
            System.out.println("ꕤ Invalid choice. Please choose a number from the list.");
        }

        return new Chips(chipOptions.get(choice - 1).trim());
    }

    public Drinks promptForDrink() {
        System.out.println("₊˚ʚ✧  Please select a drink: ₊˚ʚ✧ ");
        List<String> drinkOptions = Drinks.DRINKSSS;
        for (int i = 0; i < drinkOptions.size(); i++) {
            System.out.println((i + 1) + "] " + drinkOptions.get(i));
        }
        System.out.print("₊˚ʚ✧  Enter drink choice: ₊˚ʚ✧ ");
        int drinkChoice = getIntInput();
        String drinkName = (drinkChoice >= 1 && drinkChoice <= drinkOptions.size())
                ? drinkOptions.get(drinkChoice - 1).trim()
                : "Water";

        System.out.println("₊˚ʚ✧ Choose drink size: Small, Medium, or Large ₊˚ʚ✧ ");
        String size = scanner.nextLine().trim();

        try {
            return new Drinks(drinkName, size);
        } catch (IllegalArgumentException e) {
            System.out.println("ꕤ Invalid input, defaulting to Small Water.");
            return new Drinks("Water", "Small");
        }
    }

    public String promptForSandwichName() {
        System.out.print("₊˚ʚ✧ What would you like to name your sandwich? Enter here! ₊˚ʚ✧ ");
        return scanner.nextLine();
    }
}