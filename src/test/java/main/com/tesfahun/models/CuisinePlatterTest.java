package test.java.main.com.tesfahun.models;

import main.com.tesfahun.models.CuisinePlatter;
import main.com.tesfahun.models.Injera;
import main.com.tesfahun.models.Topping;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

public class CuisinePlatterTest {

    @Test
    public void testGetPrice() {
        // Arrange
        Injera injera = new Injera("Teff", 7.00);
        List<Topping> toppings = Arrays.asList(
            new Topping("Kitfo", 9.00, false),
            new Topping("Gomen", 7.50, false)
        );
        CuisinePlatter platter = new CuisinePlatter("Full", injera, toppings, false);

        // Act
        double price = platter.getPrice();

        // Assert
        assertEquals(23.50, price); // 7.00 + 9.00 + 7.50
    }

    @Test
    public void testGetDescription() {
        // Arrange
        Injera injera = new Injera("White", 5.50);
        List<Topping> toppings = Arrays.asList(
            new Topping("Tibs", 10.50, false)
        );
        CuisinePlatter platter = new CuisinePlatter("Half", injera, toppings, true);

        // Act
        String description = platter.getDescription();

        // Assert
        assertTrue(description.contains("Half\" White injera (toasted)"));
        assertTrue(description.contains("- Tibs [$10.50]"));
    }

    @Test
    public void testGetPriceWithExtraToppings() {
        // Arrange
        Injera injera = new Injera("Regular", 4.00);
        List<Topping> toppings = Arrays.asList(
            new Topping("Cheese", 2.00, true), // Extra topping
            new Topping("Lettuce", 1.50, false)
        );
        CuisinePlatter platter = new CuisinePlatter("Combo", injera, toppings, false);

        // Act
        double price = platter.getPrice();

        // Assert
        assertEquals(9.50, price); // 4.00 + (2.00 * 2) + 1.50
    }
}
