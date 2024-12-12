import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class JavaMarktTest {
    private JavaMarkt javaMarkt;
    private Product[] cart;
    private List<DiscountCommand> promotions;

    @Before
    public void setUp() {
        cart = new Product[]{
                new Product("D329", "Drukarka", 200.0),
                new Product("M948", "Monitor", 449.49),
                new Product("K150", "Klawiatura", 79.99),
                new Product("B079", "Baterie AA 4szt.", 19.90)
        };

        promotions = List.of(
                new Discount5PercentCommand(),
                new AddMugCommand(),
                new SingleProductDiscountCommand("D329"),
                new ThirdCheapestFreeCommand()
        );

        javaMarkt = new JavaMarkt(cart, promotions);
    }

    @Test
    public void testGetCart() {
        Product[] cart = javaMarkt.getCart();
        assertEquals(5, cart.length); // od razu dodany kubek
        assertEquals("M948", cart[0].getCode());
        assertEquals("D329", cart[1].getCode());
        assertEquals("K150", cart[2].getCode());
        assertEquals("B079", cart[3].getCode());
        assertEquals("M001", cart[4].getCode());
    }

    @Test
    public void testApplyBestDiscount() {
        javaMarkt.applyBestDiscount();
        Product[] updatedCart = javaMarkt.getCart();

        // Monitor - bez rabatu
        assertEquals("M948", updatedCart[0].getCode());
        assertEquals(449.49, updatedCart[0].getDiscountPrice(), 0.01);

        // Drukarka - rabat 30%
        assertEquals("D329", updatedCart[1].getCode());
        assertEquals(140.0, updatedCart[1].getDiscountPrice(), 0.01);

        // Klawiatura - bez rabatu
        assertEquals("K150", updatedCart[2].getCode());
        assertEquals(79.99, updatedCart[2].getDiscountPrice(), 0.01);

        // Baterie - bez rabatu
        assertEquals("B079", updatedCart[3].getCode());
        assertEquals(19.90, updatedCart[3].getDiscountPrice(), 0.001);

        // Firmowy kubek - dodany za darmo
        assertEquals("M001", updatedCart[4].getCode());
        assertEquals("Firmowy kubek", updatedCart[4].getName());
        assertEquals(0.0, updatedCart[4].getDiscountPrice(), 0.001);
    }

    @Test
    public void testAddProductToCart() {
        Product newProduct = new Product("P300", "Pendrive 32GB", 49.99);
        javaMarkt.addProductToCart(newProduct);

        Product[] updatedCart = javaMarkt.getCart();
        assertEquals(6, updatedCart.length);

        assertEquals("P300", updatedCart[3].getCode());
        assertEquals(49.99, updatedCart[3].getDiscountPrice(), 0.01);
    }

    @Test
    public void testEmptyCart() {
        JavaMarkt emptyJavaMarkt = new JavaMarkt(new Product[]{}, promotions);

        Product[] updatedCart = emptyJavaMarkt.getCart();
        assertEquals(0, updatedCart.length);
    }
}
