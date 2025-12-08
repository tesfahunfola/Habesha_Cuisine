package main.com.tesfahun.ui.swing;

import main.com.tesfahun.models.Drink;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Dialog for selecting a drink
 */
public class DrinkDialog extends JDialog {
    private Drink drink;
    private boolean confirmed = false;
    
    private ButtonGroup sizeGroup;
    private ButtonGroup flavorGroup;
    
    private static final String[] FLAVORS = {
        "Perrier (Sparkling Water)",
        "Cola",
        "Sprite",
        "Orange Juice",
        "Mango Juice",
        "Ethiopian Coffee",
        "Ethiopian Tea (Shai)",
        "Tej (Honey Wine)",
        "Tella (Traditional Beer)"
    };
    
    public DrinkDialog(JFrame parent) {
        super(parent, "🥤 Select Drink", true);
        setSize(450, 500);
        setLocationRelativeTo(parent);
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(255, 248, 240));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Header
        JLabel titleLabel = new JLabel("Choose Your Drink", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(new Color(139, 69, 19));
        
        // Content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(255, 248, 240));
        
        // Size selection
        contentPanel.add(createSizePanel());
        contentPanel.add(Box.createVerticalStrut(15));
        
        // Flavor selection
        contentPanel.add(createFlavorPanel());
        
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);
        
        // Bottom buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(new Color(255, 248, 240));
        
        JButton addButton = createStyledButton("✅ Add to Order", new Color(34, 139, 34));
        addButton.addActionListener(e -> {
            if (createDrink()) {
                confirmed = true;
                dispose();
            }
        });
        
        JButton cancelButton = createStyledButton("❌ Cancel", new Color(178, 34, 34));
        cancelButton.addActionListener(e -> dispose());
        
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);
        
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    /**
     * Creates the size selection panel
     */
    private JPanel createSizePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(255, 248, 240));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(139, 69, 19)),
            "1️⃣ Select Size",
            0, 0, new Font("Arial", Font.BOLD, 14),
            new Color(139, 69, 19)
        ));
        
        sizeGroup = new ButtonGroup();
        
        JRadioButton smallBtn = new JRadioButton("Small - $2.00");
        JRadioButton mediumBtn = new JRadioButton("Medium - $2.50");
        JRadioButton largeBtn = new JRadioButton("Large - $3.00");
        
        smallBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        mediumBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        largeBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        
        smallBtn.setBackground(new Color(255, 248, 240));
        mediumBtn.setBackground(new Color(255, 248, 240));
        largeBtn.setBackground(new Color(255, 248, 240));
        
        sizeGroup.add(smallBtn);
        sizeGroup.add(mediumBtn);
        sizeGroup.add(largeBtn);
        
        mediumBtn.setSelected(true);
        
        panel.add(smallBtn);
        panel.add(mediumBtn);
        panel.add(largeBtn);
        
        return panel;
    }
    
    /**
     * Creates the flavor selection panel
     */
    private JPanel createFlavorPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(255, 248, 240));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(139, 69, 19)),
            "2️⃣ Select Flavor",
            0, 0, new Font("Arial", Font.BOLD, 14),
            new Color(139, 69, 19)
        ));
        
        flavorGroup = new ButtonGroup();
        
        for (String flavor : FLAVORS) {
            JRadioButton flavorBtn = new JRadioButton(flavor);
            flavorBtn.setFont(new Font("Arial", Font.PLAIN, 12));
            flavorBtn.setBackground(new Color(255, 248, 240));
            flavorGroup.add(flavorBtn);
            panel.add(flavorBtn);
        }
        
        // Select first flavor by default
        ((JRadioButton) panel.getComponent(0)).setSelected(true);
        
        return panel;
    }
    
    /**
     * Creates the drink based on selections
     */
    private boolean createDrink() {
        // Get size
        String size = "medium";
        Component[] sizeComponents = ((JPanel)((JComponent)sizeGroup.getElements().nextElement()).getParent()).getComponents();
        for (Component comp : sizeComponents) {
            if (comp instanceof JRadioButton) {
                JRadioButton rb = (JRadioButton) comp;
                if (rb.isSelected()) {
                    String text = rb.getText().toLowerCase();
                    if (text.contains("small")) {
                        size = "small";
                    } else if (text.contains("large")) {
                        size = "large";
                    }
                    break;
                }
            }
        }
        
        // Get flavor
        String flavor = "Perrier";
        Component[] flavorComponents = ((JPanel)((JComponent)flavorGroup.getElements().nextElement()).getParent()).getComponents();
        for (Component comp : flavorComponents) {
            if (comp instanceof JRadioButton) {
                JRadioButton rb = (JRadioButton) comp;
                if (rb.isSelected()) {
                    flavor = rb.getText();
                    break;
                }
            }
        }
        
        drink = new Drink(size, flavor);
        return true;
    }
    
    /**
     * Shows the dialog and returns the selected drink
     */
    public Drink showDialog() {
        setVisible(true);
        return confirmed ? drink : null;
    }
    
    /**
     * Creates a styled button
     */
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(150, 40));
        
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
}
