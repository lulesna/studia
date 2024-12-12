import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ProductUtilsTest {
    private Product[] products;

    @Before
    public void setUp() {
        products = new Product[] {
                new Product("T001", "Test 1", 100.0),
                new Product("T002", "Test 2", 50.0),
                new Product("T003", "Test 3", 150.0)
        };
    }

    @Test
    public void testFindCheapest() {
        Product cheapest = ProductUtils.findCheapest(products);
        assert cheapest != null;
        assertEquals("T002", cheapest.getCode());
        assertEquals(50.0, cheapest.getPrice(), 0.001);
    }

    @Test
    public void testFindCheapestEqualPrices() {
        Product[] equalPrices = {
                new Product("P001", "Produkt 1", 100.0),
                new Product("P002", "Produkt 2", 100.0),
                new Product("P003", "Produkt 3", 100.0)
        };
        Product cheapest = ProductUtils.findCheapest(equalPrices);
        assert cheapest != null;
        assertEquals("P001", cheapest.getCode());
    }

    @Test
    public void testFindCheapestEmptyArray() {
        assertNull(ProductUtils.findCheapest(new Product[]{}));
    }

    @Test
    public void testFindMostExpensive() {
        Product mostExpensive = ProductUtils.findMostExpensive(products);
        assert mostExpensive != null;
        assertEquals("T003", mostExpensive.getCode());
        assertEquals(150.0, mostExpensive.getPrice(), 0.001);
    }

    @Test
    public void testFindNCheapest() {
        Product[] cheapest = ProductUtils.findNCheapest(products, 2);
        assertEquals(2, cheapest.length);
        assertEquals("T002", cheapest[0].getCode());
        assertEquals("T001", cheapest[1].getCode());
    }

    @Test
    public void testFindNMostExpensive() {
        Product[] expensive = ProductUtils.findNMostExpensive(products, 2);
        assertEquals(2, expensive.length);
        assertEquals("T003", expensive[0].getCode());
        assertEquals("T001", expensive[1].getCode());
    }

    @Test
    public void testSumPrices() {
        assertEquals(300.0, ProductUtils.sumPrices(products), 0.001);
    }

    @Test
    public void testSortProducts() {
        ProductUtils.sortProducts(products, new ProductComparator());
        assertEquals("Test 3", products[0].getName());
        assertEquals("Test 1", products[1].getName());
        assertEquals("Test 2", products[2].getName());
    }
}