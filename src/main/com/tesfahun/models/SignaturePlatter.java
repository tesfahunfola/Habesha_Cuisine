package main.com.tesfahun.models;

import java.util.ArrayList;
import java.util.List;


// SignaturePlatter extends CuisinePlatter and defines preset platter with fixed toppings
public class SignaturePlatter extends CuisinePlatter {

    // Constructor takes the type of signature platter (e.g. "veggie combo", "meat combo")
    public SignaturePlatter(String type) {
        // Always small" White injera for signature platter
        super("Full", getBread(), getToppings(type), isToasted(type));
    }

    // Always uses regular injera for signature platter
    private static Injera getBread() {
        return new Injera("regular", 7.00); // full" regular injera price
    }

    // Determines whether the injera is toasted based on its type
    private static boolean isToasted(String type) {
        return switch (type.toLowerCase()) {
            case "veggie combo", "meat combo" -> true;
            default -> false;
        };
    }

    // Returns a preset list of toppings depending on the  platter type
    private static List<Topping> getToppings(String type) {
        List<Topping> toppings = new ArrayList<>();
        switch (type.toLowerCase()) {
            case "veggie combo" -> {
                toppings.add(new Topping("Misir", 8.00, false));
                toppings.add(new Topping("Gomen", 7.50, false));
                toppings.add(new Topping("Key Siir", 3.00, false));
                toppings.add(new Topping("Shiro wot", 5.00, false));
                toppings.add(new Topping("Ater", 5.00, false));
            }
            case "meat combo" -> {
                toppings.add(new Topping("Kitfo", 9.00, false));
                toppings.add(new Topping("Tibs", 10.50, false));
                toppings.add(new Topping("Key Wot", 7.50, false));
                toppings.add(new Topping("Gomen Besiga", 6.00, false));
            }
            case "habesha special" -> {
                toppings.add(new Topping("Gored", 10.00, false));
                toppings.add(new Topping("Awaze Tibs", 8.00, false));
                toppings.add(new Topping("Bozena Shiro", 11.00, false));
                toppings.add(new Topping("Minchet", 9.00, false));
                toppings.add(new Topping("Beef Alicha", 8.00, false));
            }
        }
        return toppings;
    }

    // ✅ Used for display confirmation in MainApp
    public String getDisplayName() {
        String desc = this.getDescription().toLowerCase();
        if (desc.contains("misir") && desc.contains("ater")) {
            return "Veggie Combo";
        } else if (desc.contains("kitfo") && desc.contains("tibs")) {
            return "Meat Combo";
        } else if (desc.contains("gored") && desc.contains("minchet")) {
            return "Habesha Special";
        } else {
            return "Custom Signature CuisinePlatter";
        }
    }
}
