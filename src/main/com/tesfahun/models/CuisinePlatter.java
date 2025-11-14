package main.com.tesfahun.models;

import main.com.tesfahun.ui.MenuProduct;

import java.util.List;


// This class represents a CuisinePlatter object that includes size, injera, toppings, and toasted status
public class CuisinePlatter extends MenuProduct {
    private String size;
    private Injera injera;               // Injera type chosen by the customer
    private List<Topping> toppings;    // List of selected toppings (meats, cheese, veggies, sauces)
    private boolean toasted;           // Whether the sandwich is toasted

    // Constructor initializes sandwich details
    public CuisinePlatter(String size, Injera injera, List<Topping> toppings, boolean toasted) {
        super(size + "\" " + injera.getDescription());
        this.size = size;
        this.injera = injera;
        this.toppings = toppings;
        this.toasted = toasted;
    }

    // Calculates the total price of the sandwich by adding up injera and toppings
    @Override
    public double getPrice() {
        double total = injera.getPrice();
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