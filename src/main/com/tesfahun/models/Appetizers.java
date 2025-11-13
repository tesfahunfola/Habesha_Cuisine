package main.com.tesfahun.models;

import main.com.tesfahun.ui.MenuProduct;
import main.com.tesfahun.util.Orderable;

// Appetizers class represents a appetizer item in the order
// Appetizers class represents a appetizer item in the order
public class Appetizers extends MenuProduct implements Orderable {

    // Constructor takes the flavor
    public Appetizers(String flavor) {
        super(flavor); // Name is just the appetizer flavor
    }

    // Returns the fixed price of appetizer
    @Override
    public double getPrice() {
        return 5.50;
    }

    // Description used in the order summary and receipt
    @Override
    public String getDescription() {
        return "Appetizers - " + name;
    }
}