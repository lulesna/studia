import java.util.List;

public class JavaMarkt {
    private Product[] cart;
    private List<DiscountCommand> discounts;

    public JavaMarkt(Product[] initialCart, List<DiscountCommand> discounts) {
        this.cart = initialCart;
        this.discounts = discounts;
    }

    public void resetCart() {
        this.cart = new Product[0];
    }

    private void sortCart() {
        ProductUtils.sortProducts(cart, new ProductComparator());
    }

    public Product[] getCart() {
        return this.cart;
    }

    public boolean hasProduct(String productCode) {
        return findProductIndex(productCode) != -1;
    }

    public int findProductIndex(String productCode) {
        for (int i = 0; i < cart.length; i++) {
            if (cart[i].getCode().equals(productCode)) {
                return i;
            }
        }
        return -1;
    }

    public void addProductToCart(Product product) {
        Product[] newCart = new Product[cart.length + 1];
        System.arraycopy(cart, 0, newCart, 0, cart.length);
        newCart[cart.length] = product;
        this.cart = newCart;
        applyDiscountsAndSort();
    }

    public void removeProductFromCart(String productCode) {
        int index = findProductIndex(productCode);
        if (index == -1) return;

        Product[] newCart = new Product[cart.length - 1];
        System.arraycopy(cart, 0, newCart, 0, index);
        System.arraycopy(cart, index + 1, newCart, index, cart.length - index - 1);
        this.cart = newCart;
        applyDiscountsAndSort();
    }

    public void applyDiscountsAndSort() {
        applyAlwaysActiveDiscounts();
        applyBestDiscount();
        sortCart();
    }

    public void applyBestDiscount() {
        if (cart.length == 0) return;

        DiscountCommand bestDiscount = null;
        double maxSavings = 0.0;

        for (DiscountCommand discount : discounts) {
            resetCartPrices();
            double savings = calculateSavings(discount);
            if (savings > maxSavings) {
                maxSavings = savings;
                bestDiscount = discount;
            }
        }

        if (bestDiscount != null) {
            resetCartPrices();
            this.cart = bestDiscount.apply(this.cart);
        }
    }

    public void applyAlwaysActiveDiscounts() {
        for (DiscountCommand promotion : discounts) {
            if (promotion instanceof AlwaysActiveDiscount) {
                this.cart = promotion.apply(this.cart);
            }
        }
    }

    private void resetCartPrices() {
        for (Product product : cart) {
            product.resetPrice();
        }
    }

    private double calculateSavings(DiscountCommand discount) {
        double originalSum = ProductUtils.sumPrices(cart);
        Product[] discountedCart = discount.apply(cart);
        double discountedSum = ProductUtils.sumPrices(discountedCart);
        return originalSum - discountedSum;
    }
}
