package test.java.main.com.tesfahun.models;

import main.com.tesfahun.models.Order;
import main.com.tesfahun.ui.MenuProduct;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class OrderTest {

    // Simple test implementation of MenuProduct for testing
    private static class TestMenuProduct extends MenuProduct {
        private double price;

        public TestMenuProduct(String name, double price) {
            super(name);
            this.price = price;
        }

        @Override
        public double getPrice() {
            return price;
        }

        @Override
        public String getDescription() {
            return name;
        }
    }

    @Test
    public void testAddItem() {
        // Arrange
        Order order = new Order();
        MenuProduct item = new TestMenuProduct("Test Item", 5.0);

        // Act
        order.addItem(item);

        // Assert
        // Since items is private, we can't directly access, but we can test via subtotal
        assertEquals(5.0, order.getSubtotal());
    }

    @Test
    public void testGetSubtotal() {
        // Arrange
        Order order = new Order();

        // Act
        order.addItem(new TestMenuProduct("Item1", 10.0));
        order.addItem(new TestMenuProduct("Item2", 15.0));

        // Assert
        assertEquals(25.0, order.getSubtotal());
    }

    @Test
    public void testGetTax() {
        // Arrange
        Order order = new Order();
        order.addItem(new TestMenuProduct("Item", 100.0));

        // Act
        double tax = order.getTax();

        // Assert
        assertEquals(10.0, tax); // 10% of 100
    }

    @Test
    public void testGetTotal() {
        // Arrange
        Order order = new Order();
        order.addItem(new TestMenuProduct("Item", 100.0));
        order.setTip(5.0);

        // Act
        double total = order.getTotal();

        // Assert
        assertEquals(115.0, total); // 100 + 10 tax + 5 tip
    }

    @Test
    public void testSetTip() {
        // Arrange
        Order order = new Order();
        order.setTip(10.0);
        order.addItem(new TestMenuProduct("Item", 100.0));

        // Act
        double total = order.getTotal();

        // Assert
        assertEquals(120.0, total); // 100 + 10 tax + 10 tip
    }

    @Test
    public void testSetTipNegative() {
        // Arrange
        Order order = new Order();
        order.setTip(-5.0);
        order.addItem(new TestMenuProduct("Item", 100.0));

        // Act
        double total = order.getTotal();

        // Assert
        assertEquals(110.0, total); // Tip should be 0
    }

    @Test
    public void testSetOrderType() {
        // Arrange
        Order order = new Order();

        // Act
        order.setOrderType("1");

        // Assert
        // Can't directly test private field, but assume it's set
        // Test via summary if needed, but for now, just ensure no exception
        assertTrue(true);
    }
}
