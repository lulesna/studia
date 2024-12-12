import java.util.Arrays;
import java.util.Comparator;

public class Product {
    private String code;
    private String name;
    private double price;
    private double discountPrice;

    public Product(String code, String name, double price) {
        if (code == null || code.isEmpty() || name == null || name.isEmpty() || price < 0) {
            throw new IllegalArgumentException("Nieprawidłowe dane produktu");
        }
        this.code = code;
        this.name = name;
        this.price = price;
        this.discountPrice = price;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        if (discountPrice < 0) {
            throw new IllegalArgumentException("Nieprawidłowa cena");
        }
        this.discountPrice = discountPrice;
    }
}

class ProductComparator implements Comparator<Product> {
    @Override
    public int compare(Product product1, Product product2) {
        // po cenie malejąco
        if (product1.getDiscountPrice() != product2.getDiscountPrice()) {
            return Double.compare(product2.getDiscountPrice(), product1.getDiscountPrice());
        }
        // po nazwie rosnąco
        return product1.getName().compareTo(product2.getName());
    }
}

class ProductUtils {

    public static Product findCheapest(Product[] products) {
        if (products.length == 0) return null;
        Product cheapest = products[0];
        for (Product product : products) {
            if (product.getDiscountPrice() < cheapest.getDiscountPrice()) {
                cheapest = product;
            }
        }
        return cheapest;
    }

    public static Product findMostExpensive(Product[] products) {
        if (products.length == 0) return null;
        Product mostExpensive = products[0];
        for (Product product : products) {
            if (product.getDiscountPrice() > mostExpensive.getDiscountPrice()) {
                mostExpensive = product;
            }
        }
        return mostExpensive;
    }

    public static Product[] findNCheapest(Product[] products, int n) {
        Product[] copy = Arrays.copyOf(products, products.length);
        sortProducts(copy, new ProductComparator());
        if (n > copy.length) {
            n = copy.length;
        }
        // pobiera n ostatnich produktów
        Product[] result = Arrays.copyOfRange(copy, copy.length - n, copy.length);

        // zamienia te n elementów miejscami
        for (int i = 0; i < result.length / 2; i++) {
            Product temp = result[i];
            result[i] = result[result.length - 1 - i];
            result[result.length - 1 - i] = temp;
        }
        return result;
    }

    public static Product[] findNMostExpensive(Product[] products, int n) {
        Product[] copy = Arrays.copyOf(products, products.length);
        sortProducts(copy, new ProductComparator());
        if (n > copy.length) {
            n = copy.length;
        }
        // zwraca n pierwszych produktów
        return Arrays.copyOfRange(copy, 0, n);
    }

    public static double sumPrices(Product[] products) {
        double sum = 0;
        for (Product product : products) {
            sum += product.getDiscountPrice();
        }
        return sum;
    }

    public static void sortProducts(Product[] products, Comparator<Product> comparator) {
        Arrays.sort(products, comparator);
    }
}


