package main.com.tesfahun.models;

import main.com.tesfahun.ui.MenuProduct;
import main.com.tesfahun.util.Orderable;

// Injera class represents the injera type chosen for the sandwich

public class Injera extends MenuProduct implements Orderable {
    private double price; // Holds the price for this injera based on sandwich size

    // Constructor sets the injera name and price
    public Injera(String name, double price) {
        super(name);
        this.price = price;
    }

    // This method returns the price of the injera
    @Override
    public double getPrice() {
        return price;
    }

    // This method gives a nice description like "Wheat injera"
    @Override
    public String getDescription() {
        return name + " injera";
    }
}

