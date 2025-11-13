package main.com.tesfahun.ui;

import main.com.tesfahun.models.Order;
import main.com.tesfahun.models.OrderItem;
import main.com.tesfahun.models.SignatureSandwich;


import java.util.Scanner;

public class MainApp {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
public void start() {
    while (running) {
        System.out.println("\n\u001B[36m=== 🥪 Welcome to DELI-cious! ===\u001B[0m");
        System.out.println("1) 🧾 New Order");
        System.out.println("0) ❌ Exit");
        System.out.print("👉 Select an option: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1" -> {
                Order order = new Order();

                // Dine-in or Takeout
                System.out.println("1) 🍽️ Dine-in\n2) 🛍️ Takeout");
                System.out.print("👉 Enter your choice: ");
                order.setOrderType(scanner.nextLine());

                boolean ordering = true;
                while (ordering) {
                    // Order menu
                    System.out.println("\n🍽️ Order Menu:");
                    System.out.println("1) 🥪 Add Custom Sandwich");
                    System.out.println("2) 🥤 Add Drink");
                    System.out.println("3) 🍟 Add Chips");
                    System.out.println("4) ⭐ Add Signature Sandwich");
                    System.out.println("5) ✅ Checkout");
                    System.out.println("0) ❌ Cancel Order");
                    System.out.print("👉 Select an option: ");
                    String orderChoice = scanner.nextLine();

                    switch (orderChoice) {
                        case "1" -> order.addItem(OrderItem.createSandwich(scanner));
                        case "2" -> order.addItem(OrderItem.createDrink(scanner));
                        case "3" -> order.addItem(OrderItem.createChips(scanner));

                        case "4" -> {
                            System.out.println("⭐ Signature Sandwiches:");
                            System.out.println("1) BLT\n2) Philly Cheese\n3) Veggie Delight");
                            System.out.print("Choose (1–3): ");
                            String choiceSig = scanner.nextLine();

                            SignatureSandwich sig = switch (choiceSig) {
                                case "1" -> new SignatureSandwich("blt");
                                case "2" -> new SignatureSandwich("philly cheese");
                                case "3" -> new SignatureSandwich("veggie delight");
                                default -> null;
                            };

                            if (sig != null) {
                                order.addItem(sig);
                                System.out.println("✅ Signature sandwich \"" + sig.getDisplayName() + "\" added.");
                            } else {
                                System.out.println("❌ Invalid choice.");
                            }
                        }

                        case "5" -> {
                            // Show summary before tip
                            System.out.println(order.getSummary());

                            // 💰 Tip selection
                            System.out.println("💰 Would you like to leave a tip?");
                            System.out.println("1) 10%  2) 15%  3) 20%  4) Custom Amount  5) No Tip");
                            System.out.print("👉 Choose an option: ");
                            String tipChoice = scanner.nextLine();

                            double subtotal = order.getSubtotal();
                            switch (tipChoice) {
                                case "1" -> order.setTip(subtotal * 0.10);
                                case "2" -> order.setTip(subtotal * 0.15);
                                case "3" -> order.setTip(subtotal * 0.20);
                                case "4" -> {
                                    System.out.print("Enter custom tip amount: ");
                                    try {
                                        double tip = Double.parseDouble(scanner.nextLine());
                                        order.setTip(tip);
                                    } catch (NumberFormatException e) {
                                        System.out.println("❌ Invalid tip entered. No tip added.");
                                    }
                                }
                                case "5" -> order.setTip(0.0);
                                default -> System.out.println("❌ Invalid choice. No tip added.");
                            }

                            // Final summary
                            System.out.println(order.getSummary());

                            // Confirm order
                            System.out.print("🧾 Confirm order? (yes/no): ");
                            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                                order.saveReceipt();
                                System.out.println("✅ Order placed successfully!");
                            } else {
                                System.out.println("🛑 Order not confirmed.");
                            }

                            ordering = false;
                        }

                        case "0" -> {
                            System.out.println("🗑️ Order canceled.");
                            ordering = false;
                        }

                        default -> System.out.println("❌ Invalid input. Please choose from the menu.");
                    }
                }
            }

            case "0" -> {
                System.out.println("👋 Thank you for visiting DELI-cious!");
                running = false;
            }

            default -> System.out.println("❌ Invalid input. Try again.");
        }
    }
    scanner.close();
}

}