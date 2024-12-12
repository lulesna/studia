import java.util.Arrays;

interface DiscountCommand {
    Product[] apply(Product[] products);
}

class ThirdCheapestFreeCommand implements DiscountCommand {
    @Override
    public Product[] apply(Product[] products) {

        // ile jest produktów bez kubka
        int productsCount = 0;
        for (Product p : products) {
            if (!"M001".equals(p.getCode())) {
                p.setDiscountPrice(p.getPrice());
                productsCount++;
            }
        }

        if (products.length >= 3) {
            // stworzenie tablicy bez kubka
            Product[] productsWithoutMug = new Product[productsCount];
            int index = 0;
            for (Product p : products) {
                if (!"M001".equals(p.getCode())) {
                    productsWithoutMug[index] = p;
                    index++;
                }
            }

            int freeCount = products.length / 3;
            Product[] cheapestProducts = ProductUtils.findNCheapest(products, freeCount);

            for (Product cheapestProduct : cheapestProducts) {
                for (Product p : products) {
                    if (p == cheapestProduct) {
                        p.setDiscountPrice(0.0);
                        break;
                    }
                }
            }
        }
        return products;
    }
}

class SingleProductDiscountCommand implements DiscountCommand {
    private String productCode;
    private double discountFactor;

    public SingleProductDiscountCommand(String productCode) {
        this.productCode = productCode;
        this.discountFactor = 0.7;
    }

    @Override
    public Product[] apply(Product[] products) {
        for (Product p : products) {
            if (p.getCode().equals(productCode)) {
                double discountedPrice = Math.floor(p.getPrice() * discountFactor * 100) / 100.0;
                p.setDiscountPrice(discountedPrice);
                break;
            }
        }
        return products;
    }
}

class AddMugCommand implements AlwaysActiveDiscount {
    @Override
    public Product[] apply(Product[] products) {
        double sum = ProductUtils.sumPrices(products);

        boolean mugExists = false;
        for (Product product : products) {
            if ("M001".equals(product.getCode())) {
                mugExists = true;
                break;
            }
        }
        if (sum > 200 && !mugExists) {
            Product mug = new Product("M001", "Firmowy kubek", 20.0);
            mug.setDiscountPrice(0.0);
            Product[] newArray = Arrays.copyOf(products, products.length + 1);
            newArray[newArray.length - 1] = mug;
            return newArray;
        }
        return products;
    }
}

class Discount5PercentCommand implements DiscountCommand {
    @Override
    public Product[] apply(Product[] products) {
        double sum = ProductUtils.sumPrices(products);
        if (sum > 300) {
            for (Product p : products) {
                double discountedPrice = Math.floor(p.getPrice() * 0.95 * 100) / 100.0;
                p.setDiscountPrice(discountedPrice);
            }
        }
        return products;
    }
}

