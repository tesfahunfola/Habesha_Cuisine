package main.com.tesfahun.models;

import main.com.tesfahun.ui.MenuProduct;
import main.com.tesfahun.util.Orderable;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

// This class handles an entire customer order, including tip, receipt saving, and price calculation
public class Order implements Orderable {
    private static final double TAX_RATE = 0.10;  // 10% tax
    private List<MenuProduct> items = new ArrayList<>();  // All items in the order (sandwiches, appetizer, drinks)
    private String orderType = "Dine-in"; // Either "Dine-in" or "Takeout"
    private double tip = 0.0;  // Customer tip amount

    // Set order type based on user input
    public void setOrderType(String type) {
        if (type.equals("1")) this.orderType = "Dine-in";
        else if (type.equals("2")) this.orderType = "Takeout";
    }

    // Add a product (sandwich, drink, appetizer, etc.) to the order
    public void addItem(MenuProduct item) {
        if (item != null) items.add(item);
    }

    // Record the tip given by the user
    public void setTip(double tip) {
        this.tip = Math.max(tip, 0);  // Avoid negative tips
    }

    // Subtotal before tax or tip
    public double getSubtotal() {
        return items.stream().mapToDouble(MenuProduct::getPrice).sum();
    }

    // Tax calculated based on subtotal
    public double getTax() {
        return getSubtotal() * TAX_RATE;
    }

    // Final total including tax and tip
    public double getTotal() {
        return getSubtotal() + getTax() + tip;
    }

    // This builds a human-readable summary of the full order
    public String getSummary() {
        StringBuilder sb = new StringBuilder();

        sb.append("╔════════════════════════════════════╗\n");
        sb.append("║        🎀  🧾 ORDER RECEIPT 🧾  🎀║\n");
        sb.append("╚════════════════════════════════════╝\n\n");

        sb.append("Order Type: ").append(orderType).append("\n");
        sb.append("Time: ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\n\n");

        sb.append("🍽️  Items Ordered:\n");
        for (MenuProduct item : items) {
            sb.append("  • ").append(item.getDescription())
                    .append("  [$").append(String.format("%.2f", item.getPrice())).append("]\n");
        }

        sb.append("\n💵 Subtotal: $").append(String.format("%.2f", getSubtotal())).append("\n");
        sb.append("💰 Tax (10%): $").append(String.format("%.2f", getTax())).append("\n");

        if (tip > 0) {
            sb.append("💖 Tip: $").append(String.format("%.2f", tip)).append("\n");
        }

        sb.append("──────────────────────────────────────\n");
        sb.append("🧮 Total: $").append(String.format("%.2f", getTotal())).append("\n");
        sb.append("──────────────────────────────────────\n");
        sb.append("⏰ Estimated Wait Time: 10–15 minutes\n");

        return sb.toString();
    }

    // This method writes the receipt to a text file in a "receipts" folder
    public void saveReceipt() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        Path dir = Paths.get("receipts");
        Path file = dir.resolve("receipt-" + timestamp + ".txt");

        try {
            Files.createDirectories(dir);
            Files.write(file, getSummary().getBytes());
            System.out.println("✅ Receipt saved to: " + file);
        } catch (IOException e) {
            System.out.println("❌ Error saving receipt: " + e.getMessage());
        }
    }



    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public double getPrice() {
        return 0;
    }
}
