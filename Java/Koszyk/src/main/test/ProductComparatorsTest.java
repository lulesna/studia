import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProductComparatorsTest {
    private Product[] cart;

    @Before
    public void setUp() {
        cart = new Product[] {
                new Product("D329", "Drukarka", 200.0),
                new Product("M948", "Monitor", 449.49),
                new Product("M001", "Firmowy kubek", 0.0),
                new Product("S022", "Suszarka", 89.99),
                new Product("B079", "Baterie AA 4szt.", 19.90),
                new Product("P249", "Podkładka", 29.99),
                new Product("K151", "Klawiatura", 89.99),
                new Product("D001", "Darmowy produkt", 0.0)
        };
    }

    @Test
    public void testProductComparator() {
        ProductUtils.sortProducts(cart, new ProductComparator());

        String[] expectedOrder = {
                "Monitor",          // 449.49
                "Drukarka",         // 200.0
                "Klawiatura",       // 89.99
                "Suszarka",         // 89.99
                "Podkładka",        // 29.99
                "Baterie AA 4szt.", // 19.90
                "Darmowy produkt",  // 0.0
                "Firmowy kubek"     // 0.0
        };

        for (int i = 0; i < cart.length; i++) {
            assertEquals("Nieprawidłowa kolejność produktu na pozycji " + i,
                    expectedOrder[i], cart[i].getName());
        }
    }
}