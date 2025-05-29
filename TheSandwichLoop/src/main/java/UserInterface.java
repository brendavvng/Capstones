import com.pluralsight.Sandwich;
import com.pluralsight.Topping;

import java.util.Scanner;

public interface UserInterface {

    // main method
    public static void main(String[] args) {

        // 	Menu prompts and input

        // initializing scanner
        Scanner sandwichScanner = new Scanner(System.in);

        // welcome message
        System.out.println("Welcome to The Sandwich Loop");


        // prints out toppings
        System.out.println(Topping.CHEESETOPPINGS);
        System.out.println(Topping.MEATTOPPINGS);
        System.out.println(Topping.SAUCETOPPINGS);
        System.out.println(Topping.REGULARTOPPINGSLIST);

    }

}
