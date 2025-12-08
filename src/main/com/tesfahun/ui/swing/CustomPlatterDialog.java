package main.com.tesfahun.ui.swing;

import main.com.tesfahun.models.CuisinePlatter;
import main.com.tesfahun.models.Injera;
import main.com.tesfahun.models.Topping;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Dialog for creating a custom cuisine platter
 */
public class CustomPlatterDialog extends JDialog {
    private CuisinePlatter platter;
    private boolean confirmed = false;
    
    private ButtonGroup sizeGroup;
    private ButtonGroup injeraGroup;
    private JCheckBox toastedCheckbox;
    private JPanel toppingsPanel;
    private List<JCheckBox> toppingCheckboxes;
    
    // Topping data: [name, veggie price, full price]
    private static final String[][] TOPPINGS = {
        {"Misir (Red Lentils)", "4.00", "8.00"},
        {"Gomen (Collard Greens)", "3.50", "7.50"},
        {"Key Siir (Beets)", "2.00", "3.00"},
        {"Shiro Wot (Chickpea Stew)", "3.00", "5.00"},
        {"Ater (Split Peas)", "3.00", "5.00"},
        {"Kitfo (Minced Beef)", "5.50", "9.00"},
        {"Tibs (Sautéed Meat)", "6.00", "10.50"},
        {"Key Wot (Spicy Beef Stew)", "4.50", "7.50"},
        {"Gomen Besiga (Collard Greens with Beef)", "4.00", "6.00"},
        {"Doro Wot (Chicken Stew)", "5.00", "8.50"},
        {"Ayib (Cottage Cheese)", "2.00", "3.50"},
        {"Tikil Gomen (Cabbage)", "2.50", "4.50"}
    };
    
    public CustomPlatterDialog(JFrame parent) {
        super(parent, "🥪 Create Custom Platter", true);
        setSize(600, 700);
        setLocationRelativeTo(parent);
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(255, 248, 240));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Header
        JLabel titleLabel = new JLabel("Build Your Custom Platter", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(new Color(139, 69, 19));
        
        // Content panel with scroll
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(255, 248, 240));
        
        // Size selection
        contentPanel.add(createSizePanel());
        contentPanel.add(Box.createVerticalStrut(15));
        
        // Injera selection
        contentPanel.add(createInjeraPanel());
        contentPanel.add(Box.createVerticalStrut(15));
        
        // Toasted option
        toastedCheckbox = new JCheckBox("🔥 Toasted (+$0.00)");
        toastedCheckbox.setFont(new Font("Arial", Font.BOLD, 14));
        toastedCheckbox.setBackground(new Color(255, 248, 240));
        toastedCheckbox.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(toastedCheckbox);
        contentPanel.add(Box.createVerticalStrut(15));
        
        // Toppings selection
        contentPanel.add(createToppingsPanel());
        
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);
        
        // Bottom buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(new Color(255, 248, 240));
        
        JButton addButton = createStyledButton("✅ Add to Order", new Color(34, 139, 34));
        addButton.addActionListener(e -> {
            if (createPlatter()) {
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
        
        JRadioButton veggieBtn = new JRadioButton("Veggie Size - $5.50 base");
        JRadioButton fullBtn = new JRadioButton("Full Size - $7.00 base");
        
        veggieBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        fullBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        veggieBtn.setBackground(new Color(255, 248, 240));
        fullBtn.setBackground(new Color(255, 248, 240));
        
        sizeGroup.add(veggieBtn);
        sizeGroup.add(fullBtn);
        
        veggieBtn.setSelected(true);
        
        panel.add(veggieBtn);
        panel.add(fullBtn);
        
        return panel;
    }
    
    /**
     * Creates the injera selection panel
     */
    private JPanel createInjeraPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(255, 248, 240));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(139, 69, 19)),
            "2️⃣ Select Injera Type",
            0, 0, new Font("Arial", Font.BOLD, 14),
            new Color(139, 69, 19)
        ));
        
        injeraGroup = new ButtonGroup();
        
        JRadioButton regularBtn = new JRadioButton("Regular Injera (Teff)");
        JRadioButton wheatBtn = new JRadioButton("Wheat Injera");
        JRadioButton whiteBtn = new JRadioButton("White Injera");
        
        regularBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        wheatBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        whiteBtn.setFont(new Font("Arial", Font.PLAIN, 13));
        
        regularBtn.setBackground(new Color(255, 248, 240));
        wheatBtn.setBackground(new Color(255, 248, 240));
        whiteBtn.setBackground(new Color(255, 248, 240));
        
        injeraGroup.add(regularBtn);
        injeraGroup.add(wheatBtn);
        injeraGroup.add(whiteBtn);
        
        regularBtn.setSelected(true);
        
        panel.add(regularBtn);
        panel.add(wheatBtn);
        panel.add(whiteBtn);
        
        return panel;
    }
    
    /**
     * Creates the toppings selection panel
     */
    private JPanel createToppingsPanel() {
        toppingsPanel = new JPanel();
        toppingsPanel.setLayout(new BoxLayout(toppingsPanel, BoxLayout.Y_AXIS));
        toppingsPanel.setBackground(new Color(255, 248, 240));
        toppingsPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(139, 69, 19)),
            "3️⃣ Select Toppings (Choose at least 3)",
            0, 0, new Font("Arial", Font.BOLD, 14),
            new Color(139, 69, 19)
        ));
        
        toppingCheckboxes = new ArrayList<>();
        
        for (String[] topping : TOPPINGS) {
            JCheckBox checkbox = new JCheckBox(topping[0] + " - $" + topping[1] + " (V) / $" + topping[2] + " (F)");
            checkbox.setFont(new Font("Arial", Font.PLAIN, 12));
            checkbox.setBackground(new Color(255, 248, 240));
            toppingCheckboxes.add(checkbox);
            toppingsPanel.add(checkbox);
        }
        
        return toppingsPanel;
    }
    
    /**
     * Creates the platter based on selections
     */
    private boolean createPlatter() {
        // Get size
        String size = "Veggie";
        double basePrice = 5.50;
        
        ButtonModel selectedSize = sizeGroup.getSelection();
        if (selectedSize != null) {
            String sizeText = selectedSize.getActionCommand();
            if (sizeText == null) {
                // Determine from buttons
                Component[] components = ((JPanel)((JComponent)sizeGroup.getElements().nextElement()).getParent()).getComponents();
                for (Component comp : components) {
                    if (comp instanceof JRadioButton) {
                        JRadioButton rb = (JRadioButton) comp;
                        if (rb.isSelected()) {
                            if (rb.getText().contains("Full")) {
                                size = "Full";
                                basePrice = 7.00;
                            }
                            break;
                        }
                    }
                }
            }
        }
        
        // Get injera type
        String injeraType = "regular";
        ButtonModel selectedInjera = injeraGroup.getSelection();
        if (selectedInjera != null) {
            Component[] components = ((JPanel)((JComponent)injeraGroup.getElements().nextElement()).getParent()).getComponents();
            for (Component comp : components) {
                if (comp instanceof JRadioButton) {
                    JRadioButton rb = (JRadioButton) comp;
                    if (rb.isSelected()) {
                        String text = rb.getText().toLowerCase();
                        if (text.contains("wheat")) {
                            injeraType = "wheat";
                        } else if (text.contains("white")) {
                            injeraType = "white";
                        }
                        break;
                    }
                }
            }
        }
        
        Injera injera = new Injera(injeraType, basePrice);
        
        // Get toppings
        List<Topping> selectedToppings = new ArrayList<>();
        boolean isFullSize = size.equals("Full");
        
        for (int i = 0; i < toppingCheckboxes.size(); i++) {
            if (toppingCheckboxes.get(i).isSelected()) {
                String toppingName = TOPPINGS[i][0];
                double price = Double.parseDouble(isFullSize ? TOPPINGS[i][2] : TOPPINGS[i][1]);
                selectedToppings.add(new Topping(toppingName, price, false));
            }
        }
        
        // Validate at least 3 toppings
        if (selectedToppings.size() < 3) {
            JOptionPane.showMessageDialog(this, 
                "Please select at least 3 toppings!", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        // Create platter
        boolean toasted = toastedCheckbox.isSelected();
        platter = new CuisinePlatter(size, injera, selectedToppings, toasted);
        
        return true;
    }
    
    /**
     * Shows the dialog and returns the created platter
     */
    public CuisinePlatter showDialog() {
        setVisible(true);
        return confirmed ? platter : null;
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
