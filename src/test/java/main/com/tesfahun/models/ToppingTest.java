package test.java.main.com.tesfahun.models;

import main.com.tesfahun.models.Topping;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ToppingTest {

    @Test
    public void testGetPriceRegular() {
        // Arrange
        Topping topping = new Topping("Lettuce", 1.50, false);

        // Act
        double price = topping.getPrice();

        // Assert
        assertEquals(1.50, price);
    }

    @Test
    public void testGetPriceExtra() {
        // Arrange
        Topping topping = new Topping("Cheese", 2.00, true);

        // Act
        double price = topping.getPrice();

        // Assert
        assertEquals(4.00, price); // 2.00 * 2
    }

    @Test
    public void testGetDescriptionRegular() {
        // Arrange
        Topping topping = new Topping("Tomato", 1.00, false);

        // Act
        String description = topping.getDescription();

        // Assert
        assertEquals("Tomato", description);
    }

    @Test
    public void testGetDescriptionExtra() {
        // Arrange
        Topping topping = new Topping("Bacon", 3.00, true);

        // Act
        String description = topping.getDescription();

        // Assert
        assertEquals("Bacon (extra)", description);
    }
}
