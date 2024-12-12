import java.util.Arrays;
import java.util.List;

public class JavaMarkt {
    private Product[] cart;
    private List<DiscountCommand> discounts;

    public JavaMarkt(Product[] initialCart, List<DiscountCommand> discounts) {
        this.cart = initialCart;
        this.discounts = discounts;
        applyDiscountsAndSort();
    }

    public void addProductToCart(Product product) {
        Product[] newCart = new Product[cart.length + 1];
        System.arraycopy(cart, 0, newCart, 0, cart.length);
        newCart[cart.length] = product;
        this.cart = newCart;
        applyDiscountsAndSort();
    }

    private void applyDiscountsAndSort() {
        applyBestDiscount();
        applyAlwaysActiveDiscounts();
        sortCart();
    }

    public void applyBestDiscount() {
        if (cart.length == 0) return;

        DiscountCommand bestDiscount = null;
        double maxSavings = 0.0;

        for (DiscountCommand discount : discounts) {
            double savings = calculateSavings(discount);
            if (savings > maxSavings) {
                maxSavings = savings;
                bestDiscount = discount;
            }
        }

        if (bestDiscount != null) {
            this.cart = bestDiscount.apply(this.cart);
        }
    }

    private double calculateSavings(DiscountCommand discount) {
        double originalSum = ProductUtils.sumPrices(cart);
        Product[] discountedCart = discount.apply(cart);
        double discountedSum = ProductUtils.sumPrices(discountedCart);
        return originalSum - discountedSum;
    }

    public void applyAlwaysActiveDiscounts() {
        for (DiscountCommand promotion : discounts) {
            if (promotion instanceof AlwaysActiveDiscount) {
                this.cart = promotion.apply(this.cart);
            }
        }
    }

    private void sortCart() {
        Arrays.sort(this.cart, new ProductComparator());
    }

    public Product[] getCart() {
        return this.cart;
    }
}
