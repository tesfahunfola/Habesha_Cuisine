package test.java.main.com.tesfahun.models;

import main.com.tesfahun.models.Injera;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InjeraTest {

    @Test
    public void testGetPrice() {
        // Arrange
        Injera injera = new Injera("Teff", 7.00);
        Injera injera1 = new Injera("white",9.0);

        // Act
        double price = injera.getPrice();
        double price1 = injera1.getPrice();

        // Assert
        assertEquals(7.00, price);
        assertEquals(9.0, price1);
    }

    @Test
    public void testGetDescription() {
        // Arrange
        Injera injera = new Injera("White", 5.50);

        // Act
        String description = injera.getDescription();

        // Assert
        assertEquals("White injera", description);
    }
}
