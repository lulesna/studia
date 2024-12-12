import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProductTest {
    private Product product;

    @Before
    public void setUp() {
        product = new Product("T001", "Testowy produkt", 123.45);
    }

    @Test
    public void testProductConstructor() {
        assertEquals("T001", product.getCode());
        assertEquals("Testowy produkt", product.getName());
        assertEquals(123.45, product.getPrice(), 0.001);
        assertEquals(123.45, product.getDiscountPrice(), 0.001);
    }

    @Test
    public void testSetDiscountPrice() {
        product.setDiscountPrice(100.0);
        assertEquals(100.0, product.getDiscountPrice(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidDiscountPrice() {
        product.setDiscountPrice(-10.0);
        assertEquals("Niepoprawna cena", 123.45, product.getDiscountPrice(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidProductCode() {
        new Product("", "Testowy Produkt 2", 123.45);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidProductName() {
        new Product("T002", "", 123.45);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidProductPrice() {
        new Product("T003", "Testowy Produkt 3", -10.0);
    }
}
