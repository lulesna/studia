import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class JavaMarktTest {
    private JavaMarkt javaMarkt;
    private Product[] cart;
    private List<DiscountCommand> discounts;

    @Before
    public void setUp() {
        cart = new Product[]{
                new Product("D329", "Drukarka", 200.0),
                new Product("M948", "Monitor", 449.49),
                new Product("K150", "Klawiatura", 79.99),
                new Product("B079", "Baterie AA 6szt.", 19.90)
        };

        discounts = List.of(
                new Discount5PercentCommand(),
                new AddMugCommand(),
                new SingleProductDiscountCommand("D329"),
                new ThirdCheapestFreeCommand()
        );

        javaMarkt = new JavaMarkt(cart, discounts);
    }

    @Test
    public void testGetCart() {
        Product[] cart = javaMarkt.getCart();
        assertEquals(4, cart.length);
        assertEquals("D329", cart[0].getCode());
        assertEquals("M948", cart[1].getCode());
        assertEquals("K150", cart[2].getCode());
        assertEquals("B079", cart[3].getCode());
    }

    @Test
    public void testApplyBestDiscount() {
        javaMarkt.applyBestDiscount();
        Product[] cart = javaMarkt.getCart();

        assertEquals(4, cart.length);

        // Drukarka - rabat 30%
        assertEquals("D329", cart[0].getCode());
        assertEquals(140.0, cart[0].getDiscountPrice(), 0.01);

        // Monitor - bez rabatu
        assertEquals("M948", cart[1].getCode());
        assertEquals(449.49, cart[1].getDiscountPrice(), 0.01);

        // Klawiatura - bez rabatu
        assertEquals("K150", cart[2].getCode());
        assertEquals(79.99, cart[2].getDiscountPrice(), 0.01);

        // Baterie - bez rabatu
        assertEquals("B079", cart[3].getCode());
        assertEquals(19.90, cart[3].getDiscountPrice(), 0.001);
    }

    @Test
    public void testApplyAlwaysActiveDiscounts() {
        javaMarkt.applyAlwaysActiveDiscounts();
        Product[] cart = javaMarkt.getCart();
        assertEquals("M001", cart[4].getCode());
        assertEquals(0.00, cart[4].getDiscountPrice(), 0.001);
    }

    @Test
    public void testAddProductToCart() {
        javaMarkt.addProductToCart(new Product("P300", "Pendrive 32GB", 49.99));
        Product[] cart = javaMarkt.getCart();
        assertEquals(6, cart.length);
        assertTrue(javaMarkt.hasProduct("P300"));
    }

    @Test
    public void testRemoveProductFromCart() {
        javaMarkt.removeProductFromCart("K150");
        Product[] cart = javaMarkt.getCart();
        assertEquals(4, cart.length);
        assertFalse(javaMarkt.hasProduct("K150"));
    }

    @Test
    public void testHasProductWithNonExistentProduct() {
        assertFalse(javaMarkt.hasProduct("A000"));
    }

    @Test
    public void testFindProductIndex() {
        int index = javaMarkt.findProductIndex("B079");
        assertEquals(3, index);

        index = javaMarkt.findProductIndex("A000");
        assertEquals(-1, index);
    }

    @Test
    public void testResetCart() {
        javaMarkt.resetCart();
        Product[] cart = javaMarkt.getCart();
        assertEquals(0, cart.length);
    }
}
