package main.com.tesfahun.models;

import main.com.tesfahun.util.PlatterBuilder;

import java.util.Scanner;

public class OrderItem {

    // This method creates a custom sandwich using the PlatterBuilder
    public static CuisinePlatter createSandwich(Scanner scanner) {
        return PlatterBuilder.build(scanner);
    }

    // This method lets the user choose a drink and returns it
    public static Drink createDrink(Scanner scanner) {
        return PlatterBuilder.selectDrink(scanner);
    }

    // This method lets the user choose a bag of Appetizers and returns it
    public static Appetizers createAppetizers(Scanner scanner) {
        return PlatterBuilder.selectAppetizers(scanner);
    }
}