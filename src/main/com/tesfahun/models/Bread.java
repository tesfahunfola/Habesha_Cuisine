package main.com.tesfahun.models;

import main.com.tesfahun.ui.MenuProduct;

// Bread class represents the bread type chosen for the sandwich

public class Bread extends MenuProduct {
    private double price; // Holds the price for this bread based on sandwich size

    // Constructor sets the bread name and price
    public Bread(String name, double price) {
        super(name);
        this.price = price;
    }

    // This method returns the price of the bread
    @Override
    public double getPrice() {
        return price;
    }

    // This method gives a nice description like "Wheat bread"
    @Override
    public String getDescription() {
        return name + " bread";
    }
}

