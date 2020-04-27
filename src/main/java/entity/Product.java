package entity;

import java.util.Objects;

public class Product implements Separator {
    public static final char PRODUCT_TYPE='P';

    private Long id;
    private String productName;
    private double price;
    private double weight;
    private int productCount;
    private String color;

    public Product(Long id, String productName, double price, double weight, int productCount, String color) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.weight = weight;
        this.productCount = productCount;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public String getBasicProductString(){
        return id + SEPARATOR +
                productName + SEPARATOR +
                price + SEPARATOR +
                weight + SEPARATOR +
                productCount+SEPARATOR+
                color;
    }
    @Override
    public String toString() {
        return PRODUCT_TYPE+SEPARATOR+getBasicProductString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 &&
                Double.compare(product.weight, weight) == 0 &&
                productCount == product.productCount &&
                Objects.equals(id, product.id) &&
                Objects.equals(productName, product.productName) &&
                Objects.equals(color, product.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, price, weight, productCount, color);
    }
}
