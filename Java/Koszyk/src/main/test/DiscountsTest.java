import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.Assert.*;

public class DiscountsTest {
    private Product[] smallCart;
    private Product[] normalCart;
    private Product[] largeCart;

    @Before
    public void setUp() {
        // 2 produkty - poniżej 200 zł
        smallCart = new Product[] {
                new Product("K150", "Klawiatura", 79.99),
                new Product("P249", "Podkładka", 29.99)
        };

        // 3 produkty - powyżej 300 zł
        normalCart = new Product[] {
                new Product("D329", "Drukarka", 200.0),
                new Product("M948", "Monitor", 449.49),
                new Product("K150", "Klawiatura", 79.99)
        };

        // 6 produktów
        largeCart = new Product[] {
                new Product("D329", "Drukarka", 200.0),
                new Product("M948", "Monitor", 449.49),
                new Product("K150", "Klawiatura", 79.99),
                new Product("B079", "Baterie AA 4szt.", 19.90),
                new Product("P249", "Podkładka", 29.99),
                new Product("S022", "Suszarka", 89.99)
        };
    }

    @Test
    public void testDiscount5PercentCommand() {
        DiscountCommand command = new Discount5PercentCommand();

        Product[] result1 = command.apply(smallCart);
        assertEquals(79.99, result1[0].getDiscountPrice(), 0.001);
        assertEquals(29.99, result1[1].getDiscountPrice(), 0.001);

        Product[] result2 = command.apply(normalCart);
        assertEquals(190.0, result2[0].getDiscountPrice(), 0.001);
        assertEquals(427.01, result2[1].getDiscountPrice(), 0.001);
        assertEquals(75.99, result2[2].getDiscountPrice(), 0.001);
    }

    @Test
    public void testThirdCheapestFreeCommand() {
        DiscountCommand command = new ThirdCheapestFreeCommand();

        Product[] result1 = command.apply(smallCart);
        int freeProductCount = 0;
        for (Product p : result1) {
            if (p.getDiscountPrice() == 0.0) {
                freeProductCount++;
            }
        }
        assertEquals("Żaden produkt nie powinien być za darmo", 0, freeProductCount);

        Product[] result2 = command.apply(normalCart);
        freeProductCount = 0;
        for (Product p : result2) {
            if (p.getDiscountPrice() == 0.0) {
                freeProductCount++;
            }
        }
        assertEquals("Jeden produkt powinien być za darmo", 1, freeProductCount);

        Product[] result3 = command.apply(largeCart);
        freeProductCount = 0;
        for (Product p : result3) {
            if (p.getDiscountPrice() == 0.0) {
                freeProductCount++;
            }
        }
        assertEquals("Dwa produkty powinny być za darmo", 2, freeProductCount);

        Product[] sortedByPrice = Arrays.copyOf(largeCart, largeCart.length);
        ProductUtils.sortProducts(sortedByPrice, Comparator.comparingDouble(Product::getDiscountPrice));
        assertEquals(0.0, sortedByPrice[0].getDiscountPrice(), 0.001);
        assertEquals(0.0, sortedByPrice[1].getDiscountPrice(), 0.001);
    }

    @Test
    public void testAddMugCommand() {
        DiscountCommand command = new AddMugCommand();

        Product[] result1 = command.apply(smallCart);
        assertEquals(2, result1.length);

        Product[] result2 = command.apply(normalCart);
        assertEquals(4, result2.length);
        Product lastProduct = result2[result2.length - 1];
        assertEquals("M001", lastProduct.getCode());
        assertEquals(0.0, lastProduct.getDiscountPrice(), 0.001);
    }

    @Test
    public void testSingleProductDiscountCommand() {
        DiscountCommand command = new SingleProductDiscountCommand("D329");
        Product[] result = command.apply(normalCart);

        assertEquals(140.0, result[0].getDiscountPrice(), 0.001);
        assertEquals(449.49, result[1].getDiscountPrice(), 0.001);
        assertEquals(79.99, result[2].getDiscountPrice(), 0.001);
    }

    @Test
    public void testAddMugAndThirdCheapestFree() {
        DiscountCommand AddMugCommand = new AddMugCommand();
        DiscountCommand ThirdCheapestFreeCommand = new ThirdCheapestFreeCommand();
    }
}