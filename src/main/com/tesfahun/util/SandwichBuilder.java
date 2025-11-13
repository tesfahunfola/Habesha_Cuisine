package main.com.tesfahun.util;

import main.com.tesfahun.models.*;
import main.com.tesfahun.models.Topping;
import java.util.*;

public class SandwichBuilder {

    // Topping options organized into categories for meat, cheese, veggies, and sauces
    private static final Map<String, List<String>> toppingOptions = Map.of(
            "Meats", List.of("Steak", "Ham", "Salami", "Roast Beef", "Chicken", "Bacon"),
            "Cheese", List.of("American", "Provolone", "Cheddar", "Swiss"),
            "Toppings", List.of("Lettuce", "Peppers", "Onions", "Tomatoes", "Jalapeños", "Cucumbers", "Pickles", "Guacamole", "Mushrooms"),
            "Sauces", List.of("Mayo", "Mustard", "Ketchup", "Ranch", "Thousand Islands", "Vinaigrette")
    );

    // List of bread types a customer can choose from
    private static final List<String> breads = List.of("White", "Wheat", "Rye", "Wrap", "Ciabatta");

    // This method walks the user through the full sandwich creation process
    public static Sandwich build(Scanner scanner) {
        String size = chooseSize(scanner);
        Bread bread = chooseBread(scanner, size);
        List<Topping> toppings = chooseToppings(scanner, size);
        boolean toasted = askYesNo(scanner, "Would you like your bread toasted?");
        return new Sandwich(size, bread, toppings, toasted);
    }

    // Prompts the user to select the sandwich size (4", 8", or 12")
    private static String chooseSize(Scanner scanner) {
        System.out.println("\n===== Choose Sandwich Size =====");
        System.out.println("1) 4\"   2) 8\"   3) 12\"");
        System.out.print("Enter your choice: ");
        String input = scanner.nextLine();
        return switch (input) {
            case "1" -> "4";
            case "2" -> "8";
            case "3" -> "12";
            default -> "8";
        };
    }

    // Shows bread options and asks the user to pick one, assigning a price based on size
    private static Bread chooseBread(Scanner scanner, String size) {
        System.out.println("\n===== Choose Your Bread =====");
        for (int i = 0; i < breads.size(); i++) {
            System.out.println((i + 1) + ") " + breads.get(i));
        }

        System.out.print("Choose a number (# to skip, Z to cancel): ");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("Z")) return null;

        int index = input.equals("#") ? 0 : Integer.parseInt(input) - 1;
        double price = switch (size) {
            case "4" -> 5.50;
            case "8" -> 7.00;
            case "12" -> 8.50;
            default -> 7.00;
        };
        return new Bread(breads.get(index), price);
    }

    // Asks the user to pick toppings by category, one at a time
    // Meats and cheeses will also ask if the user wants extra
    private static List<Topping> chooseToppings(Scanner scanner, String size) {
        List<Topping> all = new ArrayList<>();

        for (Map.Entry<String, List<String>> category : toppingOptions.entrySet()) {
            String type = category.getKey();
            List<String> items = category.getValue();

            System.out.println("\nAvailable: " + type.toUpperCase());
            for (int i = 0; i < items.size(); i++) {
                System.out.printf("%d) %s%n", i + 1, items.get(i));
            }

            while (true) {
                System.out.print("Choose a number (# to skip, Z to cancel): ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("Z") || input.equalsIgnoreCase("#")) break;

                try {
                    int index = Integer.parseInt(input) - 1;
                    if (index < 0 || index >= items.size()) {
                        System.out.println("Invalid choice.");
                        continue;
                    }

                    String selected = items.get(index);
                    boolean isExtra = false;

                    // Only meats and cheeses ask for extra
                    if (type.equals("Meats") || type.equals("Cheese")) {
                        System.out.print("Would you like extra " + selected + "? (yes/no): ");
                        isExtra = scanner.nextLine().equalsIgnoreCase("yes");
                    }

                    double price = getPriceByType(type, size, isExtra);
                    Topping topping = new Topping(selected, price, isExtra);
                    all.add(topping);

                    System.out.printf("Added: %s%s [$%.2f]%n",
                            selected,
                            isExtra ? " (extra)" : "",
                            price
                    );

                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }
        }

        return all;
    }

    // Calculates the price of a topping based on size, type, and whether it’s extra
    private static double getPriceByType(String type, String size, boolean extra) {
        double multiplier = switch (size) {
            case "4" -> 1.0;
            case "8" -> 2.0;
            case "12" -> 3.0;
            default -> 1.0;
        };

        return switch (type) {
            case "Meats" -> extra ? multiplier * 0.5 : multiplier * 1.0;
            case "Cheese" -> extra ? multiplier * 0.3 : multiplier * 0.75;
            default -> 0.0;
        };
    }

    // A reusable yes/no prompt that returns true if the user says "yes"
    private static boolean askYesNo(Scanner scanner, String prompt) {
        System.out.print(prompt + " (yes/no): ");
        return scanner.nextLine().equalsIgnoreCase("yes");
    }

    // Lets the user choose a drink size and flavor, then confirms the selection
    public static Drink selectDrink(Scanner scanner) {
        List<String> flavors = List.of("Cola", "Lemonade", "Iced Tea", "Root Beer");
        System.out.println("\n===== Select a Drink =====");
        System.out.println("1) Small   2) Medium   3) Large");
        System.out.print("Choose size: ");
        String size = switch (scanner.nextLine()) {
            case "1" -> "small";
            case "2" -> "medium";
            case "3" -> "large";
            default -> "small";
        };

        for (int i = 0; i < flavors.size(); i++) {
            System.out.println((i + 1) + ") " + flavors.get(i));
        }

        System.out.print("Choose drink: ");
        String flavor = flavors.get(Integer.parseInt(scanner.nextLine()) - 1);
        Drink drink = new Drink(size, flavor);
        System.out.println("Added: " + drink.getDescription() + " [$" + String.format("%.2f", drink.getPrice()) + "]");
        return drink;
    }

    // Lets the user choose a chips flavor, or skip by entering 0
    public static Chips selectChips(Scanner scanner) {
        List<String> chips = List.of("BBQ", "Sour Cream", "Salt & Vinegar", "Jalapeño", "Lays Classic");
        System.out.println("\n===== Select Chips =====");
        for (int i = 0; i < chips.size(); i++) {
            System.out.println((i + 1) + ") " + chips.get(i));
        }

        System.out.print("Choose a number (0 to skip): ");
        String input = scanner.nextLine();
        if (input.equals("0")) return null;

        Chips chip = new Chips(chips.get(Integer.parseInt(input) - 1));
        System.out.println("Added: " + chip.getDescription() + " [$" + String.format("%.2f", chip.getPrice()) + "]");
        return chip;
    }
}