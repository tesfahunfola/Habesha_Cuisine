package main.com.tesfahun.models;

import main.com.tesfahun.util.SandwichBuilder;

import java.util.Scanner;

public class OrderItem {

    // This method creates a custom sandwich using the SandwichBuilder
    public static Sandwich createSandwich(Scanner scanner) {
        return SandwichBuilder.build(scanner);
    }

    // This method lets the user choose a drink and returns it
    public static Drink createDrink(Scanner scanner) {
        return SandwichBuilder.selectDrink(scanner);
    }

    // This method lets the user choose a bag of chips and returns it
    public static Chips createChips(Scanner scanner) {
        return SandwichBuilder.selectChips(scanner);
    }
}