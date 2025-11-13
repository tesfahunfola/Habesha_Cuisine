package main.com.tesfahun.util;

import main.com.tesfahun.models.*;
import main.com.tesfahun.models.Topping;
import java.util.*;

public class PlatterBuilder {

    // Topping options organized into categories for meat, side, veggies, and sauces
    private static final Map<String, List<String>> toppingOptions = Map.of(
            "Meats", List.of("Kitfo", "Gored", "Tibs", "Sigawot", "Yetibs Firfir", "Quanta Firfir"),
            "Side", List.of("Ayeb", "Side Azefa", "Mitimita", "Kariya"),
            "Toppings", List.of("Garlic", "Onions", "Kibe(Ethio Butter)", "Jalapeños","Salad", "Tomatoes", "Ethiopian Herbs"),
            "Sauces", List.of("Berbere Sauce", "Ethiopian Mustard", "Ketchup", "Ranch")
    );

    // List of injera types a customer can choose from
    private static final List<String> injeras = List.of("Teff","White", "Gluten Free", "Wheat", "Regular");

    // This method walks the user through the full sandwich creation process
    public static CuisinePlatter build(Scanner scanner) {
        String size = chooseSize(scanner);
        Injera injera = chooseInjera(scanner, size);
        List<Topping> toppings = chooseToppings(scanner, size);
        boolean toasted = askYesNo(scanner, "Would you like your injera fresh?");
        return new CuisinePlatter(size, injera, toppings, toasted);
    }

    // Prompts the user to select the sandwich size (Half", Full", or Combo")
    private static String chooseSize(Scanner scanner) {
        System.out.println("\n===== Choose Bowl/Plate Size =====");
        System.out.println("1) Half\"   2) Full\"   3) Combo\"");
        System.out.print("Enter your choice: ");
        String input = scanner.nextLine();
        return switch (input) {
            case "1" -> "Half";
            case "2" -> "Full";
            case "3" -> "Combo";
            default -> "Full";
        };
    }

    // Shows injera options and asks the user to pick one, assigning a price based on size
    private static Injera chooseInjera(Scanner scanner, String size) {
        System.out.println("\n===== Choose Your Injera =====");
        for (int i = 0; i < injeras.size(); i++) {
            System.out.println((i + 1) + ") " + injeras.get(i));
        }

        System.out.print("Choose a number (# to skip, Z to cancel): ");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("Z")) return null;

        int index = input.equals("#") ? 0 : Integer.parseInt(input) - 1;
        double price = switch (size) {
            case "Half" -> 5.50;
            case "Full" -> 7.00;
            case "Combo" -> 8.50;
            default -> 7.00;
        };
        return new Injera(injeras.get(index), price);
    }

    // Asks the user to pick toppings by category, one at a time
    // Meats and sides will also ask if the user wants extra
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

                    // Only meats and sides ask for extra
                    if (type.equals("Meats") || type.equals("side")) {
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
            case "8" -> 1.75;
            case "12" -> 3.0;
            default -> 1.0;
        };

        return switch (type) {
            case "Meats" -> extra ? multiplier * 17.5 : multiplier * 15.0;
            case "side" -> extra ? multiplier * 0.5 : multiplier * 1.75;
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
        List<String> flavors = List.of("Perrier", "Tej (Honey Wine)", "Ethiopian Spiced Tea", "Ethiopian Beer");
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

    // Lets the user choose a appetizer flavor, or skip by entering 0
    public static Appetizers selectAppetizers(Scanner scanner) {
        List<String> appetizers = List.of("Sambusa", "Chechebsa", "Veggie rolls", "Foull", "Tomato Fitfit");
        System.out.println("\n===== Select Appetizers =====");
        for (int i = 0; i < appetizers.size(); i++) {
            System.out.println((i + 1) + ") " + appetizers.get(i));
        }

        System.out.print("Choose a number (0 to skip): ");
        String input = scanner.nextLine();
        if (input.equals("0")) return null;

        Appetizers appetizers1 = new Appetizers(appetizers.get(Integer.parseInt(input) - 1));
        System.out.println("Added: " + appetizers1.getDescription() + " [$" + String.format("%.2f", appetizers1.getPrice()) + "]");
        return appetizers1;
    }
}