package main.com.tesfahun.models;

import main.com.tesfahun.ui.MenuProduct;

// Chips class represents a chips item in the order
// Chips class represents a chips item in the order
public class Chips extends MenuProduct {

    // Constructor takes the flavor (e.g. "BBQ", "Sour Cream")
    public Chips(String flavor) {
        super(flavor); // Name is just the chip flavor
    }

    // Returns the fixed price of chips
    @Override
    public double getPrice() {
        return 1.50;
    }

    // Description used in the order summary and receipt
    @Override
    public String getDescription() {
        return "Chips - " + name;
    }
}