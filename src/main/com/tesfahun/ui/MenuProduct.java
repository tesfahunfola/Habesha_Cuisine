package main.com.tesfahun.ui;
import main.com.tesfahun.models.*;

// This abstract class is the foundation for all menu items (like Sandwiches, Drinks, Chips, etc.)
// This abstract class is the foundation for all menu items (like Sandwiches, Drinks, Chips, etc.)
public abstract class MenuProduct {
    protected String name; // Every product has a name, like "Small Cola" or "Cheddar Cheese"

    // Constructor to set the name
    public MenuProduct(String name) {
        this.name = name;
    }

    // This method will return the price of the product (implemented by subclasses)
    public abstract double getPrice();

    // This method will describe the product in a user-friendly way (also implemented by subclasses)
    public abstract String getDescription();
}


