package main.com.tesfahun.ui.swing;

import main.com.tesfahun.models.Order;

import javax.swing.*;
import java.awt.*;

/**
 * Main application window for Habesha Cuisine ordering system
 */
public class MainFrame extends JFrame {
    private Order currentOrder;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    
    // Panel names for CardLayout
    private static final String WELCOME_PANEL = "welcome";
    private static final String ORDER_TYPE_PANEL = "orderType";
    private static final String MENU_PANEL = "menu";
    
    public MainFrame() {
        setTitle("🥪 Habesha Cuisine - Order System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        
        // Initialize CardLayout for switching between panels
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        
        // Add all panels
        contentPanel.add(createWelcomePanel(), WELCOME_PANEL);
        contentPanel.add(createOrderTypePanel(), ORDER_TYPE_PANEL);
        
        add(contentPanel);
        
        // Start with welcome screen
        cardLayout.show(contentPanel, WELCOME_PANEL);
    }
    
    /**
     * Creates the welcome screen panel
     */
    private JPanel createWelcomePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 248, 240));
        
        // Header
        JLabel titleLabel = new JLabel("Welcome to Habesha Cuisine!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(new Color(139, 69, 19));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(50, 20, 30, 20));
        
        // Center content
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(255, 248, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel subtitleLabel = new JLabel("🍽️ Authentic Ethiopian Cuisine 🍽️");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        subtitleLabel.setForeground(new Color(101, 67, 33));
        centerPanel.add(subtitleLabel, gbc);
        
        JButton newOrderBtn = createStyledButton("🧾 Start New Order", new Color(34, 139, 34));
        newOrderBtn.setPreferredSize(new Dimension(250, 60));
        newOrderBtn.addActionListener(e -> {
            currentOrder = new Order();
            cardLayout.show(contentPanel, ORDER_TYPE_PANEL);
        });
        centerPanel.add(newOrderBtn, gbc);
        
        JButton exitBtn = createStyledButton("❌ Exit", new Color(178, 34, 34));
        exitBtn.setPreferredSize(new Dimension(250, 60));
        exitBtn.addActionListener(e -> System.exit(0));
        centerPanel.add(exitBtn, gbc);
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Creates the order type selection panel (Dine-in or Takeout)
     */
    private JPanel createOrderTypePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 248, 240));
        
        JLabel titleLabel = new JLabel("Select Order Type", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(139, 69, 19));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(40, 20, 30, 20));
        
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(255, 248, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(15, 10, 15, 10);
        
        JButton dineInBtn = createStyledButton("🍽️ Dine-in", new Color(70, 130, 180));
        dineInBtn.setPreferredSize(new Dimension(300, 70));
        dineInBtn.addActionListener(e -> {
            currentOrder.setOrderType("1");
            showMenuPanel();
        });
        buttonPanel.add(dineInBtn, gbc);
        
        JButton takeoutBtn = createStyledButton("🛍️ Takeout", new Color(218, 165, 32));
        takeoutBtn.setPreferredSize(new Dimension(300, 70));
        takeoutBtn.addActionListener(e -> {
            currentOrder.setOrderType("2");
            showMenuPanel();
        });
        buttonPanel.add(takeoutBtn, gbc);
        
        JButton backBtn = createStyledButton("⬅️ Back", new Color(128, 128, 128));
        backBtn.setPreferredSize(new Dimension(300, 50));
        backBtn.addActionListener(e -> cardLayout.show(contentPanel, WELCOME_PANEL));
        buttonPanel.add(backBtn, gbc);
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Shows the main menu panel
     */
    private void showMenuPanel() {
        // Remove existing menu panel if present
        Component[] components = contentPanel.getComponents();
        for (Component comp : components) {
            if (comp instanceof MenuPanel) {
                contentPanel.remove(comp);
            }
        }
        
        // Add new menu panel
        MenuPanel menuPanel = new MenuPanel(currentOrder, this);
        contentPanel.add(menuPanel, MENU_PANEL);
        cardLayout.show(contentPanel, MENU_PANEL);
    }
    
    /**
     * Returns to welcome screen
     */
    public void returnToWelcome() {
        cardLayout.show(contentPanel, WELCOME_PANEL);
    }
    
    /**
     * Creates a styled button with consistent appearance
     */
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add hover effect
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
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Use system look and feel
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
