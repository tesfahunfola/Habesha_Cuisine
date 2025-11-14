package main.com.tesfahun.models;

import main.com.tesfahun.ui.MenuProduct;
import main.com.tesfahun.util.Orderable;


// Drink class represents a drink item in the order
public class Drink extends MenuProduct implements Orderable {
    private String size;  // small, medium, large

    // Constructor to set the drink size and flavor
    public Drink(String size, String flavor) {
        super(size + " " + flavor); // Name will look like "Medium perrier"
        this.size = size.toLowerCase(); // Normalize for pricing logic
    }

    // Returns the price based on drink size
    @Override
    public double getPrice() {
        return switch (size) {
            case "small" -> 2.00;
            case "medium" -> 2.50;
            case "large" -> 3.00;
            default -> 2.00;
        };
    }

    // Returns description, used in receipt
    @Override
    public String getDescription() {
        return name;
    }
}