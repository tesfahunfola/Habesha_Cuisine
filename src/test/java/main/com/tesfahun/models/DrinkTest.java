package test.java.main.com.tesfahun.models;

import main.com.tesfahun.models.Drink;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DrinkTest {

    @Test
    public void testGetPriceSmall() {
        // Arrange
        Drink drink = new Drink("small", "Cola");

        // Act
        double price = drink.getPrice();

        // Assert
        assertEquals(2.00, price);
    }

    @Test
    public void testGetPriceMedium() {
        // Arrange
        Drink drink = new Drink("medium", "Cola");

        // Act
        double price = drink.getPrice();

        // Assert
        assertEquals(2.50, price);
    }

    @Test
    public void testGetPriceLarge() {
        // Arrange
        Drink drink = new Drink("large", "Cola");

        // Act
        double price = drink.getPrice();

        // Assert
        assertEquals(3.00, price);
    }

    @Test
    public void testGetPriceDefault() {
        // Arrange
        Drink drink = new Drink("unknown", "Cola");

        // Act
        double price = drink.getPrice();

        // Assert
        assertEquals(2.00, price); // Default to small
    }

    @Test
    public void testGetDescription() {
        // Arrange
        Drink drink = new Drink("medium", "Cola");

        // Act
        String description = drink.getDescription();

        // Assert
        assertEquals("medium Cola", description);
    }
}
