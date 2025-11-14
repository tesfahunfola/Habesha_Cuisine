package main.com.tesfahun.models;

import main.com.tesfahun.ui.MenuProduct;

// Topping class represents any topping added to a sandwich—meats, cheeses, veggies, sauces
public class Topping extends MenuProduct {
    private double basePrice; // Base price of the topping depending on size/category
    private boolean isExtra;  // If true, it's an extra portion (charged more)

    // Constructor to set the topping name, price, and whether it's extra
    public Topping(String name, double basePrice, boolean isExtra) {
        super(name);
        this.basePrice = basePrice;
        this.isExtra = isExtra;
    }

    // Returns the actual price: extra toppings are charged more
    @Override
    public double getPrice() {
        return isExtra ? basePrice * 2 : basePrice;
    }

    // Description like "Cheddar (extra)" or "Lettuce"
    @Override
    public String getDescription() {
        return name + (isExtra ? " (extra)" : "");
    }
}
