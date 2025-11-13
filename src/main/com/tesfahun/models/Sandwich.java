package main.com.tesfahun.models;

import main.com.tesfahun.ui.MenuProduct;

import java.util.List;

// This class represents a Sandwich object that includes size, bread, toppings, and toasted status
import java.util.List;

// This class represents a Sandwich object that includes size, bread, toppings, and toasted status
public class Sandwich extends MenuProduct {
    private String size;                // 4", 8", or 12"
    private Bread bread;               // Bread type chosen by the customer
    private List<Topping> toppings;    // List of selected toppings (meats, cheese, veggies, sauces)
    private boolean toasted;           // Whether the sandwich is toasted

    // Constructor initializes sandwich details
    public Sandwich(String size, Bread bread, List<Topping> toppings, boolean toasted) {
        super(size + "\" " + bread.getDescription());
        this.size = size;
        this.bread = bread;
        this.toppings = toppings;
        this.toasted = toasted;
    }

    // Calculates the total price of the sandwich by adding up bread and toppings
    @Override
    public double getPrice() {
        double total = bread.getPrice();
        for (Topping topping : toppings) {
            total += topping.getPrice();
        }
        return total;
    }

    // Builds a detailed description of the sandwich for receipts
    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder(name + (toasted ? " (toasted)" : "") + "\n");
        for (Topping topping : toppings) {
            sb.append("  - ").append(topping.getDescription()).append(" [$")
                    .append(String.format("%.2f", topping.getPrice())).append("]\n");
        }
        return sb.toString();
    }
}