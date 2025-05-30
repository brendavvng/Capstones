package com.pluralsight;

import com.pluralsight.models.Sandwich;
import com.pluralsight.models.Topping;
import com.pluralsight.models.Order;
import com.pluralsight.models.Chips;
import com.pluralsight.models.Drinks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.pluralsight.ui.UserInterface;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {

        UserInterface ui = new UserInterface();

        while (true) {
            int homeChoice = ui.showHomeScreen();
            if (homeChoice == 0) {
                System.out.println(" ∴.·:*¨¨*:·. Now exiting. Goodbye! .·:*¨ ¨*:·.∴ ");
                break;
            } else if (homeChoice == 1) {

                Order currentOrder = new Order();
                boolean ordering = true;

                while (ordering) {
                    int orderChoice = ui.showOrderMenu();

                    switch (orderChoice) {
                        // adding a sandwich
                        case 1:
                            // prompts for bread, size, and if toasted
                            String bread = ui.promptForBreadType();
                            int size = ui.promptForSize();
                            boolean toasted = ui.promptForToasted();

                            // creating new sandwich object
                            Sandwich sandwich = new Sandwich(bread, size, toasted);

                            // user can name their sandwich
                            boolean wantsToName = ui.askYesNo("ꕤ Would you like to name your sandwich? (Yes/No): ");
                            if (wantsToName) {
                                String sandwichName = ui.promptForSandwichName();
                                sandwich.setName(sandwichName);
                                System.out.println("ꕤ Your sandwich \"" + sandwichName + "\" has been added to the order!");
                            } else {
                                sandwich.setName("Unnamed Sandwich");
                                System.out.println("ꕤ Sandwich added without a name.");
                            }

                            // adding meats
                            boolean addMoreMeat;
                            do {
                                String meat = ui.promptForMeat();

                                // if user chooses none, it will not add meat and will skip extra meats question
                                if (meat.equalsIgnoreCase("none")) {
                                    System.out.println("ꕤ No meat added.");
                                    break;
                                }

                                boolean extra = ui.promptForExtraMeat();
                                sandwich.addMeat(meat, extra);
                                addMoreMeat = ui.askYesNo("ꕤ Add another meat? (Yes/No): ");
                            } while (addMoreMeat);

                            // adding cheeses
                            boolean addMoreCheese;
                            do {
                                String cheese = ui.promptForCheese();

                                // if user chooses 'none' – skip extra cheese prompt and don't add to sandwich
                                if (cheese.equalsIgnoreCase("none")) {
                                    System.out.println("ꕤ No cheese added.");
                                    break;
                                } else {
                                    boolean extraCheese = ui.promptForExtraCheese();
                                    sandwich.addCheese(cheese, extraCheese);
                                }

                                addMoreCheese = ui.askYesNo("ꕤ Add another cheese? (Yes/No): ");
                            } while (addMoreCheese);

                            // adding toppings
                            boolean addMoreToppings = true;
                            do {
                                String topping = ui.promptForTopping();
                                // if user chooses "none", no toppings will be added
                                if (topping.equalsIgnoreCase("none")) {
                                    System.out.println("ꕤ No toppings added.");
                                    break;
                                }

                                sandwich.addTopping(topping);
                                addMoreToppings = ui.askYesNo("ꕤ Add another topping? (Yes/No): ");
                            } while (addMoreToppings);

                            // add sauces - included
                            boolean addMoreSauces;
                            do {
                                String sauce = ui.promptForSauce();
                                sandwich.addSauce(sauce);
                                addMoreSauces = ui.askYesNo("ꕤ Add another sauce? (Yes/No): ");
                            } while (addMoreSauces);

                            boolean addSides = ui.promptToAddSide();
                            if (addSides) {
                                while (addSides) {
                                    String side = ui.promptForSide();
                                    sandwich.addSide(side);
                                    addSides = ui.askYesNo("ꕤ Add another side? (Yes/No): ");
                                }
                            } else {
                                System.out.println("₊˚ʚ✧ No side added. ₊˚ʚ✧");
                            }

                            currentOrder.addSandwich(sandwich);

                            // printing order summary
                            System.out.println("ꕤ Sandwich has been added: " + sandwich.getSummary());
                            break;

                        // adding a drink
                        case 2:
                            Drinks drink = ui.promptForDrink();
                            currentOrder.addDrink(drink);
                            System.out.println("ꕤ Added: " + drink);
                            break;
                        case 3:
                            Chips chip = ui.promptForChips();
                            currentOrder.addChips(chip);
                            System.out.println("ꕤ Added: " + chip);
                            break;
                        case 4:
                            // prints out order summary
                            System.out.println(currentOrder.getOrderSummary());

                            boolean confirm = ui.askYesNo("ꕤ Confirm order? (Yes/No): ");
                            if (confirm) {
                                try {
                                    String receiptContent = currentOrder.getOrderSummary();

                                    // time stamp format yyyyMMdd-HHmmss
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
                                    String timestamp = LocalDateTime.now().format(formatter);

                                    // full path with timestamp
                                    String receiptPath = "src/main/resources/Receipts/receipt_" + timestamp + ".txt";

                                    // write the receipt file
                                    Files.write(Paths.get(receiptPath), receiptContent.getBytes());

                                    System.out.println("ꕤ Receipt saved to: " + receiptPath);
                                } catch (Exception e) {
                                    System.out.println("ꕤ Error saving receipt: " + e.getMessage());
                                }
                                System.out.println("✧･ﾟ: *✧･ﾟ:* 　　 *:･ﾟ✧*:･ﾟ✧\n" +
                                        "       ❀ Thank you for your order! ❀\n" +
                                        "       ✧･ﾟ: *✧･ﾟ:* 　　 *:･ﾟ✧*:･ﾟ✧\n" +
                                        "         Your order is on its way!\n" +
                                        "           ꕥ Stay DELI-cious ꕥ");
                            } else {
                                System.out.println("ꕤ Order cancelled.");
                            }

                            ordering = false;
                            break;


                        case 0:
                            System.out.println("ꕤ Order has been cancelled. Returning to home screen.");
                            ordering = false;
                            break;
                        default:
                            System.out.println("ꕤ Invalid option. Try again.");
                    }

                }
            }
        }

    }
}
