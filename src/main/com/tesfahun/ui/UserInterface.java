package main.com.tesfahun.ui;

import main.com.tesfahun.models.Receipt;
import main.com.tesfahun.models.OrderItem;
import main.com.tesfahun.models.SignaturePlatter;


import java.util.Scanner;

public class UserInterface {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
public void start() {
    while (running) {
        System.out.println("\n\u001B[36m=== 🥪 Welcome to Habesha Cuisine! ===\u001B[0m");
//        Homescreen for my App
        System.out.println("1) 🧾 New Order");
        System.out.println("0) ❌ Exit");
        System.out.print("👉 Select an option: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1" -> {
                Receipt receipt = new Receipt();

                // Dine-in or Takeout
                System.out.println("1) 🍽️ Dine-in\n2) 🛍️ Takeout");
                System.out.print("👉 Enter your choice: ");
                receipt.setOrderType(scanner.nextLine());

                boolean ordering = true;
                while (ordering) {
                    // Receipt menu
                    System.out.println("\n🍽️ Order Menu:");
                    System.out.println("1) 🥪 Add Custom CuisinePlatter");
                    System.out.println("2) 🥤 Add Drink");
                    System.out.println("3) 🍟 Add Appetizers");
                    System.out.println("4) ⭐ Add Signature Platter");
                    System.out.println("5) 🔍 View Order");
                    System.out.println("6) ✅ Checkout");
                    System.out.println("0) ❌ Cancel Order");
                    System.out.print("👉 Select an option: ");
                    String orderChoice = scanner.nextLine();

                    switch (orderChoice) {
                        case "1" -> receipt.addItem(OrderItem.createSandwich(scanner));
                        case "2" -> receipt.addItem(OrderItem.createDrink(scanner));
                        case "3" -> receipt.addItem(OrderItem.createAppetizers(scanner));

                        case "4" -> {
                            System.out.println("⭐ Signature Platter:");
                            System.out.println("1) Veggie Combo (Misir, Gomen, Key siir, Shiro Wot, Alter)\n2) Meat Combo (Kitfo, Tibs, Key wot, Gomen besiga)\n3) Habesha Special (Gored, Awaze Tibs, Bozena Shiro, Minchet, Beef Alicha)");
                            System.out.print("Choose (1–3): ");
                            String choiceSig = scanner.nextLine();

                            SignaturePlatter sig = switch (choiceSig) {
                                case "1" -> new SignaturePlatter("veggie combo");
                                case "2" -> new SignaturePlatter("meat combo");
                                case "3" -> new SignaturePlatter("habesha special");
                                default -> null;
                            };

                            if (sig != null) {
                                receipt.addItem(sig);
                                System.out.println("✅ Signature Platter \"" + sig.getDisplayName() + "\" added.");
                            } else {
                                System.out.println("❌ Invalid choice.");
                            }
                        }
                        case "5" -> {
                            if (receipt.getSummary().isEmpty()) System.out.println("You haven't ordered anything yet 🛍️. Please add an item ➕.");
                            else {
                                System.out.println(receipt.getSummary());
                            }
                        }

                        case "6" -> {
                            // Show summary before tip
                            System.out.println(receipt.getSummary());

                            // 💰 Tip selection
                            System.out.println("💰 Would you like to leave a tip?");
                            System.out.println("1) 10%  2) 15%  3) 20%  4) Custom Amount  5) No Tip");
                            System.out.print("👉 Choose an option: ");
                            String tipChoice = scanner.nextLine();

                            double subtotal = receipt.getSubtotal();
                            switch (tipChoice) {
                                case "1" -> receipt.setTip(subtotal * 0.10);
                                case "2" -> receipt.setTip(subtotal * 0.15);
                                case "3" -> receipt.setTip(subtotal * 0.20);
                                case "4" -> {
                                    System.out.print("Enter custom tip amount: ");
                                    try {
                                        double tip = Double.parseDouble(scanner.nextLine());
                                        receipt.setTip(tip);
                                    } catch (NumberFormatException e) {
                                        System.out.println("❌ Invalid tip entered. No tip added.");
                                    }
                                }
                                case "5" -> receipt.setTip(0.0);
                                default -> System.out.println("❌ Invalid choice. No tip added.");
                            }

                            // Final summary
                            System.out.println(receipt.getSummary());

                            // Confirm receipt
                            System.out.print("🧾 Confirm receipt? (yes/no): ");
                            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                                receipt.saveReceipt();
                                System.out.println("✅ Receipt placed successfully🎉! Thank you for your receipt!");
                            } else {
                                System.out.println("🛑 Receipt not confirmed.");
                            }

                            ordering = false;
                        }

                        case "0" -> {
                            System.out.println("🗑️ Receipt canceled.");
                            ordering = false;
                        }

                        default -> System.out.println("❌ Invalid input. Please choose from the menu.");
                    }
                }
            }

            case "0" -> {
                System.out.println("👋 Thank you for visiting Habesha Cuisine!");
                running = false;
            }

            default -> System.out.println("❌ Invalid input. Try again.");
        }
    }
    scanner.close();
}

}