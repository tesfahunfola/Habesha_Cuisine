package main.com.tesfahun.ui.swing;

import main.com.tesfahun.models.*;
import main.com.tesfahun.ui.MenuProduct;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Main menu panel for ordering food items
 */
public class MenuPanel extends JPanel {
    private Order order;
    private MainFrame parentFrame;
    private JTextArea orderSummaryArea;
    private JLabel totalLabel;
    
    public MenuPanel(Order order, MainFrame parentFrame) {
        this.order = order;
        this.parentFrame = parentFrame;
        
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(255, 248, 240));
        setBorder(new EmptyBorder(15, 15, 15, 15));
        
        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(255, 248, 240));
        
        JLabel titleLabel = new JLabel("🍽️ Order Menu", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setForeground(new Color(139, 69, 19));
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        // Main content split into left (buttons) and right (order summary)
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        mainPanel.setBackground(new Color(255, 248, 240));
        
        // Left panel - menu buttons
        JPanel leftPanel = createMenuButtonsPanel();
        
        // Right panel - order summary
        JPanel rightPanel = createOrderSummaryPanel();
        
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        
        // Bottom panel - action buttons
        JPanel bottomPanel = createBottomPanel();
        
        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Creates the menu buttons panel
     */
    private JPanel createMenuButtonsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(255, 248, 240));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(139, 69, 19), 2),
            "Menu Options",
            0,
            0,
            new Font("Arial", Font.BOLD, 16),
            new Color(139, 69, 19)
        ));
        
        panel.add(Box.createVerticalStrut(15));
        panel.add(createMenuButton("🥪 Add Custom Cuisine Platter", new Color(205, 133, 63), e -> addCustomPlatter()));
        panel.add(Box.createVerticalStrut(10));
        panel.add(createMenuButton("⭐ Add Signature Platter", new Color(184, 134, 11), e -> addSignaturePlatter()));
        panel.add(Box.createVerticalStrut(10));
        panel.add(createMenuButton("🥤 Add Drink", new Color(30, 144, 255), e -> addDrink()));
        panel.add(Box.createVerticalStrut(10));
        panel.add(createMenuButton("🍟 Add Appetizers", new Color(255, 140, 0), e -> addAppetizers()));
        panel.add(Box.createVerticalStrut(15));
        
        return panel;
    }
    
    /**
     * Creates the order summary panel
     */
    private JPanel createOrderSummaryPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(new Color(255, 248, 240));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(139, 69, 19), 2),
            "Current Order",
            0,
            0,
            new Font("Arial", Font.BOLD, 16),
            new Color(139, 69, 19)
        ));
        
        orderSummaryArea = new JTextArea();
        orderSummaryArea.setEditable(false);
        orderSummaryArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        orderSummaryArea.setBackground(Color.WHITE);
        orderSummaryArea.setMargin(new Insets(10, 10, 10, 10));
        
        JScrollPane scrollPane = new JScrollPane(orderSummaryArea);
        scrollPane.setPreferredSize(new Dimension(350, 400));
        
        totalLabel = new JLabel("Total: $0.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalLabel.setForeground(new Color(139, 69, 19));
        totalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalLabel.setBorder(new EmptyBorder(10, 5, 10, 5));
        
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(totalLabel, BorderLayout.SOUTH);
        
        updateOrderSummary();
        
        return panel;
    }
    
    /**
     * Creates the bottom action panel
     */
    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel.setBackground(new Color(255, 248, 240));
        
        JButton checkoutBtn = createStyledButton("✅ Checkout", new Color(34, 139, 34));
        checkoutBtn.addActionListener(e -> checkout());
        
        JButton cancelBtn = createStyledButton("❌ Cancel Order", new Color(178, 34, 34));
        cancelBtn.addActionListener(e -> cancelOrder());
        
        panel.add(checkoutBtn);
        panel.add(cancelBtn);
        
        return panel;
    }
    
    /**
     * Creates a menu button
     */
    private JButton createMenuButton(String text, Color bgColor, java.awt.event.ActionListener listener) {
        JButton button = createStyledButton(text, bgColor);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(listener);
        return button;
    }
    
    /**
     * Creates a styled button
     */
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(200, 45));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
        
        return button;
    }
    
    /**
     * Adds a custom cuisine platter
     */
    private void addCustomPlatter() {
        CustomPlatterDialog dialog = new CustomPlatterDialog(parentFrame);
        CuisinePlatter platter = dialog.showDialog();
        if (platter != null) {
            order.addItem(platter);
            updateOrderSummary();
            JOptionPane.showMessageDialog(this, "✅ Custom platter added!", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Adds a signature platter
     */
    private void addSignaturePlatter() {
        String[] options = {
            "Veggie Combo - (Misir ✦ Gomen ✦ Key Siir ✦ Shiro Wot ✦ Ater)",
            "Meat Combo - (Kitfo ✦ Tibs ✦ Key Wot ✦ Gomen Besiga)",
            "Habesha Special - (Gored ✦ Awaze Tibs ✦ Bozena Shiro ✦ Minchet ✦ Beef Alicha)"
        };
        
        String choice = (String) JOptionPane.showInputDialog(
            this,
            "Select a Signature Platter:",
            "⭐ Signature Platters",
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );
        
        if (choice != null) {
            SignaturePlatter platter = null;
            if (choice.startsWith("Veggie")) {
                platter = new SignaturePlatter("veggie combo");
            } else if (choice.startsWith("Meat")) {
                platter = new SignaturePlatter("meat combo");
            } else if (choice.startsWith("Habesha")) {
                platter = new SignaturePlatter("habesha special");
            }
            
            if (platter != null) {
                order.addItem(platter);
                updateOrderSummary();
                JOptionPane.showMessageDialog(this, "✅ Signature platter added!", 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    /**
     * Adds a drink
     */
    private void addDrink() {
        DrinkDialog dialog = new DrinkDialog(parentFrame);
        Drink drink = dialog.showDialog();
        if (drink != null) {
            order.addItem(drink);
            updateOrderSummary();
            JOptionPane.showMessageDialog(this, "✅ Drink added!", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Adds appetizers
     */
    private void addAppetizers() {
        String[] options = {"Sambusa", "Timatim", "Azifa", "Kitfo Bread"};
        
        String choice = (String) JOptionPane.showInputDialog(
            this,
            "Select an Appetizer:",
            "🍟 Appetizers",
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );
        
        if (choice != null) {
            Appetizers appetizer = new Appetizers(choice);
            order.addItem(appetizer);
            updateOrderSummary();
            JOptionPane.showMessageDialog(this, "✅ Appetizer added!", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Updates the order summary display
     */
    private void updateOrderSummary() {
        if (order.getSummary().isEmpty()) {
            orderSummaryArea.setText("No items in order yet.\n\nPlease add items from the menu.");
            totalLabel.setText("Total: $0.00");
        } else {
            orderSummaryArea.setText(order.getSummary());
            totalLabel.setText(String.format("Total: $%.2f", order.getTotal()));
        }
    }
    
    /**
     * Proceeds to checkout
     */
    private void checkout() {
        if (order.getSummary().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "You haven't ordered anything yet!\nPlease add items to your order.", 
                "Empty Order", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Show tip selection dialog
        String[] tipOptions = {"10%", "15%", "20%", "Custom Amount", "No Tip"};
        String tipChoice = (String) JOptionPane.showInputDialog(
            this,
            "💰 Would you like to leave a tip?",
            "Tip Selection",
            JOptionPane.QUESTION_MESSAGE,
            null,
            tipOptions,
            tipOptions[1]
        );
        
        if (tipChoice != null) {
            double subtotal = order.getSubtotal();
            switch (tipChoice) {
                case "10%" -> order.setTip(subtotal * 0.10);
                case "15%" -> order.setTip(subtotal * 0.15);
                case "20%" -> order.setTip(subtotal * 0.20);
                case "Custom Amount" -> {
                    String input = JOptionPane.showInputDialog(this, "Enter custom tip amount:");
                    if (input != null) {
                        try {
                            double tip = Double.parseDouble(input);
                            order.setTip(tip);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(this, "Invalid tip amount. No tip added.", 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                case "No Tip" -> order.setTip(0.0);
            }
            
            // Update summary with tip
            updateOrderSummary();
            
            // Save receipt and show confirmation
            order.saveReceipt();
            
            JOptionPane.showMessageDialog(this, 
                order.getSummary() + "\n✅ Order saved successfully!\n\n⏰ Estimated Wait Time: 10-15 minutes", 
                "Order Confirmed", JOptionPane.INFORMATION_MESSAGE);
            
            // Return to welcome screen
            parentFrame.returnToWelcome();
        }
    }
    
    /**
     * Cancels the current order
     */
    private void cancelOrder() {
        int choice = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to cancel this order?", 
            "Cancel Order", 
            JOptionPane.YES_NO_OPTION);
        
        if (choice == JOptionPane.YES_OPTION) {
            parentFrame.returnToWelcome();
        }
    }
}
