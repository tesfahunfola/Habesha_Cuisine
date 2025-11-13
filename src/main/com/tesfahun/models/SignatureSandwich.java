package main.com.tesfahun.models;

import java.util.ArrayList;
import java.util.List;

// SignatureSandwich extends Sandwich and defines preset sandwiches with fixed toppings


// SignatureSandwich extends Sandwich and defines preset sandwiches with fixed toppings
public class SignatureSandwich extends Sandwich {

    // Constructor takes the type of signature sandwich (e.g. "blt", "philly cheese")
    public SignatureSandwich(String type) {
        // Always 8" White bread for signature sandwiches
        super("8", getBread(), getToppings(type), isToasted(type));
    }

    // Always uses White bread for signature sandwiches
    private static Bread getBread() {
        return new Bread("White", 7.00); // 8" white bread price
    }

    // Determines whether the sandwich is toasted based on its type
    private static boolean isToasted(String type) {
        return switch (type.toLowerCase()) {
            case "blt", "philly cheese" -> true;
            default -> false;
        };
    }

    // Returns a preset list of toppings depending on the sandwich type
    private static List<Topping> getToppings(String type) {
        List<Topping> toppings = new ArrayList<>();
        switch (type.toLowerCase()) {
            case "blt" -> {
                toppings.add(new Topping("Bacon", 2.00, false));
                toppings.add(new Topping("Cheddar", 1.50, false));
                toppings.add(new Topping("Lettuce", 0.00, false));
                toppings.add(new Topping("Tomatoes", 0.00, false));
                toppings.add(new Topping("Ranch", 0.00, false));
            }
            case "philly cheese" -> {
                toppings.add(new Topping("Steak", 2.00, false));
                toppings.add(new Topping("American", 1.50, false));
                toppings.add(new Topping("Peppers", 0.00, false));
                toppings.add(new Topping("Mayo", 0.00, false));
            }
            case "veggie delight" -> {
                toppings.add(new Topping("Lettuce", 0.00, false));
                toppings.add(new Topping("Tomatoes", 0.00, false));
                toppings.add(new Topping("Cucumbers", 0.00, false));
                toppings.add(new Topping("Pickles", 0.00, false));
                toppings.add(new Topping("Vinaigrette", 0.00, false));
            }
        }
        return toppings;
    }

    // ✅ Used for display confirmation in MainApp
    public String getDisplayName() {
        String desc = this.getDescription().toLowerCase();
        if (desc.contains("bacon") && desc.contains("cheddar")) {
            return "BLT";
        } else if (desc.contains("steak") && desc.contains("american")) {
            return "Philly Cheese";
        } else if (desc.contains("cucumbers") && desc.contains("vinaigrette")) {
            return "Veggie Delight";
        } else {
            return "Custom Signature Sandwich";
        }
    }
}
